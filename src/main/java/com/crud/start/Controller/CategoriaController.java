package com.crud.start.Controller;

import com.crud.start.DTO.CategoriaDTO;
import com.crud.start.Model.Categoria;
import com.crud.start.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("http://localhost:4200")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }
    @GetMapping("/{id}")
    public CategoriaDTO getCategoriaById(@PathVariable Integer id) {
        return categoriaService.getCategoriaById(id);
    }
    @PostMapping
    public CategoriaDTO saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.saveCategoria(categoriaDTO);
    }
    @PutMapping("/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoria(id,categoriaDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        categoriaService.deleteById(id);
    }
}
