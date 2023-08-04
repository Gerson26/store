package com.sotore.store.controlador;

import com.sotore.store.excepcion.RecursoNoEncontradoExcepcion;
import com.sotore.store.modelo.Proveedor;
import com.sotore.store.servicio.ProveedorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/store") //puede que se tenga que configurar diferente si se utiliza el jwt
@CrossOrigin(value = "http://localhost:3000")
//@CrossOrigin(value = "*") //puede que se tenga que configurar difernete si se utiliza jwt mas adelante
public class ProveedorControlador {
    //revisar la informacion que se envia en consola
    private static final Logger logger = LoggerFactory.getLogger(ProveedorControlador.class);

    @Autowired
    //si no funciona lo dejamos con IProveedorServicio
    private ProveedorServicio proveedorServicio;

    @GetMapping("prueba")
    public String prueba(){
        return ("Esto es una prueba");
    }

    @GetMapping("/proveedores")
    public List<Proveedor> obtenerProveedores(){
        var proveedores = proveedorServicio.listarProveedores();
        //para ver lo que trae el metodo en consola
        proveedores.forEach((proveedor -> logger.info(proveedor.toString())));
        return proveedores;
    }

    @PostMapping("/proveedor")
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor){
        Date fechaRegistroProveedor = new Date();
        proveedor.setFechaRegistroProveedor(fechaRegistroProveedor);
        logger.info("Proveedor a agregar: " + proveedor);
        return proveedorServicio.guardarProveedor(proveedor);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Integer id){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);
        if (proveedor == null) throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+id);
        return  ResponseEntity.ok(proveedor);

    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedorRecibido){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);
        if (proveedor == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);
        proveedor.setNombreProveedor(proveedorRecibido.getNombreProveedor());
        proveedor.setDireccionProveedor(proveedorRecibido.getDireccionProveedor());
        proveedor.setTelefonoProveedor(proveedorRecibido.getTelefonoProveedor());

        proveedorServicio.guardarProveedor(proveedor);
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProveedor(@PathVariable Integer id){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);
        if(proveedor == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);

        proveedorServicio.eliminarProveedor(proveedor);
        //respuesta Json{"eliminado":"true"}

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }
}
