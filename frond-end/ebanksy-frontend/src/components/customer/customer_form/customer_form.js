import React, { useState } from 'react';
import {Button } from 'react-bootstrap';
import './customer_form.css';
const CustomerForm = ({
  customer,
  handleCustomerOk
}) => {
  const [personBO, setPersonBO]  = useState(() => {
    return {
          name: customer ? customer.name : '',
          gender: customer ? customer.gender : '',
          age: customer ? customer.age : 0,
          idCard: customer ? customer.idCard : '',
          address: customer ? customer.address : '',
          phone:   customer ? customer.phone : ''
    };
  });
  const {  name, gender, age, idCard ,address, phone } = personBO;
  const [customerBO, setCustomerBO] = useState(() => {
    return {
        id: customer ? customer.id : null,
        personBO: personBO,
        password: customer ? customer.password : '',
        status: customer ? customer.status : true
    };
  });
  const {   id, password, status } = customerBO;
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCustomerBO((prevState) => ({
        ...prevState,
        [name]: value
      }));
      setPersonBO((prevState) => ({
        ...prevState,
        [name]: value
      }));
  };

  return (
    <div>
        <div class="row">
          <div class="col align-self-center">
            <form>
              <div className="mb-3">
                  <label  className="form-label status-label"  >Nombre</label>
                  <input type="text" className="form-control" id="name" name="name"  value={name} onChange={handleInputChange}/>
              </div>
              <div className="mb-3">
                  <label  className="form-label gender-label" >Genero</label>
                  <select className="form-select gender" value={gender}  id="gender" name="gender" onChange={handleInputChange} >            
                      <option value="M">Masculino</option>
                      <option value="F">Femenimo</option>
                      <option value="O">Otros</option>
                  </select>
              </div>
              <div className="mb-3">
                  <label  className="form-label status-label"  >Edad</label>
                  <input type="number" className="form-control" id="age" name="age"  value={age} onChange={handleInputChange}/>
              </div>
              <div className="mb-3">
                  <label  className="form-label status-label"  >Identificación</label>
                  <input type="text" className="form-control" id="idCard" name="idCard"  value={idCard} onChange={handleInputChange}/>
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
                  <label  className="form-label status-label" >Estado</label>
                  <select className="form-select status" value={status}  id="status" name="status" onChange={handleInputChange} >            
                      <option value={true}>True</option>
                      <option value={false}>False</option>
                  </select>
              </div>
              <div className="mb-3">
                  <label className="form-label">Telefono</label>
                  <input type="phone" className="form-control" id="phone" name="phone"  value={phone} onChange={handleInputChange}/>
              </div>
              <Button variant="primary"  onClick={() => handleCustomerOk(customerBO,personBO)} className="btn btn-primary">
                Aceptar
              </Button>
            
              
            </form>
          </div>
        
        </div>
      
    </div>
  );
};

export default CustomerForm;