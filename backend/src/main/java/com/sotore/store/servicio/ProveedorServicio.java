package com.sotore.store.servicio;

import com.sotore.store.modelo.Proveedor;
import com.sotore.store.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServicio implements IProveedorServicio{

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;


    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepositorio.findAll();
    }

    @Override
    public Proveedor buscarProveedorPorId() {
        return null;
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    @Override
    public void eliminarProveedor(Proveedor proveedor) {

    }
}
