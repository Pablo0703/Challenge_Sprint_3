package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.HistoricoLocalizacaoDTO;
import br.com.fiap.Challenge.Service.HistoricoLocalizacaoService;
import br.com.fiap.Challenge.Service.MotoService;
import br.com.fiap.Challenge.Service.MotociclistaService;
import br.com.fiap.Challenge.Service.ZonaPatioService;
import br.com.fiap.Challenge.Service.StatusOperacaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historicos")
public class HistoricoLocalizacaoController {

    private final HistoricoLocalizacaoService service;
    private final MotoService motoService;
    private final MotociclistaService motociclistaService;
    private final ZonaPatioService zonaPatioService;
    private final StatusOperacaoService statusOperacaoService;

    public HistoricoLocalizacaoController(HistoricoLocalizacaoService service,
                                          MotoService motoService,
                                          MotociclistaService motociclistaService,
                                          ZonaPatioService zonaPatioService,
                                          StatusOperacaoService statusOperacaoService) {
        this.service = service;
        this.motoService = motoService;
        this.motociclistaService = motociclistaService;
        this.zonaPatioService = zonaPatioService;
        this.statusOperacaoService = statusOperacaoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historicos", service.listarTodos());
        return "historicolocalizacao/listar"; // corrigido para minúsculo
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("historico", new HistoricoLocalizacaoDTO());
        model.addAttribute("motos", motoService.listarTodos());
        model.addAttribute("motociclistas", motociclistaService.listarTodos());
        model.addAttribute("zonas", zonaPatioService.listarTodos());
        model.addAttribute("statusOperacoes", statusOperacaoService.listarTodos());
        return "historicolocalizacao/formulario"; // corrigido para minúsculo
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        HistoricoLocalizacaoDTO dto = service.buscarPorId(id);
        model.addAttribute("historico", dto);
        model.addAttribute("motos", motoService.listarTodos());
        model.addAttribute("motociclistas", motociclistaService.listarTodos());
        model.addAttribute("zonas", zonaPatioService.listarTodos());
        model.addAttribute("statusOperacoes", statusOperacaoService.listarTodos());
        return "historicolocalizacao/formulario"; // corrigido para minúsculo
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("historico") HistoricoLocalizacaoDTO dto) {
        service.salvar(dto);
        return "redirect:/historicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/historicos";
    }
}
