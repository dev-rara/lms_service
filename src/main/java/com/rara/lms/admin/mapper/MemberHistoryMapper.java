package com.rara.lms.admin.mapper;

import com.rara.lms.admin.dto.MemberHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberHistoryMapper {

    List<MemberHistoryDto> selectHistoryList(String userId);

    long selectListCount(String userId);
}
