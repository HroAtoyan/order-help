package com.platform.springjpa;

import com.platform.config.JwtUtil;
import com.platform.entity.UserEntity;
import com.platform.exceptions.userexceptions.UserApiException;
import com.platform.exceptions.userexceptions.UserBadRequestException;
import com.platform.model.LoginRequest;
import com.platform.repository.UserRepository;
import com.platform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenSpringJpa implements TokenService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String getToken(LoginRequest loginRequest) {
        String token = null;
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            String email = authenticate.getName();
            UserEntity userEntity = userRepository.getByEmail(email);
            token = jwtUtil.createToken(userEntity);

        } catch (BadCredentialsException e) {
            throw new UserBadRequestException("Invalid username or password");
        } catch (Exception e) {
            throw new UserApiException("Problem during getting token");
        }
        return token;
    }
}