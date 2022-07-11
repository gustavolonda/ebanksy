import React from 'react';
import Button from 'react-bootstrap/Button'
import { useHistory } from 'react-router-dom';

const Account = ({
    id, 
    accountNum, 
    accountType, 
    initialBalance, 
    status,
    customerName,
    availableBalance,
    onDelete
}) => {
  const history = useHistory();

  return (
    <tr>
    <td>{accountNum}</td>
    <td>{accountType === 'S' ? <p>Cuenta de Ahorros</p> : <p>Cuenta Corriente</p>}</td>
    <td>{initialBalance}</td>
    <td>{status  ? <p>True</p> : <p>False</p>}</td>
    <td>{customerName}</td>
    <td>{availableBalance}</td>
    <td>{status  ? <p>True</p> : <p>False</p>}</td>
    <td><Button className="btn btn-primary" onClick={() => history.push(`/account/update/${id}`)} >Editar</Button></td>
    <td><Button className="btn btn-danger" onClick={() => onDelete(id)}>Eliminar</Button></td>
    </tr>
  );
};

export default Account;