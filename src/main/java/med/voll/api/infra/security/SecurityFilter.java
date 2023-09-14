package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("el filtro esta siendo llamado");
      /*  var authHeader=request.getHeader("Authorization").replace("Bearer ","");

        if (authHeader!=null){

            System.out.println("validamos que el token no es null");
            var token=authHeader.replace("Bearer ","");

            var nombreUsuario=tokenService.getSubject(authHeader);
            if (nombreUsuario!=null){
                var usuario=usuarioRepository.findByLogin(nombreUsuario);
                var autentication= new UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autentication);
            }

        }*/
        filterChain.doFilter(request,response);


    }
}
