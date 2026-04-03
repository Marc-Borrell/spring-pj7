package edu.fje.daw2.pj7daw2.config;

import edu.fje.daw2.pj7daw2.service.UsuariService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuariService usuariService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        String email   = oauth2User.getAttribute("email");
        String nom     = oauth2User.getAttribute("name");
        String fotoUrl = oauth2User.getAttribute("picture");

        usuariService.obtenirOCrearUsuari(email, nom, fotoUrl);

        getRedirectStrategy().sendRedirect(request, response, "/admin");
    }
}