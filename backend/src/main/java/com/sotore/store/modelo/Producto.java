package com.sotore.store.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;
    String nombreProducto;
    String imagenProducto;
    String statusProducto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idProveedorFk", referencedColumnName = "idProveedor", nullable = false)
    private Proveedor proveedor;
}
