package br.com.fiap.Challenge.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOTTU_PERFIL")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @ManyToMany(mappedBy = "perfis")
    private Set<UsuarioEntity> usuarios = new HashSet<>();
}
