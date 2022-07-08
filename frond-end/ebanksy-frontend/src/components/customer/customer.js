import React from 'react';
import Button from 'react-bootstrap/Button'
import { useHistory } from 'react-router-dom';

const Customer = ({
    id, 
    name, 
    address, 
    password, 
    status
    
}) => {
  const history = useHistory();

  return (
    <tr>
    <td>{name}</td>
    <td>{address}</td>
    <td>{password}</td>
    <td>{status  ? <p>True</p> : <p>False</p>}</td>
    <td><Button className="btn btn-primary" onClick={() => history.push(`/customer/update?id=${id}`)}>Editar</Button></td>
    <td><Button className="btn btn-danger" onClick={() => history.push(`/customer/delete?id=${id}`)}>Eliminar</Button></td>
    </tr>
  );
};

export default Customer;