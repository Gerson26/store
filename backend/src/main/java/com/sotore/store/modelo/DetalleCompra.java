package com.sotore.store.modelo;

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
@Table(name = "detalleCompra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idDetallecompra;   ;
    Integer cantidad;
    Integer precioUnitario;
    Integer total;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "idCompraFk", referencedColumnName = "idCompra", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore //si dejo esta notacion y en el FecthType.LAZY solo me devuelve el objeto de procutos sin relacion pero puedo hacer que el json que quiera se cree en el controlador con DTO
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "idProductoFk", referencedColumnName = "idProducto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore //si dejo esta notacion y en el FecthType.LAZY solo me devuelve el objeto de procutos sin relacion pero puedo hacer que el json que quiera se cree en el controlador con DTO
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "idProveedorFk", referencedColumnName = "idProveedor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore //si dejo esta notacion y en el FecthType.LAZY solo me devuelve el objeto de procutos sin relacion pero puedo hacer que el json que quiera se cree en el controlador con DTO
    private Proveedor proveedor;
}
