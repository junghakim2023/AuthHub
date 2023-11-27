package com.jha.authhub.controller;

import com.jha.authhub.model.TokenEntity;
import com.jha.authhub.model.UserEntity;
import com.jha.authhub.repository.TokenRepository;
import com.jha.authhub.security.JWTManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthHubController {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    JWTManager jwtManager;

    @GetMapping("/test")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("test");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/login/token/get")
    public String requestToken(HttpServletRequest request, HttpServletResponse response) {
        String tokenKey = request.getParameter("tokenKey");
        TokenEntity tokenEntity = tokenRepository.findByTokenKey(tokenKey).orElseThrow();
        JSONObject resObject = new JSONObject();

        if (!jwtManager.checkValidToken(tokenEntity.getAccessToken()) || !jwtManager.checkValidToken(tokenEntity.getRefreshToken())){
            // todo : Exception Handling
            // case : expired, invalid .. etc
            resObject.put("accessToken", null);
            resObject.put("refreshToken", null);

            return resObject.toJSONString();
        }

        resObject.put("accessToken", tokenEntity.getAccessToken());
        resObject.put("refreshToken", tokenEntity.getRefreshToken());

        return resObject.toJSONString();
    }


    @CrossOrigin(origins = {"${allowed.origins1}", "${allowed.origins2}"}, allowCredentials = "true")
    @PostMapping("/login/token/refresh/accessToken")
    public String requestAccessToken(@RequestBody String refreshTokenBody, HttpServletRequest request, HttpServletResponse response) {
        JSONObject resObject = new JSONObject();
        String refreshToken = refreshTokenBody.replace("refresh=", "");
        //todo : handling throw
        TokenEntity tokenEntity = tokenRepository.findByRefreshToken(refreshToken).orElseThrow();
        if (!jwtManager.checkValidToken(tokenEntity.getRefreshToken())){
            resObject.put("accessToken", null);
            return resObject.toJSONString();
        }

        UserEntity userEntity = tokenEntity.getUser();
        String newAccessToken = jwtManager.createAccessToken(userEntity.getUserIndex(), userEntity.getName());
        resObject.put("accessToken", tokenEntity.getAccessToken());
        tokenEntity.setAccessToken(newAccessToken);
        tokenRepository.save(tokenEntity);

        return resObject.toJSONString();
    }
}
