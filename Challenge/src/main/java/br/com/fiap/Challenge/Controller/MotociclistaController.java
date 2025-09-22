package br.com.fiap.Challenge.Controller;


import br.com.fiap.Challenge.DTO.MotociclistaDTO;
import br.com.fiap.Challenge.Repository.EnderecoRepository;
import br.com.fiap.Challenge.Service.MotociclistaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motociclistas")
public class MotociclistaController {

    private final MotociclistaService service;
    private final EnderecoRepository enderecoRepository;

    public MotociclistaController(MotociclistaService service, EnderecoRepository enderecoRepository) {
        this.service = service;
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motociclistas", service.listarTodos());
        // Este já está correto para o seu arquivo listar.html
        return "Motociclista/listar";
    }

    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("motociclista", new MotociclistaDTO());
        model.addAttribute("enderecos", enderecoRepository.findAll());

        // CORREÇÃO FINAL: Apontando para o seu nome de arquivo
        return "Motociclista/FormularioMotociclista";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        MotociclistaDTO motociclistaDTO = service.buscarPorId(id);
        model.addAttribute("motociclista", motociclistaDTO);
        model.addAttribute("enderecos", enderecoRepository.findAll());

        // CORREÇÃO FINAL: Apontando para o seu nome de arquivo
        return "Motociclista/FormularioMotociclista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("motociclista") MotociclistaDTO motociclistaDTO,
                         BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("enderecos", enderecoRepository.findAll());
            // CORREÇÃO FINAL: Se der erro, volte para o mesmo formulário
            return "Motociclista/FormularioMotociclista";
        }
        service.salvar(motociclistaDTO);
        redirectAttributes.addFlashAttribute("sucesso", "Motociclista salvo com sucesso!");
        return "redirect:/motociclistas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Motociclista excluído com sucesso!");
        return "redirect:/motociclistas";
    }
}