package com.token.authenticate.service;

import com.token.authenticate.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${jwt.token.secret}")
    private String secretKey;

    private Long expiredTimeMs = 1000 * 60 * 60l;

    public String login(String userName, String password){
        return JwtTokenUtil.createToken("kimnayeong", secretKey, expiredTimeMs);
    }
}
