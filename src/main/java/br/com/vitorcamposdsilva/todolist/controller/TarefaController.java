package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.Repository.ITarefaRepository;
import br.com.vitorcamposdsilva.todolist.model.TarefaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private ITarefaRepository tarefaRepository;

    @PostMapping("/cadastrar")
    public TarefaModel cadastrar(@RequestBody TarefaModel tarefaModel){
        var tarefaCriada = this.tarefaRepository.save(tarefaModel);
        return tarefaCriada;
    }
}
