package com.agenceImmo.security;


import com.agenceImmo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User userConnecter = (User) authentication.getPrincipal();
        String redirectUrl = request.getContextPath(); // l'url de redirection

        if (userConnecter.isRole("ADMIN")){
            redirectUrl = "/admin";
        }else if (userConnecter.isRole("USER")){
            redirectUrl = "/user";
        }

        response.sendRedirect(redirectUrl); // d'effectuer la redirection

    }
}
