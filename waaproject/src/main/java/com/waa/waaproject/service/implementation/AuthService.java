package com.waa.waaproject.service.implementation;

import com.waa.waaproject.domain.Role;
import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.LoginRequest;
import com.waa.waaproject.dto.LoginResponse;
import com.waa.waaproject.dto.RefreshTokenRequest;
import com.waa.waaproject.repository.IUserRepository;
import com.waa.waaproject.repository.implementation.UserRepository;
import com.waa.waaproject.service.IAuthService;
import com.waa.waaproject.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        if (jwtUtil.validateToken(refreshTokenRequest.getRefreshToken()) && jwtUtil.isRefreshToken(refreshTokenRequest.getRefreshToken())) { //Added check for refresh token type
            if (jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken())) {
                final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            } else {
                log.info("Access token is not expired.");
                return new LoginResponse(refreshTokenRequest.getAccessToken(), refreshTokenRequest.getRefreshToken()); //Return the same tokens if access token is still valid.
            }
        } else {
            log.warn("Invalid refresh token.");
            return new LoginResponse(); // Or throw an exception
        }
    }

    @Override
    public void signUp(User user) {

    }

}
