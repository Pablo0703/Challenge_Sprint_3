package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.NotaFiscalDTO;
import br.com.fiap.Challenge.Service.NotaFiscalService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    private final NotaFiscalService service;

    public NotaFiscalController(NotaFiscalService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("notasFiscais", service.listarTodos());
        return "NotaFiscal/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("notaFiscal", new NotaFiscalDTO());
        return "NotaFiscal/FormularioNotaFiscal";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        NotaFiscalDTO dto = service.buscarPorId(id);
        model.addAttribute("notaFiscal", dto);
        return "NotaFiscal/FormularioNotaFiscal";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("notaFiscal") NotaFiscalDTO dto) {
        service.salvar(dto);
        return "redirect:/notas-fiscais";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/notas-fiscais";
    }
}