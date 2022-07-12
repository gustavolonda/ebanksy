import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Account  from '../account.js';
import './read.css';
import Search from '../../search/search';
import Button from 'react-bootstrap/Button'
import { useHistory } from 'react-router-dom';
export const urlGet = `http://localhost:8080/api/accounts/getListActive`;
export const fetchAccounts = async () => {
    try {
      return await axios.get(`${urlGet}`);
    } catch (e) {
      return [];
    }
  };
export default function AccountRead() {
    const history = useHistory();
    const getAll = () => {
        fetchAccounts().then((response) => {
                    setAPIData(response.data.result);
                })
    }
    const onSearchChange = (searchText) => {
        if(searchText.length > 2 ){
            axios.get(`http://localhost:8080/api/accounts/searchText/${searchText}`)
                .then((response) => {
                    setAPIData(response.data.result);
                })
          }
          if(searchText.length === 0 )
            getAll();
      };
    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/accounts/${id}`)
        .then(() => {
            window.location.reload(false);
        })
    }
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        getAll();
    }, []);
    

    return (
        <div className='container-read'>
            <h2 className='title'>Cuentas</h2>
            <div className="row search">
               <div className="col-sm-10">
               <Search onChange={onSearchChange} />

              </div>
              <div className="col-sm-2">
                    <Button className="btn btn-warning buttun-new" onClick={() => history.push(`/account/create`)}>Nuevo</Button>
              </div>
              
            </div>
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
                                <Account key={data.id} {...data} onDelete={onDelete}/>
                            
                            )
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}