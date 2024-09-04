package com.platform.service;

import com.platform.model.LoginRequest;

public interface TokenService {

    String getToken(LoginRequest loginRequest);

}
