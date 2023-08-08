package com.sotore.store.servicio;

import com.sotore.store.modelo.Producto;
import com.sotore.store.repositorio.ProductoRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRespositorio productoRespositorio;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRespositorio.save(producto);
    }
}
