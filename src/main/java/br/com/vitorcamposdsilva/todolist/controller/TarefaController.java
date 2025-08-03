package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.Repository.ITarefaRepository;
import br.com.vitorcamposdsilva.todolist.model.TarefaModel;
import br.com.vitorcamposdsilva.todolist.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private ITarefaRepository tarefaRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody TarefaModel tarefaModel, HttpServletRequest request) {

        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        if (idUsuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        tarefaModel.setIdUsuario(idUsuario);

        var dataAtual = LocalDateTime.now();

        if (dataAtual.isAfter(tarefaModel.getDataInicio())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início e data de término deve ser maior que a data atual");
        }

        if (tarefaModel.getDataInicio().isAfter(tarefaModel.getDataFim())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor do que a data de término");
        }

        tarefaRepository.save(tarefaModel);
        return ResponseEntity.status(HttpStatus.OK).body(tarefaModel);
    }

    @GetMapping("/listar")
    public List<TarefaModel> listarTarefa(HttpServletRequest request){
        var idUsuario = request.getAttribute("idUsuario");
        var tarefas = this.tarefaRepository.findByIdUsuario((UUID) idUsuario);
        return tarefas;
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity editarTarefa(@RequestBody TarefaModel tarefaModel, HttpServletRequest request, @PathVariable UUID id){
        var idUsuario = request.getAttribute("idUsuario");
        var  tarefa = this.tarefaRepository.findById(id).orElse(null);

        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa não encontrada.");
        }

        if (!tarefa.getIdUsuario().equals(idUsuario)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O usuário não tem permissão para alterar esta tarefa.");
        }
        Util.copiarPropriedadeNula(tarefaModel,tarefa);

        return ResponseEntity.ok().body(this.tarefaRepository.save(tarefa));
    }
}
