package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.FilialDTO;
import br.com.fiap.Challenge.Service.EnderecoService;
import br.com.fiap.Challenge.Service.FilialService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filiais")
public class FilialController {

    private final FilialService service;
    private final EnderecoService enderecoService;

    public FilialController(FilialService service, EnderecoService enderecoService) {
        this.service = service;
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filiais", service.listarTodos());
        // Caminho do template corrigido para minúsculas
        return "filial/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filial", new FilialDTO());
        model.addAttribute("enderecos", enderecoService.listarTodos());
        // Caminho do template corrigido para minúsculas
        return "filial/FormularioFilial";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        FilialDTO dto = service.buscarPorId(id);
        model.addAttribute("filial", dto);
        model.addAttribute("enderecos", enderecoService.listarTodos());
        // Caminho do template corrigido para minúsculas
        return "filial/FormularioFilial";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("filial") FilialDTO dto) {
        service.salvar(dto);
        return "redirect:/filiais";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/filiais";
    }
}