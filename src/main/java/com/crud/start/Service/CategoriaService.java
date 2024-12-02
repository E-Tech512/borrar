package com.crud.start.Service;

import com.crud.start.DTO.CategoriaDTO;
import com.crud.start.Model.Categoria;

import java.util.List;

public interface CategoriaService {
    // Método para devolver una lista de objetos
    List<CategoriaDTO> getAllCategorias();
    // Método para devolver un objeto por su id
    CategoriaDTO getCategoriaById(Integer id);
    // Método para agregar un nuevo objeto
    CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO);
    // Método para modificar un objeto
    CategoriaDTO updateCategoria(Integer id,CategoriaDTO categoriaDTO);
    // Método para eliminar un objeto
    void deleteById(Integer id);
}
