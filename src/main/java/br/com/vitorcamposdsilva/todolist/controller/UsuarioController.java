package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.Repository.IUsuarioRepository;
import br.com.vitorcamposdsilva.todolist.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public UsuarioModel cadastrar(@RequestBody UsuarioModel usuarioModel) {
        var usuarioCriado = this.usuarioRepository.save(usuarioModel);
        return usuarioCriado;
    }
}
