package com.crud.start.Converters;

import com.crud.start.DTO.CategoriaDTO;
import com.crud.start.Model.Categoria;
import com.crud.start.Repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ModelMapper modelMapper;

    public Categoria dtoToEntity(CategoriaDTO dto) {
        Categoria categoria = modelMapper.map(dto, Categoria.class);
        return categoria;
    }
}
