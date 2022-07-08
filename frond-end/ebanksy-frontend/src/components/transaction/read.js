import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Transaction  from './transaction.js';
export default function TransactionRead() {
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/transactions`)
            .then((response) => {
                console.log(response.data)
                setAPIData(response.data.result);
            })
    }, []);

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
                            return (<Transaction key={data.id} {...data}/>)
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}