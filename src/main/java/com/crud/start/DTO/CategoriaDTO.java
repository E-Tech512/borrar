package com.crud.start.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombreCategoria;
    private boolean estadoCategoria;
    private LocalDateTime fecharegistroCategoria;
}
