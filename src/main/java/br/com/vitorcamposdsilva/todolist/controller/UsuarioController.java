package br.com.vitorcamposdsilva.todolist.controller;

import br.com.vitorcamposdsilva.todolist.Repository.IUsuarioRepository;
import br.com.vitorcamposdsilva.todolist.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody UsuarioModel usuarioModel) {
        var usuarioExistente = this.usuarioRepository.findByNome(usuarioModel.getNome());

        if (usuarioExistente != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado!");
        }

        var usuarioCriado = this.usuarioRepository.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}
