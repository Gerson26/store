package com.sotore.store.repositorio;


import com.sotore.store.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Integer> {
}
