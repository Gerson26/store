// import axios from 'axios';
import React, { useEffect, useState } from 'react'

import { request } from '../../axios_helper';
import ModalAgreagarProveedor from './ModalAgreagarProveedor';

export default function ListadoProvedores(props) {


    //useSatate para listar proveedores
    const [proveedores, setProveedores] = useState ([]);

    //useState para guardar el proveedor
    const [proveedor, setProveedor] = useState({
        nombreProveedor:"",
        direccionProveedor:"",
        telefonoProveedor:""
    });

    //inicializar varibles 
    const{nombreProveedor,direccionProveedor,telefonoProveedor} = proveedor;

    
       
    useEffect(() => {
        cargarProveedores();
    },[]);

    const cargarProveedores = async () => {
        // const resultado = await axios.get('http://localhost:8080/api/v1/store/proveedores');
        // console.log("Resultado cargar proveedores");
        // console.log(resultado.data);
        // setProveedores(resultado.data);
        await request(
            "GET",
            "/proveedores"
        ).then((response) => {
            console.log(response);
            setProveedores(response.data);
            console.log(proveedores);

        }).catch((error) => {
            console.log(error);
        })
    }

    const onInputChange = (e) => {
        //spread operator ...(expandir los eventos)
        setProveedor({...proveedor,[e.target.name]: e.target.value});

    }

    const onSubmit = async (e) => {
        e.preventDefault();
        console.log(proveedor);
        await request(
            "POST",
            "/proveedor",
            proveedor
        ).then((response) => {
            console.log(response);
            cargarProveedores();
            setProveedor({
                nombreProveedor:"",
                direccionProveedor:"",
                telefonoProveedor:""
            })

        }).catch((error) => {
            console.log(error);
        })
    }

  return (

    <div>
        <section className="content-header">
        <div className="container-fluid">
            <div className="row mb-2">
            <div className="col-sm-6">
                <h1>{props.title}</h1>
            </div>
            {/* <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                <li className="breadcrumb-item"><a href="#">Home</a></li>
                <li className="breadcrumb-item active">DataTables</li>
                </ol>
            </div> */}
            </div>
        </div>
        </section>
        <section className="content">
            <div className="container-fluid">
                <div className="row">
                <div className="col-12">
                    <div className="card">
                    <div className="card-header" >
                        <div className='row'>
                            <div className='col-md-6'>
                                <h3 className="card-title">Listado de Proveedores</h3>
                            </div>
                            <div className='col-md-6 d-flex justify-content-end' >
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-default">
                                    <i class="fas fa-solid fa-plus"></i> Agregar Proveedor
                                </button>
                            </div>

                        </div>
                        
                    </div>
                    {/* /.card-header */}
                    <div className="card-body">
                        <table id="example1" className="table table-bordered table-striped">
                        <thead>
                            <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Fecha de registro</th>
                            <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                        {proveedores.map((proveedor, indice) => (
                            <tr key={indice}>
                                <td>{proveedor.idProveedor}</td>
                                <td>{proveedor.nombreProveedor}</td>
                                <td>{proveedor.direccionProveedor}</td>
                                <td>{proveedor.telefonoProveedor}</td>
                                <td>{proveedor.fechaRegistroProveedor}</td>
                                <td>X</td>
                            </tr>
                        ))}
                            
                        </tbody>
                        </table>
                    </div>
                    {/* /.card-body */}
                    </div>
                    {/* /.card */}
                </div>
                {/* /.col */}
                </div>
                {/* /.row */}
            </div>
            {/* /.container-fluid */}
        </section>

        {/* Modal Agregar Nuevo proveedor */}
        <ModalAgreagarProveedor onInputChange={onInputChange} onSubmit={onSubmit} proveedor={proveedor}/>

    </div>
  )
}
