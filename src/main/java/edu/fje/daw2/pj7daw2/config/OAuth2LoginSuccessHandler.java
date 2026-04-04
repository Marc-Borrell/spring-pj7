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
/**
 * Handler executat després d'un login exitós amb OAuth2 Google.
 * Desa o actualitza l'usuari a la base de dades MySQL i redirigeix al panell admin.
 *
 * @author Grup1
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuariService usuariService;

    /**
     * Processa l'autenticació exitosa, desa l'usuari i redirigeix al panell.
     *
     * @param request        petició HTTP
     * @param response       resposta HTTP
     * @param authentication objecte d'autenticació amb les dades de l'usuari
     * @throws IOException si hi ha un error de redirecció
     */
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