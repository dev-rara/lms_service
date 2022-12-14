package com.rara.lms.admin.dto;

import com.rara.lms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {

    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;
    LocalDateTime udtDt;

    boolean emailAuthYn;
    LocalDateTime  emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;

    String zipcode;
    String addr;
    String addrDetail;

    LocalDateTime lastLoginDt;

    //컬럼 추가
    long totalCount;
    long seq;

    public static MemberDto of(Member member) {
        return  MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .regDt(member.getRegDt())
                .udtDt(member.getUdtDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .zipcode(member.getZipcode())
                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail())
                .lastLoginDt(member.getLastLoginDt())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        return regDt != null? regDt.format(formatter) : "";
    }

    public String getUdtDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        return udtDt != null? udtDt.format(formatter) : "";
    }

    public String getLastLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        return lastLoginDt != null? lastLoginDt.format(formatter) : "";
    }
}
