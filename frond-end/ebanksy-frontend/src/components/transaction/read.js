import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
export default function TransactionRead() {
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/transactions`)
            .then((response) => {
                console.log(response.data)
                setAPIData(response.data.result);
            })
    }, []);

      
    const setData = (data) => {
        let { id, transactionDate, customerName,accountNum, accountType, initialBalance, value ,status,transactionType,availableBalance} = data;
        localStorage.setItem('id', id);
        localStorage.setItem('transactionDate', transactionDate);
        localStorage.setItem('customerName', customerName);
        localStorage.setItem('accountNum', accountNum);
        localStorage.setItem('accountType', accountType);
        localStorage.setItem('initialBalance', initialBalance);
        localStorage.setItem('value', value);
        localStorage.setItem('status', status);
        localStorage.setItem('transactionType', transactionType);
        localStorage.setItem('availableBalance', availableBalance);
    }

    const getData = () => {
        axios.get(`http://localhost:8080/api/transactions`)
            .then((getData) => {
                setAPIData(getData.data);
            })
    }

    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/transactions/${id}`)
        .then(() => {
            getData();
        })
    }

    return (
        <div className='ms-20'>
            <h2 className='mt-15'>Movimientos</h2>
            <Table striped bordered hover className='float-start '>
                <thead>
                    <tr>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>Numero Cuenta</th>
                    <th>Tipo</th>
                    <th>Saldo Inicial</th>
                    <th>Estado</th>
                    <th>Movimiento</th>
                    <th>Saldo Disponible</th>
                    </tr>
                </thead>
                <tbody>

                    {APIData.map((data) => {
                            return (
                                <tr>
                                <td>{data.transactionDate}</td>
                                <td>{data.customerName}</td>
                                <td>{data.accountNum}</td>
                                <td>{data.accountType === 'S' ? <p>Cuenta de Ahorros</p> : <p>Cuenta Corriente</p>}</td>
                                <td>{data.initialBalance}</td>
                                <td>{data.status  ? <p>True</p> : <p>False</p>}</td>
                                <td>{data.value}</td>
                                <td>{data.availableBalance}</td>
                                <Link to='/account/update'>
                                    <td><Button onClick={() => setData(data)}>Update</Button></td>
                                </Link>
                                <Link to='/account/delete'>
                                    <td><Button onClick={() => onDelete(data.id)}>Delete</Button></td>
                                </Link>
                                
                                </tr>
                            
                            )
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}