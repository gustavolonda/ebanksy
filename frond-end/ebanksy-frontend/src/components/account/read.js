import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
export default function AccountRead() {
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/accounts`)
            .then((response) => {
                console.log(response.data)
                setAPIData(response.data.result);
            })
    }, []);
    const setData = (data) => {
        let { id, accountNum, accountType, initialBalance, status,customerName,availableBalance} = data;
        localStorage.setItem('id', id);
        localStorage.setItem('accountNum', accountNum);
        localStorage.setItem('accountType', accountType);
        localStorage.setItem('initialBalance', initialBalance);
        localStorage.setItem('status', status);
        localStorage.setItem('customerName', customerName);
        localStorage.setItem('availableBalance', availableBalance);

    }

    const getData = () => {
        axios.get(`http://localhost:8080/api/accounts`)
            .then((getData) => {
                setAPIData(getData.data);
            })
    }

    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/accounts/${id}`)
        .then(() => {
            getData();
        })
    }

    return (
        <div className='ms-20'>
            <h2 className='mt-15'>Cuentas</h2>
            <Table striped bordered hover className='float-start '>
                <thead>
                    <tr>
                    <th>Numero de cuenta</th>
                    <th>Tipo de cuenta</th>
                    <th>Saldo Inicial</th>
                    <th>Estado</th>
                    <th>Cliente</th>
                    <th>Saldo Disponible</th>
                    </tr>
                </thead>
                <tbody>

                    {APIData.map((data) => {
                            return (
                                <tr>
                                <td>{data.accountNum}</td>
                                <td>{data.accountType === 'S' ? <p>Cuenta de Ahorros</p> : <p>Cuenta Corriente</p>}</td>
                                <td>{data.initialBalance}</td>
                                <td>{data.status  ? <p>True</p> : <p>False</p>}</td>
                                <td>{data.customerName}</td>
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