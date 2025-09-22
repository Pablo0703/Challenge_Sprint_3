package br.com.fiap.Challenge.Controller;

import br.com.fiap.Challenge.DTO.MotoDTO;
import br.com.fiap.Challenge.Repository.NotaFiscalRepository;
import br.com.fiap.Challenge.Repository.StatusMotoRepository;
import br.com.fiap.Challenge.Repository.TipoMotoRepository;
import br.com.fiap.Challenge.Service.MotoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
public class MotoController {

    // 1. Dependências injetadas, tal como no exemplo.
    private final MotoService motoService;
    private final TipoMotoRepository tipoMotoRepository;
    private final StatusMotoRepository statusMotoRepository;
    private final NotaFiscalRepository notaFiscalRepository;

    public MotoController(MotoService motoService, TipoMotoRepository tipoMotoRepository,
                          StatusMotoRepository statusMotoRepository, NotaFiscalRepository notaFiscalRepository) {
        this.motoService = motoService;
        this.tipoMotoRepository = tipoMotoRepository;
        this.statusMotoRepository = statusMotoRepository;
        this.notaFiscalRepository = notaFiscalRepository;
    }

    // 2. Método para listar todos, seguindo o padrão.
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motos", motoService.listarTodos());
        return "Moto/listar";
    }

    // 3. Método para exibir o formulário de um novo registo.
    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("moto", new MotoDTO());
        // Adiciona as listas necessárias para os dropdowns do formulário.
        model.addAttribute("tipos", tipoMotoRepository.findAll());
        model.addAttribute("statusList", statusMotoRepository.findAll());
        model.addAttribute("notasFiscais", notaFiscalRepository.findAll());
        return "Moto/FormularioMoto";
    }

    // 4. Método para exibir o formulário de edição.
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("moto", motoService.buscarPorId(id));
        model.addAttribute("tipos", tipoMotoRepository.findAll());
        model.addAttribute("statusList", statusMotoRepository.findAll());
        model.addAttribute("notasFiscais", notaFiscalRepository.findAll());
        return "Moto/FormularioMoto";
    }

    /**
     * 5. MÉTODO SALVAR (A VERSÃO MELHORADA):
     * Este método tem a estrutura do teu exemplo, mas com o acréscimo do try-catch
     * para uma melhor experiência do utilizador.
     */
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("moto") MotoDTO motoDTO,
                         BindingResult result, RedirectAttributes redirectAttributes, Model model) {

        // Se houver erros de validação (@NotBlank, @Size, etc.), volta para o formulário.
        if (result.hasErrors()) {
            model.addAttribute("tipos", tipoMotoRepository.findAll());
            model.addAttribute("statusList", statusMotoRepository.findAll());
            model.addAttribute("notasFiscais", notaFiscalRepository.findAll());
            return "Moto/FormularioMoto";
        }

        try {
            // Tenta salvar. Se a placa/chassi for duplicado, o service vai lançar um erro.
            motoService.salvar(motoDTO);
            redirectAttributes.addFlashAttribute("sucesso", "Moto salva com sucesso!");
            // Em caso de sucesso, redireciona para a lista.
            return "redirect:/motos";

        } catch (DataIntegrityViolationException | EntityNotFoundException e) {
            // Captura o erro de duplicidade (ou outro erro de dados) e, em vez de crashar...
            model.addAttribute("erro", e.getMessage()); // ...adiciona a mensagem de erro ao modelo...
            // ...recarrega as listas para os dropdowns...
            model.addAttribute("tipos", tipoMotoRepository.findAll());
            model.addAttribute("statusList", statusMotoRepository.findAll());
            model.addAttribute("notasFiscais", notaFiscalRepository.findAll());
            // ...e volta para o formulário para o utilizador corrigir.
            return "Moto/FormularioMoto";
        }
    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        motoService.excluir(id); // Simplesmente chama o serviço para excluir
        redirectAttributes.addFlashAttribute("sucesso", "Moto excluída com sucesso!");
        return "redirect:/motos";
    }

}