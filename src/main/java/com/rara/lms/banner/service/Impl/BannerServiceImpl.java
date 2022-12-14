package com.rara.lms.banner.service.Impl;

import com.rara.lms.banner.dto.BannerDto;
import com.rara.lms.banner.entity.Banner;
import com.rara.lms.banner.mapper.BannerMapper;
import com.rara.lms.banner.model.BannerInput;
import com.rara.lms.banner.model.BannerParam;
import com.rara.lms.banner.repository.BannerRepository;
import com.rara.lms.banner.service.BannerService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .url(parameter.getUrl())
                .alterText(parameter.getAlterText())
                .openMethod(parameter.getOpenMethod())
                .fileDirectory(parameter.getFileDirectory())
                .sortOrder(parameter.getSortOrder())
                .openYn(true)
                .regDt(LocalDateTime.now())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setUrl(parameter.getUrl());
        banner.setAlterText(parameter.getAlterText());
        banner.setOpenMethod(parameter.getOpenMethod());
        banner.setFileDirectory(parameter.getFileDirectory());
        banner.setSortOrder(parameter.getSortOrder());
        banner.setOpenYn(parameter.isOpenYn());
        banner.setRegDt(banner.getRegDt());
        banner.setUdtDt(LocalDateTime.now());

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(long id) {

        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;

                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> listAll() {

        return bannerMapper.selectAllList();
    }
}
