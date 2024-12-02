package com.crud.start.Controller;

import com.crud.start.DTO.ProductoDTO;
import com.crud.start.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProducto();
    }
    @GetMapping("/{id}")
    public ProductoDTO getProductoById(@PathVariable Integer id) {
        return productoService.getProductoById(id);
    }
    @PostMapping
    public ProductoDTO saveProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.saveProducto(productoDTO);
    }

}
