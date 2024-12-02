package com.crud.start.Service.Impl;

import com.crud.start.DTO.ProductoDTO;
import com.crud.start.Model.Categoria;
import com.crud.start.Model.Producto;
import com.crud.start.Repository.CategoriaRepository;
import com.crud.start.Repository.ProductoRepository;
import com.crud.start.Service.ProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductoDTO> getAllProducto(){
        return productoRepository.findAll().stream()
                .map(producto -> modelMapper.map(producto,ProductoDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ProductoDTO getProductoById(Integer id){
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return modelMapper.map(producto,ProductoDTO.class);
    }
    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO){
        Producto producto = modelMapper.map(productoDTO,Producto.class);
        if(productoDTO.getCategoriaProducto() != null && productoDTO.getCategoriaProducto().getIdCategoria() != null){
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaProducto().getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
            producto.setCategoria(categoria);
        }
        Producto saveProducto = productoRepository.save(producto);
        return modelMapper.map(saveProducto,ProductoDTO.class);

    }


}
