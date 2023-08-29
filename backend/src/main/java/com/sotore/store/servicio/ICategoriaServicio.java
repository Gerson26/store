package com.sotore.store.servicio;



import com.sotore.store.modelo.Categoria;

import java.util.List;

public interface ICategoriaServicio {

    public List<Categoria> listarCategorias();

    public Categoria guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId(Integer idCategoria);
}
