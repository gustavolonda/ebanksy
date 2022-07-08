import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Account  from './account.js';
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
                                <Account key={data.id} {...data}/>
                            
                            )
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}