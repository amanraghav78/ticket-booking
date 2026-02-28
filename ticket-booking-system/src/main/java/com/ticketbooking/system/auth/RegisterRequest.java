package com.ticketbooking.system.auth;

import com.ticketbooking.system.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @NotEmpty(message = "user name is required")
    private String username;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    private Role role;

    @NotEmpty(message = "Phone is required")
    private String phone;

}
