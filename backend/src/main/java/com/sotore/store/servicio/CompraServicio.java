package com.sotore.store.servicio;

import com.sotore.store.modelo.Categoria;
import com.sotore.store.modelo.Compra;
import com.sotore.store.repositorio.CompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompraServicio implements ICompraServicio{

    @Autowired
    private CompraRepositorio compraRepositorio;

    @Override
    public List<Compra> listarCompras(){
        return compraRepositorio.findAll();
    }
}
