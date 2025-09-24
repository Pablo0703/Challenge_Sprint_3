package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.RegisterRequestDTO;
import br.com.fiap.Challenge.Entity.UsuarioEntity;
import br.com.fiap.Challenge.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequestDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute RegisterRequestDTO registerRequest, Model model) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            model.addAttribute("error", "As senhas não coincidem!");
            return "signup";
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setName(registerRequest.getName());
        usuario.setUsername(registerRequest.getUsername());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        return "redirect:/auth/login";
    }
}
