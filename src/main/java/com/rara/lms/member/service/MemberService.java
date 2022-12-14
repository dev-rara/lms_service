package com.rara.lms.member.service;

import com.rara.lms.admin.dto.MemberDto;
import com.rara.lms.admin.model.MemberParam;
import com.rara.lms.course.model.ServiceResult;
import com.rara.lms.member.model.MemberInput;
import com.rara.lms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);


    /**
     * uuid 에 해당하는 계정을 활성화
     */
    boolean emailAuth(String uuid);


    /**
     * 입력한 이메일로 비밀번호 초기화 안내 전송
     */
    boolean sendResetPassword(ResetPasswordInput parameter);


    /**
     * 입력받은 uuid 에 대해서 password 초기화 진행
     */
    boolean resetPassword(String uuid, String password);


    /**
     * 입력받은 uuid 값이 유효한지 확인
     */
    boolean checkResetPassword(String uuid);


    /**
     * 회원 목록 가져오기(관리자만 사용가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String userId, String password);

    /**
     * 회원 정보 수정
     */
    ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원 정보 페이지에서 비밀번호 변경
     */
    ServiceResult updateMemberPassword(MemberInput parameter);

    /**
     * 회원 탈퇴
     */
    ServiceResult withdraw(String userId, String password);

}
