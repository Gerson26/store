package com.sotore.store.controlador;



import com.sotore.store.modelo.Compra;
import com.sotore.store.servicio.CompraServicio;
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
public class CompraControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);
    @Autowired
    private CompraServicio compraServicio;

    @GetMapping("/compras")
    public List<Compra> obtenerCompras(){
        var compras = compraServicio.listarCompras();
        compras.forEach(compra -> {
            logger.info(compra.toString());

        });
        return compras;
    }
}
