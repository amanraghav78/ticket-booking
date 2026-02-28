package com.ticketbooking.system.config;

import com.ticketbooking.system.util.ResponseHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("Forbidden: you don't have permissions to access this resource");
        ResponseHandler.write(response.getOutputStream(),
                ResponseHandler.generateResponse("Forbidden: you don't have permissions to access this resource.",
                        HttpStatus.FORBIDDEN, false, ""));
    }
}
