import React from 'react';
import Button from 'react-bootstrap/Button'
import { useHistory } from 'react-router-dom';

const Transaction = ({
    id, 
    transactionDate, 
    customerName,
    accountNum, 
    accountType, 
    initialBalance, 
    value ,
    status,
    transactionType,
    availableBalance
    
}) => {
  const history = useHistory();

  return (
    <tr>
    <td>{transactionDate}</td>
    <td>{customerName}</td>
    <td>{accountNum}</td>
    <td>{accountType === 'S' ? <p>Cuenta de Ahorros</p> : <p>Cuenta Corriente</p>}</td>
    <td>{initialBalance}</td>
    <td>{status  ? <p>True</p> : <p>False</p>}</td>
    <td>{value}</td>
    <td>{availableBalance}</td>
    <td><Button className="btn btn-primary" onClick={() => history.push(`/customer/update?id=${id}`)}>Editar</Button></td>
    <td><Button className="btn btn-danger" onClick={() => history.push(`/customer/delete?id=${id}`)}>Eliminar</Button></td>
    </tr>
  );
};

export default Transaction;