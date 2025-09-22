package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.StatusMotoDTO;
import br.com.fiap.Challenge.Service.StatusMotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/status-motos")
public class StatusMotoController {

    private final StatusMotoService service;

    public StatusMotoController(StatusMotoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("statusMotos", service.listarTodos());
        // CORREÇÃO: Alterado para "StatusMoto" para corresponder ao nome da pasta.
        return "StatusMoto/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("statusMoto", new StatusMotoDTO());
        // CORREÇÃO: Alterado para "StatusMoto" para corresponder ao nome da pasta.
        return "StatusMoto/FormularioStatusMoto";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        StatusMotoDTO dto = service.buscarPorId(id);
        model.addAttribute("statusMoto", dto);
        // CORREÇÃO: Alterado para "StatusMoto" para corresponder ao nome da pasta.
        return "StatusMoto/FormularioStatusMoto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("statusMoto") StatusMotoDTO dto) {
        service.salvar(dto);
        return "redirect:/status-motos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/status-motos";
    }
}