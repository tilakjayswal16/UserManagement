package com.bitbuy.usermanagement.config;

import com.bitbuy.usermanagement.model.ApiError;
import com.bitbuy.usermanagement.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),authEx.getMessage());
        authEx.printStackTrace();
        writer.println(JsonUtil.convertJsonToString(apiError));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("UserManagement");
        super.afterPropertiesSet();
    }

}