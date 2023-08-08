import React, { useEffect, useState } from 'react'
import { request } from '../../axios_helper';
import Swal from 'sweetalert2';
import $ from 'jquery'; 

export default function ModalAgregarProducto(props) {


    const initialProductoState = {
        nombreProducto:"",
        statusProducto: false,
        idProveedor: ""
    }
    
    //useState para guardar el proveedor
    const [producto, setProducto] = useState(initialProductoState);
    const [proveedores, setProveedores] = useState ([]);

    //inicializar varibles 
    const{nombreProducto,statusProducto,idProveedor} = producto;

    useEffect(() => {
        cargarProveedores();
    },[])

    const onInputChange = (e) => {
        //spread operator ...(expandir los eventos)
        setProducto({...producto,[e.target.name]: e.target.value});

    }

    const onCheckChange = (e) => {
        //spread operator ...(expandir los eventos)
        setProducto({...producto,statusProducto: e.target.checked});
    }


    //Get Proveedores
    const cargarProveedores = async () => {
            await request(
                "GET",
                "/proveedores"
            ).then((response) => {
                setProveedores(response.data);
            }).catch((error) => {
                console.log(error);
            })
        
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        console.log(producto);
        await request(
            "POST",
            "/producto",
            producto
        ).then((response) => {
            console.log(response);
            props.cargarProveedores();
            setProducto(initialProductoState);
            //Proveedor Creado
            Toast.fire({
                icon: 'success',
                title: 'Producto creado correctamente.'
            })
            document.querySelector('.close-modal').click();

        }).catch((error) => {
            console.log(error);
        })
    }

    //Sweet alert configuration
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });

    
    
  return (
    <div className="modal fade" id="modal-default">
            <div className="modal-dialog">
                <div className="modal-content">
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="modal-header">
                            <h4 className="modal-title">Registrar Producto</h4>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        
                        <div className="modal-body">                            
                            <div className="mb-3">
                                <label htmlFor="nombreProducto" className="form-label">Nombre</label>
                                <input type="text" className="form-control" id="nombreProducto" name="nombreProducto" required={true} value={nombreProducto} onChange={(e)=>onInputChange(e)}/>
                            </div>
                            
                            <div class="form-group">
                                <label>Proveedor</label>
                                <select class="form-control" style={{width: '100%'}} id="idProveedor" name="idProveedor" required={true} value={idProveedor} onChange={(e)=>onInputChange(e)}>
                                <option>Selecciona un Proveedor</option>
                                {proveedores.map((proveedor, indice) => (
                                    <option key={indice} value={proveedor.idProveedor}>{proveedor.nombreProveedor}</option>
                                ))}
                                </select>
                            </div>

                            <div class="custom-control custom-switch custom-switch-off-danger custom-switch-on-success">
                                <input type="checkbox" class="custom-control-input" id="statusProducto" name="statusProducto"  onChange={(e)=>onCheckChange(e)}/>
                                <label class="custom-control-label" htmlFor="statusProducto">{producto.statusProducto ? 'Habilitado' : 'Deshabilitado'}</label>
                            </div>
                            
                        </div>
                        <div className="modal-footer justify-content-between">
                            <button type="button" className="btn btn-default close-modal" data-dismiss="modal">Close</button>
                            <button type="submit" className="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
  )
}
