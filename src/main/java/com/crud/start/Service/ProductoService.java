package com.crud.start.Service;

import com.crud.start.DTO.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> getAllProducto();
    ProductoDTO getProductoById(Integer id);
    ProductoDTO saveProducto(ProductoDTO productoDTO);
}
