package com.sotore.store.controlador;

import com.sotore.store.excepcion.RecursoNoEncontradoExcepcion;
import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import com.sotore.store.servicio.ProductoServicio;
import com.sotore.store.servicio.ProveedorServicio;
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
    private ProveedorServicio proveedorServicio;

    @PostMapping("/producto/{proveedorId}")
    public Producto agregarProducto(@PathVariable(value = "proveedorId") Integer proveedorId, @RequestBody Producto producto){
        logger.info("Producto a agregar: " + producto + proveedorId);

        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(proveedorId);
        if (proveedor == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el proveedor con el id: " + proveedorId);
        }else {
            producto.setProveedor(proveedor);
            return productoServicio.guardarProducto(producto);
        }

    }

}
