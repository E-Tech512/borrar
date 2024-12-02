package com.crud.start.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private boolean estado;

    @Column(name = "fecha_registro", columnDefinition = "datetime default getdate()")
    private LocalDateTime fecharegistro;

    @PrePersist
    public void prePersist() {
        if (fecharegistro == null) {
            fecharegistro = LocalDateTime.now();
        }
    }

}
