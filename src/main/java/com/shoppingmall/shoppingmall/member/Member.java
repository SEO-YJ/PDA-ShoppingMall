package com.shoppingmall.shoppingmall.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
