import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
export default function CustomerRead() {
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/customers`)
            .then((response) => {
                console.log(response.data)
                setAPIData(response.data.result);
            })
    }, []);

    const setData = (data) => {
        let { id, name, address, password, status } = data;
        localStorage.setItem('id', id);
        localStorage.setItem('name', name);
        localStorage.setItem('address', address);
        localStorage.setItem('password', password);
        localStorage.setItem('status', status);

    }

    const getData = () => {
        axios.get(`http://localhost:8080/api/customers`)
            .then((getData) => {
                setAPIData(getData.data);
            })
    }

    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/customers/${id}`)
        .then(() => {
            getData();
        })
    }

    return (
        <div className='ms-20'>
            <h2 className='mt-15'>Clientes</h2>
            <Table striped bordered hover className='float-start '>
                <thead>
                    <tr>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Contraseña</th>
                    <th>Estado</th>
                    </tr>
                </thead>
                <tbody>

                    {APIData.map((data) => {
                            return (
                                <tr>
                                <td>{data.name}</td>
                                <td>{data.address}</td>
                                <td>{data.password}</td>
                                <td>{data.status  ? <p>True</p> : <p>False</p>}</td>
                                <Link to='/customer/update'>
                                    <td><Button onClick={() => setData(data)}>Update</Button></td>
                                </Link>
                                <Link to='/customer/delete'>
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