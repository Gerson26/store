import React, { useState } from 'react'

export default function ModalAgreagarProveedor(props) {

    
  return (
    <div className="modal fade" id="modal-default">
            <div className="modal-dialog">
                <div className="modal-content">
                    <form onSubmit={(e) => props.onSubmit(e)}>
                        <div className="modal-header">
                            <h4 className="modal-title">Registrar Proveedor</h4>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div className="modal-body">                            
                            <div className="mb-3">
                                <label htmlFor="nombreProveedor" className="form-label">Nombre</label>
                                <input type="text" className="form-control" id="nombreProveedor" name="nombreProveedor" required={true} value={props.nombreProveedor} onChange={(e)=>props.onInputChange(e)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="direccionProveedor" className="form-label">Dirección</label>
                                <input type="text" className="form-control" id="direccionProveedor" name="direccionProveedor" value={props.direccionProveedor} onChange={(e) => props.onInputChange(e)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="telefonoProveedor" className="form-label">Teléfono</label>
                                <input type="number" step="any" className="form-control" id="telefonoProveedor" name="telefonoProveedor" value={props.telefonoProveedor} onChange={(e) => props.onInputChange(e)}/>
                            </div>
                            {/* <div className="mb-3">
                                <label htmlFor="fechaRegistroProveedor" className="form-label">Teléfono</label>
                                <input type="date" className="form-control" id="fechaRegistroProveedor" name="fechaRegistroProveedor" value={fechaRegistroProveedor} onChange={(e) => onInputChange(e)}/>
                            </div>                            
                             */}
                        </div>
                        <div className="modal-footer justify-content-between">
                            <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" className="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
  )
}
