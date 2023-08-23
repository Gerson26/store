package com.sotore.store.servicio;

import com.sotore.store.modelo.Categoria;
import com.sotore.store.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicio implements ICategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public List<Categoria> listarCategorias(){
        return categoriaRepositorio.findAll();
    }
}
