package br.com.vitorcamposdsilva.todolist.autenticacao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vitorcamposdsilva.todolist.Repository.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/tarefas")) {

            String autorizacao = request.getHeader("Authorization");

            if (autorizacao == null || !autorizacao.startsWith("Basic ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciais ausentes");
                return;
            }

            String base64Credenciais = autorizacao.substring("Basic".length()).trim();
            byte[] credenciaisDecodificadas = Base64.getDecoder().decode(base64Credenciais);
            String credenciais = new String(credenciaisDecodificadas);

            String[] partes = credenciais.split(":");
            if (partes.length != 2) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Formato inválido");
                return;
            }

            String nomeUsuario = partes[0];
            String senha = partes[1];

            var usuario = iUsuarioRepository.findByNome(nomeUsuario);
            if (usuario == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
                return;
            }

            var resultado = BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha());
            if (!resultado.verified) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Senha incorreta");
                return;
            }

            request.setAttribute("idUsuario", usuario.getId());

            filterChain.doFilter(request, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }
}
