package com.crud.start.DTO;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombreProducto;
    private double precioProducto;
    private CategoriaDTO categoriaProducto;
}
