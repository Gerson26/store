package com.sotore.store.controlador;

import com.sotore.store.modelo.Proveedor;
import com.sotore.store.servicio.ProveedorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
}
