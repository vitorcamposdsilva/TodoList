package br.com.vitorcamposdsilva.todolist.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "usuario", unique = true)
    private String nome;
    private String email;
    private  String senha;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
