package com.culturestamp.back.auth.api.controller;


import com.culturestamp.back.auth.api.service.UserOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private UserOAuthService userOAuthService;

    @RequestMapping("/login/google")
    public ResponseEntity loginWithGoogleOauth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken = userOAuthService.loginOAuthGoogle(request);

        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
                .httpOnly(true)
                .maxAge(7 * 24 * 3600)
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        // TODO: 후에 redirectionUri 변경
        response.sendRedirect("http://localhost:8080/");
        return ResponseEntity.ok().build();
    }

}
