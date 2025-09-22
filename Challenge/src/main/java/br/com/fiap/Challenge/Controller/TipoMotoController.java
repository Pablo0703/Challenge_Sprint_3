package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.TipoMotoDTO;
import br.com.fiap.Challenge.Service.TipoMotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos-moto")
public class TipoMotoController {

    private final TipoMotoService service;

    public TipoMotoController(TipoMotoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tiposMoto", service.listarTodos());
        return "TipoMoto/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tipoMoto", new TipoMotoDTO());
        return "TipoMoto/FormularioTipoMoto";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        TipoMotoDTO dto = service.buscarPorId(id);
        model.addAttribute("tipoMoto", dto);
        return "TipoMoto/FormularioTipoMoto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("tipoMoto") TipoMotoDTO dto) {
        service.salvar(dto);
        return "redirect:/tipos-moto";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/tipos-moto";
    }
}