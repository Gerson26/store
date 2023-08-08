package com.sotore.store.controlador;

import com.sotore.store.modelo.Producto;
import com.sotore.store.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/store") //puede que se tenga que configurar diferente si se utiliza el jwt
@CrossOrigin(value = "http://localhost:3000")
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    //si no funciona lo dejamos con IProveedorServicio
    private ProductoServicio productoServicio;

    @PostMapping("/producto")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Proveedor a agregar: " + producto);
        return productoServicio.guardarProducto(producto);
    }
}
