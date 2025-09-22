package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.ZonaPatioDTO;
import br.com.fiap.Challenge.Service.PatioService;
import br.com.fiap.Challenge.Service.ZonaPatioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/zonas-patio")
public class ZonaPatioController {

    private final ZonaPatioService zonaPatioService;
    private final PatioService patioService;

    public ZonaPatioController(ZonaPatioService zonaPatioService, PatioService patioService) {
        this.zonaPatioService = zonaPatioService;
        this.patioService = patioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("zonasPatio", zonaPatioService.listarTodos());
        return "ZonaPatio/listar";
    }
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("zonaPatio", new ZonaPatioDTO());
        model.addAttribute("patios", patioService.listarTodos()); // Carrega os pátios para o <select>
        return "ZonaPatio/FormularioZonaPatio";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("zonaPatio", zonaPatioService.buscarPorId(id));
        model.addAttribute("patios", patioService.listarTodos()); // Carrega os pátios para o <select>
        return "ZonaPatio/FormularioZonaPatio";
    }
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("zonaPatio") ZonaPatioDTO dto,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        // Se houver erros de validação (ex: campos em branco), retorna ao formulário.
        if (result.hasErrors()) {
            // É crucial recarregar a lista de pátios aqui para o formulário não quebrar.
            model.addAttribute("patios", patioService.listarTodos());
            return "ZonaPatio/FormularioZonaPatio";
        }

        try {
            zonaPatioService.salvar(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Zona de Pátio salva com sucesso!");
            return "redirect:/zonas-patio";
        } catch (EntityNotFoundException e) {
            // Se o Service lançar uma exceção (ex: Pátio não encontrado), exibe um erro amigável.
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("patios", patioService.listarTodos());
            return "ZonaPatio/FormularioZonaPatio";
        }
    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            zonaPatioService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Zona de Pátio excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir a zona: " + e.getMessage());
        }
        return "redirect:/zonas-patio";
    }
}