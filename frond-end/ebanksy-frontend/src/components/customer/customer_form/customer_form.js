import React, { useState } from 'react';
import {Button } from 'react-bootstrap';
import './customer_form.css';
const CustomerForm = (props) => {
  const [customer, setCustomer] = useState(() => {
    return {
        id: props.customer ? props.customer.id : '',
        name: props.customer ? props.customer.name : '',
        address: props.customer ? props.customer.address : '',
        password: props.customer ? props.customer.password : '',
        status: props.customer ? props.customer.status : ''
    };
  });
  const {   id, name, address, password, status } = customer;
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCustomer((prevState) => ({
        ...prevState,
        [name]: value
      }));
  };

  return (
    <div className="main-form container-form">
      <form>
        <div className="mb-3">
            <label >Nombre</label>
            <input type="text" className="form-control" id="name" name="name"  value={name} onChange={handleInputChange}/>
        </div>
        <div className="mb-3">
            <label className="form-label">Dirección</label>
            <input type="text" className="form-control" id="address" name="address"  value={address} onChange={handleInputChange}/>
        </div>
        <div className="mb-3">
            <label className="form-label">Contraseña</label>
            <input type="text" className="form-control" id="password" name="password"  value={password} onChange={handleInputChange}/>
        </div>
        <div className="mb-3">
            <label  className="form-label" >Estado</label>
            <select value={status}  id="status" name="status" onChange={handleInputChange} >            
                <option value={true}>True</option>
                <option value={false}>False</option>
             </select>
        </div>
        <Button type="submit" className="btn btn-primary">
          Aceptar
        </Button>
      
        
      </form>
    </div>
  );
};

export default CustomerForm;