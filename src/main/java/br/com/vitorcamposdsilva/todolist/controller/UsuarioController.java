package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.model.UsuarioModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioModel usuarioModel) {
        System.out.println(usuarioModel.getNome());
    }
}
