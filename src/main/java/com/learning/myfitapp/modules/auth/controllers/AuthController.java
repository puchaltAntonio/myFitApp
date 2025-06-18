package com.learning.myfitapp.modules.auth.controllers;

import com.learning.myfitapp.modules.auth.models.requests.AccessRequest;
import com.learning.myfitapp.modules.auth.models.responses.TokensResponse;
import com.learning.myfitapp.modules.auth.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = AuthController.SWAGGER_TAG)
@RequestMapping(path = AuthController.CONTROLLER_PATH)
@Validated
@RequiredArgsConstructor
public class AuthController {
    public static final String SWAGGER_TAG = "Auth API";

    public static final String CONTROLLER_PATH = "/v1/auth";

    public static final String POST_ACCESS_SUB_PATH = "/login";

    public static final String POST_REFRESH_SUB_PATH = "/refresh";

    public static final String POST_LOGOUT_SUB_PATH = "/logout";

    public static final String PUT_RECOVER_PASSWORD_PATH = "/password";

    public static final String REFRESH_COOKIE_NAME = "refreshToken";

    @Value("${api.auth.cookie.http-only}")
    private boolean cookieHttpOnly;

    @Value("${api.auth.cookie.same-site}")
    private String cookieSameSite;

    @Value("${api.auth.cookie.secure}")
    private boolean cookieSecure;

    private final AuthService authService;

    @PostMapping(POST_ACCESS_SUB_PATH)
    public ResponseEntity<TokensResponse> getAccessToken(
            @RequestBody @Valid AccessRequest accessRequest
    ){
       final TokensResponse tokensResponse = authService
               .access(accessRequest);

       return ResponseEntity
               .ok()
               .header(
                       HttpHeaders.SET_COOKIE,
                       refreshTokenCookies(
                               tokensResponse.refreshToken(),
                               tokensResponse.refreshExpiresIn()
                       )
               )
               .body(tokensResponse);
    }

    @PostMapping(POST_REFRESH_SUB_PATH)
    public ResponseEntity<TokensResponse> refreshToken(
            @CookieValue(name = REFRESH_COOKIE_NAME)
            String refreshToken
    ){
       final TokensResponse tokensResponse = authService
               .refresh(refreshToken);

       return ResponseEntity
               .ok()
               .header(
                       HttpHeaders.SET_COOKIE,
                       refreshTokenCookies(
                               tokensResponse.refreshToken(),
                               tokensResponse.refreshExpiresIn()
                       )
               )
               .body(tokensResponse);
    }

    @PostMapping(POST_LOGOUT_SUB_PATH)
    public ResponseEntity<Void> logout(
            @CookieValue(name = REFRESH_COOKIE_NAME)
            String refreshToken
    ){
        authService.logout(refreshToken);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(PUT_RECOVER_PASSWORD_PATH)
    public ResponseEntity<Void> recoverPassword(
            @RequestParam @Email(message = "Invalid email format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
            final String refreshToken
    ){
        authService.logout(refreshToken);
        return ResponseEntity.noContent().build();
    }


    private String refreshTokenCookies(final String refreshToken, final long refreshExpiresIn){
        return ResponseCookie
                .from(REFRESH_COOKIE_NAME, refreshToken)
                .httpOnly(cookieHttpOnly)
                .maxAge(refreshExpiresIn)
                .sameSite(cookieSameSite)
                .secure(cookieSecure)
                .build()
                .toString();
    }






}
