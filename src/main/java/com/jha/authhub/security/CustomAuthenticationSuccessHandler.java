package com.jha.authhub.security;
import com.jha.authhub.model.TokenEntity;
import com.jha.authhub.model.UserEntity;
import com.jha.authhub.repository.TokenRepository;
import com.jha.authhub.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userName = authentication.getName();

        // todo : handle throw
        UserEntity userEntity = userRepository.findOneByName(userName).orElseThrow();
        Long userIndex = userEntity.getUserIndex();
        String accessToken = jwtManager.createAccessToken(userIndex, userName);
        String refreshToken = jwtManager.createRefreshToken(userIndex);

        // todo : handle throw
        TokenEntity tokenEntity = tokenRepository.findById(userIndex).orElseGet(TokenEntity::new);
        tokenEntity.setAccessToken(accessToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity = tokenRepository.save(tokenEntity);

        String redirectionURL = request.getParameter("redirection");
        if (redirectionURL != null) {
            redirectionURL = redirectionURL + "?tokenIndex=" + tokenEntity.getTokenIndex();
            response.sendRedirect(redirectionURL);
        }
    }
}
