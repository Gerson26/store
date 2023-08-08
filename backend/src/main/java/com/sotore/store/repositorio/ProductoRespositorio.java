package com.sotore.store.repositorio;

import com.sotore.store.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRespositorio extends JpaRepository<Producto, Integer> {
}
