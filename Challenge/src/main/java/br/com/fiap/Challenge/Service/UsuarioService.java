package br.com.fiap.Challenge.Service;

import br.com.fiap.Challenge.Entity.UsuarioEntity;
import br.com.fiap.Challenge.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntity cadastrar(String username, String email, String password) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password));
        return repo.save(usuario);
    }
}
