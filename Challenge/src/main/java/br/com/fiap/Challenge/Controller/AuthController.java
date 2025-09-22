package br.com.fiap.Challenge.Controller;

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

    // 👉 Página de login
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    // 👉 Página de cadastro
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        return "signup"; // templates/signup.html
    }

    // 👉 Registro de usuário
    @PostMapping("/signup")
    public String register(@ModelAttribute UsuarioEntity usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEnabled(true);
        usuarioRepository.save(usuario);

        // ✅ Depois de cadastrar, redireciona para login
        return "redirect:/auth/login";
    }
}
