package com.crud.start.Service.Impl;

import com.crud.start.DTO.CategoriaDTO;
import com.crud.start.Model.Categoria;
import com.crud.start.Repository.CategoriaRepository;
import com.crud.start.Service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> modelMapper.map(categoria,CategoriaDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public CategoriaDTO getCategoriaById(Integer id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
    @Override
    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaDTO.class);
    }
    @Override
    public CategoriaDTO updateCategoria(Integer id,CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        modelMapper.map(categoriaDTO,categoria);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaDTO.class);
    }
    @Override
    public void deleteById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        categoriaRepository.delete(categoria);
    }

}
