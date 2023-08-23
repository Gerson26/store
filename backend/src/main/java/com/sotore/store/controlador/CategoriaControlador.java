package com.sotore.store.controlador;


import com.sotore.store.modelo.Categoria;
import com.sotore.store.servicio.CategoriaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/store") //puede que se tenga que configurar diferente si se utiliza el jwt
@CrossOrigin(value = "http://localhost:3000")
//@CrossOrigin(value = "*") //puede que se tenga que configurar difernete si se utiliza jwt mas adelante
public class CategoriaControlador {

    //revisar la informacion que se envia en consola
    private static final Logger logger = LoggerFactory.getLogger(ProveedorControlador.class);

    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias(){
        var categorias = categoriaServicio.listarCategorias();
        //para ver lo que trae el metodo en consola
        categorias.forEach((categoria -> logger.info(categoria.toString())));
        return categorias;
    }
}
