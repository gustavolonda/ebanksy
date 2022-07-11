import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Transaction  from '../../transaction/transaction.js';
import Search from '../../search/search';
import Button from 'react-bootstrap/Button'
import { useHistory } from 'react-router-dom';

export default function TransactionRead() {
    const [APIData, setAPIData] = useState([]);
    const history = useHistory();

    useEffect(() => {
        getAll();
    }, []);
    const getAll = () => {
        axios.get(`http://localhost:8080/api/transactions/getListActive`)
        .then((response) => {
            console.log(response.data)
            setAPIData(response.data.result);
        })
    }
    const onSearchChange = (searchText) => {
        if(searchText.length > 2 ){
            axios.get(`http://localhost:8080/api/transactions/searchText/${searchText}`)
                .then((response) => {
                    setAPIData(response.data.result);
                })
          }
          if(searchText.length === 0 )
            getAll();
      };
    const onDelete = (id) => {
        axios.delete(`http://localhost:8080/api/transactions/${id}`)
        .then(() => {
            window.location.reload(false);
        })
    }
    return (
        <div className='container-read'>
            <h2 className='title'>Movimientos</h2>
            <div className="row search">
               <div className="col-sm-10">
               <Search onChange={onSearchChange} />

              </div>
              <div className="col-sm-2">
                    <Button className="btn btn-warning buttun-new" onClick={() => history.push(`/transaction/create`)}>Nuevo</Button>
              </div>
              
          </div>
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
                            return (<Transaction key={data.id} {...data} onDelete={onDelete}/>)
                        })}
                    
                </tbody>
            </Table>
        </div>
    )
}