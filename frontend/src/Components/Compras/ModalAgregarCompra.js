import React, { useState, useEffect } from 'react'
import { request } from '../../axios_helper';
import Swal from 'sweetalert2';




export default function ModalAgregarCompra(props) {

    const LOCAL_STORAGE_KEY = 'detallesCompra.productos';

    const initialCompraState = {
        idProveedor: "",
        monto: ""
    }

    const initialDetallecompra = {
        cantidad: "",
        precio_unitario: "",
        total: "",
        id_compra: "",
        id_producto: "",
        id_proveedor: ""
    }

    //useState para guardar el proveedor
    // const [producto, setProducto] = useState(initialProductoState);
    const [proveedores, setProveedores] = useState([]);
    const [imagenProducto, setImagenProducto] = useState(null);
    const [categorias, setCategorias] = useState([]);

    const [compra, setCompra] = useState(initialCompraState);
    //este sera un array de objetos
    const [detalleCompra, setDetalleCompra] = useState([initialDetallecompra]);

    // //useState para guardar el proveedor
    // const [proveedor, setProveedor] = useState(initialProveedorState);

    // //inicializar varibles Compra
    const { idProveedor, monto } = compra;

    //inicializar varibles  detalle compra
    const { cantidad, precio_unitario, id_proveedor,total } = detalleCompra;

    useEffect(() => {
        cargarProveedores();
    }, [])

    useEffect(() => {
        const storedDetalleCompra = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY))
        if (storedDetalleCompra) setDetalleCompra(storedDetalleCompra)
    }, [])

    useEffect(() => {
        localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(detalleCompra))
    }, [detalleCompra])

    const handleAddItems = () => {

        const newDetalleCompraItem = {
            ...initialDetallecompra,
            id_proveedor: compra.idProveedor
        };
    
        // Agregamos el nuevo elemento al estado detalleCompra
        setDetalleCompra([...detalleCompra, newDetalleCompraItem]);
       
    };

    const handleInputChange = (index, field, value) => {
        let total;//se crea la variable para contener el total
        const newDetallecompra = [...detalleCompra]; // se crea el nuevo json a setear
        newDetallecompra[index][field] = value; // index es el indice el objeto en el array, field es el nombre del campo, y value el valor del contenido               

        //validacion para saber por que se va a multiolicar
        if(field == 'cantidad'){
            total = value * detalleCompra[index].precio_unitario;
            
        }else if(field == 'precio_unitario'){
            total = value * detalleCompra[index].cantidad;           
        }
        //se agrega el valor del total
        newDetallecompra[index].total = total;
        //se setea al esdtado
        setDetalleCompra(newDetallecompra);
       
    };

    const handleDeleteItems = (index) => {
        const newDetallecompra = [...detalleCompra];
        newDetallecompra.splice(index, 1);
        setDetalleCompra(newDetallecompra);
    };

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


    const onInputChange = (e) => {
        //spread operator ...(expandir los eventos)
        setCompra({ ...compra, [e.target.name]: e.target.value });
        actualizarIdProveedorEnDetallecompra(e.target.value);
        
    }

    const actualizarIdProveedorEnDetallecompra = (newIdProveedor) => {
        const updatedDetalleCompra = detalleCompra.map(detalle => ({
            ...detalle,
            id_proveedor: newIdProveedor
        }));
        // Actualiza el estado de detalleCompra con los valores actualizados
        setDetalleCompra(updatedDetalleCompra);
    }
    const onSubmit = async (e) => {
        e.preventDefault();
        // console.log(proveedor);
        // await request(
        //     "POST",
        //     "/proveedor",
        //     proveedor
        // ).then((response) => {
        //     console.log(response);
        //     props.cargarProveedores();
        //     setProveedor(initialProveedorState);
        //     //Proveedor Creado
        //     Toast.fire({
        //         icon: 'success',
        //         title: 'Proveedor creado correctamente.'
        //     })
        //     document.querySelector('.close-modal').click();

        // }).catch((error) => {
        //     console.log(error);
        // })
    }

    // //Sweet alert configuration
    // const Toast = Swal.mixin({
    //     toast: true,
    //     position: 'top-end',
    //     showConfirmButton: false,
    //     timer: 3000
    // });

    return (
        <div className="modal fade" id="modal-add-compra">
            <div className="modal-dialog modal-lg">
                <div className="modal-content">
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="modal-header">
                            <h4 className="modal-title">Registrar Compra</h4>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>

                        <div className="modal-body">

                            <div class="form-group">
                                <label>Proveedor</label>
                                <select class="form-control" style={{ width: '100%' }} id="idProveedor" name="idProveedor" required={true} value={idProveedor} onChange={(e) => onInputChange(e)}>
                                    <option>Selecciona un Proveedor</option>
                                    {proveedores.map((proveedor, indice) => (
                                        <option key={indice} value={proveedor.idProveedor}>{proveedor.nombreProveedor}</option>
                                    ))}
                                </select>
                            </div>

                            <div className='mb-3 row'>
                                <div className='col-md-3 col-sm-12'>
                                    <button type="button" class="btn bg-gradient-success" onClick={handleAddItems} style={{ borderRadius: '50%' }}><i class="fas fa-plus-circle"></i></button>
                                </div>
                            </div>

                            {detalleCompra.map((item, index) => (
                                <div key={index}>
                                    <div className='row'>
                                        <div className="mb-3 col-sm-2">
                                            <label htmlFor="cantidad" className="form-label">Cantidad</label>
                                            <input type="number" className="form-control" id="cantidad" name="cantidad" min="1" required={true} value={item.cantidad} onChange={(e) => handleInputChange(index, 'cantidad', e.target.value)} />
                                        </div>

                                        <div className="mb-3 col-sm-2">
                                            <label htmlFor="precio_unitario" className="form-label">Precio</label>
                                            <input type="number" className="form-control" id="precio_unitario" name="precio_unitario" step="0.01" min="1" required={true} value={item.precio_unitario} onChange={(e) => handleInputChange(index, 'precio_unitario', e.target.value)} />
                                        </div>

                                        <div className="mb-3 col-sm-2">
                                            <label htmlFor="total" className="form-label">Total</label>
                                            <input type="number" className="form-control" id="total" name="total" step="0.01" min="1" required={true} value={item.total} onChange={(e) => handleInputChange(index, 'total', e.target.value)} />
                                        </div>

                                        <div className="mb-3 col-sm-2">
                                            <button className='btn btn-sm btn-danger' style={{ marginTop: '40px' }} onClick={() => handleDeleteItems(index)}><i class="fas fa-trash-alt"></i></button>
                                        </div>


                                    </div>

                                </div>
                            ))}

                            <pre>{JSON.stringify(detalleCompra, null, 2)}</pre>

                            {/* <div className="mb-3">
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
                            </div> */}

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
