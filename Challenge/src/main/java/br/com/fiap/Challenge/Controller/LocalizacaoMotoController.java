package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.LocalizacaoMotoDTO;
import br.com.fiap.Challenge.Service.LocalizacaoMotoService;
import br.com.fiap.Challenge.Service.MotoService;
import br.com.fiap.Challenge.Service.ZonaPatioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/localizacoes-moto")
public class LocalizacaoMotoController {

    private final LocalizacaoMotoService service;
    private final MotoService motoService;
    private final ZonaPatioService zonaPatioService;

    public LocalizacaoMotoController(LocalizacaoMotoService service, MotoService motoService, ZonaPatioService zonaPatioService) {
        this.service = service;
        this.motoService = motoService;
        this.zonaPatioService = zonaPatioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("localizacoes", service.listarTodos());
        return "LocalizacaoMoto/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("localizacao", new LocalizacaoMotoDTO());
        model.addAttribute("motos", motoService.listarTodos());
        model.addAttribute("zonas", zonaPatioService.listarTodos());
        // CORREÇÃO: Ajustado para o nome correto do arquivo
        return "LocalizacaoMoto/FormularioLocalizacaoMoto";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        LocalizacaoMotoDTO dto = service.buscarPorId(id);
        model.addAttribute("localizacao", dto);
        model.addAttribute("motos", motoService.listarTodos());
        model.addAttribute("zonas", zonaPatioService.listarTodos());
        // CORREÇÃO: Ajustado para o nome correto do arquivo
        return "LocalizacaoMoto/FormularioLocalizacaoMoto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("localizacao") LocalizacaoMotoDTO dto) {
        service.salvar(dto);
        return "redirect:/localizacoes-moto";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/localizacoes-moto";
    }
}