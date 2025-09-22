package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.Entity.UsuarioEntity;
import br.com.fiap.Challenge.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final UsuarioService service;

    public SignupController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody UsuarioEntity usuario) {
        service.cadastrar(usuario.getUsername(), usuario.getEmail(), usuario.getPassword());
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
