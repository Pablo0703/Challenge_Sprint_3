package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.PatioDTO;
import br.com.fiap.Challenge.Repository.FilialRepository;
import br.com.fiap.Challenge.Service.PatioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patios")
public class PatioController {

    private final PatioService service;
    private final FilialRepository filialRepository;

    public PatioController(PatioService service, FilialRepository filialRepository) {
        this.service = service;
        this.filialRepository = filialRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("patios", service.listarTodos());
        return "Patio/listar";
    }

    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("patio", new PatioDTO());
        model.addAttribute("filiais", filialRepository.findAll());
        return "Patio/FormularioPatio";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("patio", service.buscarPorId(id));
        model.addAttribute("filiais", filialRepository.findAll());
        return "Patio/FormularioPatio";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("patio") PatioDTO patioDTO,
                         BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            return "Patio/FormularioPatio";
        }
        service.salvar(patioDTO);
        redirectAttributes.addFlashAttribute("sucesso", "Pátio salvo com sucesso!");
        return "redirect:/patios";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Pátio excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir pátio: " + e.getMessage());
        }
        return "redirect:/patios";
    }
}