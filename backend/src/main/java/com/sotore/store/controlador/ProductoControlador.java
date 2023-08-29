package com.sotore.store.controlador;

import com.google.gson.Gson;
import com.sotore.store.excepcion.RecursoNoEncontradoExcepcion;
import com.sotore.store.modelo.Categoria;
import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import com.sotore.store.servicio.CategoriaServicio;
import com.sotore.store.servicio.ProductoServicio;
import com.sotore.store.servicio.ProveedorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("api/v1/store") //puede que se tenga que configurar diferente si se utiliza el jwt
@CrossOrigin(value = "http://localhost:3000")
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    //si no funciona lo dejamos con IProveedorServicio
    private ProductoServicio productoServicio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        var productos = productoServicio.listarProductos();
        productos.forEach(producto -> {
          logger.info(producto.toString());

        });
        return productos;
    }

    /*@GetMapping("/productos")
    public List<Map<String, Object>> obtenerProductosConProveedor(){
        var productos = productoServicio.listarProductos();
        List<Map<String, Object>> productosConProveedor = new ArrayList<>();

        for (Producto producto : productos) {
            Map<String, Object> productoConProveedor = new HashMap<>();

            productoConProveedor.put("idProducto", producto.getIdProducto());
            productoConProveedor.put("nombreProducto", producto.getNombreProducto());
            productoConProveedor.put("imagenProducto", producto.getImagenProducto());
            productoConProveedor.put("statusProducto", producto.getStatusProducto());

            Proveedor proveedor = producto.getProveedor();
            Map<String, Object> proveedorMap = new HashMap<>();
            proveedorMap.put("idProveedor", proveedor.getIdProveedor()); // Asume que existe un método getIdProveedor() en la clase Proveedor
            proveedorMap.put("nombreProveedor", proveedor.getNombreProveedor()); // Asume que existe un método getNombreProveedor() en la clase Proveedor

            productoConProveedor.put("Proveedor", proveedorMap);

            productosConProveedor.add(productoConProveedor);
        }

        return productosConProveedor;
    }*/

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null) throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+id);
        return  ResponseEntity.ok(producto);

    }

    @PostMapping("/producto/{proveedorId}/{categoriaId}")
    public Producto agregarProducto(
            @PathVariable(value = "proveedorId") Integer proveedorId,
            @PathVariable(value = "categoriaId") Integer categoriaId,
            @RequestParam(value = "imagenProducto", required = false) MultipartFile imagenProducto,
            @RequestParam("producto") String productoJson
    ) {
        logger.info("Producto a agregar: " + productoJson + proveedorId);

        // Convert the JSON string to a Producto object using Jackson or other JSON library
        Producto producto = new Gson().fromJson(productoJson, Producto.class);

        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(proveedorId);

        Categoria categoria = categoriaServicio.buscarCategoriaPorId(categoriaId);
        
        if (proveedor == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el proveedor con el id: " + proveedorId);
        } else if(categoria == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro la categoria con el id: " + categoriaId);
        } else{
            producto.setProveedor(proveedor);
            producto.setCategoria(categoria);

            if(imagenProducto != null && !imagenProducto.isEmpty()){
                Path directorioImagenes = Paths.get("src//main//resources//static/productos");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();


                try {
                    byte[] bytesImg = new byte[0];
                    bytesImg = imagenProducto.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ imagenProducto.getOriginalFilename());
                    //Files.createDirectories(directorioImagenes + "/" + );
                    Files.write(rutaCompleta, bytesImg);
                    producto.setImagenProducto(imagenProducto.getOriginalFilename());
                } catch (IOException e) {
                    throw new RecursoNoEncontradoExcepcion("No se pudo crear la imagen: " + imagenProducto);
                }

            }

            return productoServicio.guardarProducto(producto);
        }
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto productoRecibido){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);
        producto.setNombreProducto(productoRecibido.getNombreProducto());
        producto.setStatusProducto(productoRecibido.getStatusProducto());
        producto.setProveedor(productoRecibido.getProveedor());
        producto.setCategoria(productoRecibido.getCategoria());

        productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Integer id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if(producto == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);

        productoServicio.eliminarProducto(producto);
        //respuesta Json{"eliminado":"true"}

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }

}
