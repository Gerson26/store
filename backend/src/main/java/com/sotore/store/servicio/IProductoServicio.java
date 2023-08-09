package com.sotore.store.servicio;

import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;

import java.util.List;

public interface IProductoServicio {

    //funciona como metodo de guardado y de actualizar dependiendo si el id viene nulo
    public Producto guardarProducto(Producto producto);

    public List<Producto> listarProductos();
}
