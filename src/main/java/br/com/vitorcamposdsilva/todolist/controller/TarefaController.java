package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.Repository.ITarefaRepository;
import br.com.vitorcamposdsilva.todolist.model.TarefaModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private ITarefaRepository tarefaRepository;

    @PostMapping("/cadastrar")
    public TarefaModel cadastrar(@RequestBody TarefaModel tarefaModel, HttpServletRequest request) {

        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        if (idUsuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        tarefaModel.setIdUsuario(idUsuario);
        return tarefaRepository.save(tarefaModel);
    }
}
