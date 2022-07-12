import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Customer  from '../../customer/customer.js';
import { useHistory } from 'react-router-dom';
import Button from 'react-bootstrap/Button'
import Search from '../../search/search';
import './read.css';
export const urlGet = `http://localhost:8080/api/customers/getListActive`;
export const fetchCustomers = async () => {
    try {
      return await axios.get(`${urlGet}`);
    } catch (e) {
      return [];
    }
  };
export default function CustomerRead() {
    const [APIData, setAPIData] = useState([]);
    const history = useHistory();
    const getAll = () => {
        fetchCustomers().then((response) => {
            console.log(response.data)
            setAPIData(response.data.result);
        })
    }
    useEffect(() => {
        getAll();
    }, []);
    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/customers/${id}`)
        .then(() => {
             history.push('/');
        })
    }
    const onSearchChange = (searchText) => {
        if(searchText.length > 2 ){
            axios.get(`http://localhost:8080/api/customers/searchText/${searchText}`)
                .then((response) => {
                    setAPIData(response.data.result);
                })
          }
          if(searchText.length === 0 )
            getAll();
      };
    return (
        
        <div className='container-read'>
            <h2 className='title'>Clientes</h2>
            <div className="row search">
               <div className="col-sm-10">
               <Search onChange={onSearchChange} />

              </div>
              <div className="col-sm-2">
                    <Button className="btn btn-warning buttun-new" onClick={() => history.push(`/customer/create`)}>Nuevo</Button>
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
                            return (<Customer key={data.id} {...data} onDelete={onDelete}/>)
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}