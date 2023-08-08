package com.sotore.store.servicio;

import com.sotore.store.modelo.Producto;

public interface IProductoServicio {

    //funciona como metodo de guardado y de actualizar dependiendo si el id viene nulo
    public Producto guardarProducto(Producto producto);
}
