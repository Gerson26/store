package com.sotore.store.controlador;


import com.sotore.store.excepcion.RecursoNoEncontradoExcepcion;
import com.sotore.store.modelo.Categoria;
import com.sotore.store.servicio.CategoriaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/categoria")
    public Categoria agregarCategoria(@RequestBody Categoria categoria){
        logger.info("Categoria a Agregar: " + categoria);
        return categoriaServicio.guardarCategoria(categoria);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Integer id){
        Categoria categoria = categoriaServicio.buscarCategoriaPorId(id);
        if (categoria == null) throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+id);
        return  ResponseEntity.ok(categoria);

    }
    @PutMapping("/categoria/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaRecibida){
        Categoria categoria = categoriaServicio.buscarCategoriaPorId(id);
        if (categoria == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);
        categoria.setNombreCategoria(categoriaRecibida.getNombreCategoria());
        categoria.setDescripcionCategoria(categoriaRecibida.getDescripcionCategoria());

        categoriaServicio.guardarCategoria(categoria);
        return ResponseEntity.ok(categoria);
    }

}
