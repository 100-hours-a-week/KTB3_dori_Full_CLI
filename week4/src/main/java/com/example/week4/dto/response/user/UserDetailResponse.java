package com.example.week4.dto.response.user;

import com.example.week4.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailResponse {

    private String email;
    private String nickname;
    private String createdDate;
    private String profile_image;

    @Builder
    public UserDetailResponse(String email, String nickname, String createdDate, String profile_image) {
        this.email = email;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.profile_image = profile_image;
    }

    public static UserDetailResponse fromEntity(User user) {
        return UserDetailResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .createdDate(user.getCreatedDate())
                .profile_image(user.getProfile_image())
                .build();

    }

}
