package com.rara.lms.member.service.impl;

import com.rara.lms.admin.dto.MemberHistoryDto;
import com.rara.lms.admin.mapper.MemberHistoryMapper;
import com.rara.lms.admin.model.MemberParam;
import com.rara.lms.member.entity.MemberHistory;
import com.rara.lms.member.model.MemberHistoryInput;
import com.rara.lms.member.repository.MemberHistoryRepository;
import com.rara.lms.member.service.MemberHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberHistoryServiceImpl implements MemberHistoryService {

    private final MemberHistoryRepository memberHistoryRepository;
    private final MemberHistoryMapper memberHistoryMapper;

    @Override
    public boolean saveHistory(MemberHistoryInput parameter) {

        MemberHistory memberHistory = MemberHistory.builder()
                .userId(parameter.getUserId())
                .loginDt(parameter.getLoginDt())
                .userIp(parameter.getUserIp())
                .userAgent(parameter.getUserAgent())
                .build();
        memberHistoryRepository.save(memberHistory);

        return true;
    }

    @Override
    public List<MemberHistoryDto> historyList(MemberParam parameter) {
        long totalCount = memberHistoryMapper.selectListCount(parameter.getUserId());
        List<MemberHistoryDto> list
                = memberHistoryMapper.selectHistoryList(parameter.getUserId());

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (MemberHistoryDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }
}
