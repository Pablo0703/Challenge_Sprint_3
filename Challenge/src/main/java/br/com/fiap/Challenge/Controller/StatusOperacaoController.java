package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.StatusOperacaoDTO;
import br.com.fiap.Challenge.Service.StatusOperacaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/status-operacoes")
public class StatusOperacaoController {

    private final StatusOperacaoService service;

    public StatusOperacaoController(StatusOperacaoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("statusOperacoes", service.listarTodos());
        return "StatusOperacao/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("statusOperacao", new StatusOperacaoDTO());
        return "StatusOperacao/FormularioStatusOperacao";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        StatusOperacaoDTO dto = service.buscarPorId(id);
        model.addAttribute("statusOperacao", dto);
        return "StatusOperacao/FormularioStatusOperacao";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("statusOperacao") StatusOperacaoDTO dto) {
        service.salvar(dto);
        return "redirect:/status-operacoes";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/status-operacoes";
    }
}