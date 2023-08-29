package com.sotore.store.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "idProveedorFk", referencedColumnName = "idProveedor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore //si dejo esta notacion y en el FecthType.LAZY solo me devuelve el objeto de procutos sin relacion pero puedo hacer que el json que quiera se cree en el controlador con DTO
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "idCategoriaFk" , referencedColumnName = "idCategoria", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categoria categoria;
}
