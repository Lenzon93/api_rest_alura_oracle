/*package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.hibernate.annotations.Comment;
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


    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
   var token=request.getHeader("Authorization").replace("Bearer ","");

        if(token!=null){

            var subject=tokenService.getSubject(token);
            if (subject != null){
                var usuario=usuarioRepository.findByLogin(subject);
                var authentication= new UsernamePasswordAuthenticationToken(usuario,null,null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
       }
        filterChain.doFilter(request,response);




    }
}*/
