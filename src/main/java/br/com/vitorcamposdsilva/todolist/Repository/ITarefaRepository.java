package br.com.vitorcamposdsilva.todolist.Repository;

import br.com.vitorcamposdsilva.todolist.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITarefaRepository extends JpaRepository<TarefaModel, UUID> {
    List<TarefaModel>findByIdUsuario(UUID idUsuario);
}
