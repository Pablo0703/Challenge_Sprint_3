package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.EnderecoDTO;
import br.com.fiap.Challenge.Service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("enderecos", service.listarTodos());
        return "endereco/listar";
    }

    // NOVO
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("endereco", new EnderecoDTO());
        return "endereco/FormularioEndereco";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("endereco", service.buscarPorId(id));
        return "endereco/FormularioEndereco";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("endereco") EnderecoDTO dto) {
        service.salvar(dto);
        return "redirect:/enderecos";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/enderecos";
    }
}
