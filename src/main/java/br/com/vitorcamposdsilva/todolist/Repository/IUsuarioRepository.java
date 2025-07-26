package br.com.vitorcamposdsilva.todolist.Repository;

import br.com.vitorcamposdsilva.todolist.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

}
