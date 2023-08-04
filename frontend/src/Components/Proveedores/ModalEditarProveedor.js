import React, {  useState, useEffect  } from 'react'
import { request } from '../../axios_helper';
import Swal from 'sweetalert2';
import $ from 'jquery'; 




export default function ModalEditarProveedor(props) {


    // const initialProveedorState = {
    //     nombreProveedor:"",
    //     direccionProveedor:"",
    //     telefonoProveedor:""
    // }
    
    //useState para guardar el proveedor
    const [proveedor, setProveedor] = useState([]);

    useEffect(() => {
        setProveedor(props.proveedor);
    },[props.proveedor]);

    //inicializar varibles 
    const{idProveedor,nombreProveedor,direccionProveedor,telefonoProveedor} = proveedor;

    const onInputChange = (e) => {
        //spread operator ...(expandir los eventos)
        setProveedor({...proveedor,[e.target.name]: e.target.value});

    }


    const onSubmit = async (e,id) => {
        e.preventDefault();
       
        await request(
            "PUT",
            `proveedor/${id}`,
            proveedor
        ).then((response) => {
            console.log(response);
            props.cargarProveedores();
            setProveedor({
                nombreProveedor:"",
                direccionProveedor:"",
                telefonoProveedor:""
            });
            //Proveedor Creado
            Toast.fire({
                icon: 'success',
                title: 'Proveedor editado correctamente.'
            })
            document.querySelector('.close-modal-d').click();

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
    <div className="modal fade" id="modal-edit-proveedor">
            <div className="modal-dialog">
                <div className="modal-content">
                    <form onSubmit={(e) => onSubmit(e,idProveedor)}>
                        <div className="modal-header">
                            <h4 className="modal-title">Editar Proveedor</h4>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        
                        <div className="modal-body">                            
                            <div className="mb-3">
                                <label htmlFor="nombreProveedor" className="form-label">Nombre</label>
                                <input type="text" className="form-control" id="nombreProveedor" name="nombreProveedor" required={true} value={nombreProveedor} onChange={(e)=>onInputChange(e)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="direccionProveedor" className="form-label">Dirección</label>
                                <input type="text" className="form-control" id="direccionProveedor" name="direccionProveedor" value={direccionProveedor} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="telefonoProveedor" className="form-label">Teléfono</label>
                                <input type="number" step="any" className="form-control" id="telefonoProveedor" name="telefonoProveedor" value={telefonoProveedor} onChange={(e) => onInputChange(e)}/>
                            </div>
                            
                        </div>
                        <div className="modal-footer justify-content-between">
                            <button type="button" className="btn btn-default close-modal-d" data-dismiss="modal">Close</button>
                            <button type="submit" className="btn btn-primary">Actualizar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
  )
}
