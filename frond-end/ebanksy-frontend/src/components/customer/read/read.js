import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Customer  from '../../customer/customer.js';
import { useHistory } from 'react-router-dom';
import Button from 'react-bootstrap/Button'
import Search from '../../search/search';
import './read.css';
export default function CustomerRead() {
    const [APIData, setAPIData] = useState([]);
    const history = useHistory();
    useEffect(() => {
        axios.get(`http://localhost:8080/api/customers`)
            .then((response) => {
                console.log(response.data)
                setAPIData(response.data.result);
            })
    }, []);
    const onSearchChange = (searchText) => {
        console.info(searchText);
      };
    return (
        
        <div className='containerRead'>
            <h2 className='title'>Clientes</h2>
            <div className="row search">
               <div className="col-sm-10">
               <Search onChange={onSearchChange} />

              </div>
              <div className="col-sm-2">
                    <Button className="btn btn-warning" onClick={() => history.push(`/customer/create`)}>Nuevo</Button>
              </div>
              
          </div>
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
                            return (<Customer key={data.id} {...data}/>)
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}