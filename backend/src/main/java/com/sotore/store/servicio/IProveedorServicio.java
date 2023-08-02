package com.sotore.store.servicio;

import com.sotore.store.modelo.Proveedor;

import java.util.List;

public interface IProveedorServicio {

    public List<Proveedor> listarProveedores();

    public Proveedor buscarProveedorPorId();

    //funciona como metodo de guardado y de actualizar dependiendo si el id viene nulo
    public Proveedor guardarProveedor(Proveedor proveedor);

    public void eliminarProveedor(Proveedor proveedor);


}
