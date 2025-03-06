package com.waa.waaproject.service;

import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.LoginRequest;
import com.waa.waaproject.dto.LoginResponse;
import com.waa.waaproject.dto.RefreshTokenRequest;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    void signUp(User user);
}
