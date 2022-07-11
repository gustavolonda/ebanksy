import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Modal from "react-modal";
import Search from '../.././search/search'
import './customer_modal.css'
import GrayLine from '../../gray_line/gray_line'
import axios from 'axios';
Modal.setAppElement("#root");
const CustomerModal = ({
    handleCustomerSelected,
    isOpen,
    toggleModal,
    account
}) => {
  const customerSelected = (id,name) => {
    account.customerId=id;
    account.customerName=name;
    handleCustomerSelected(account)
  };
  const [APIData, setAPIData] = useState([]);
  const [customers, setCustomers] = useState([]);
  useEffect(() => {
    axios.get(`http://localhost:8080/api/customers/getTopTeen`)
        .then((response) => {
            console.log(response.data)
            setAPIData(response.data.result);
        })
}, []);
  const onSearchChange = (searchText) => {
      if(searchText.length > 2 ){
        axios.get(`http://localhost:8080/api/customers/searchText/${searchText}`)
            .then((response) => {
                setAPIData(response.data.result);
            })
      }
      
    };
  return (
    <div className="customer-modal">
      
      <Modal
        isOpen={isOpen}
        onRequestClose={toggleModal}
        contentLabel="Seleccionar Cliente"
      >
        <div>

        
            <h4>Seleccionar Cliente</h4>
            <GrayLine/>
            <Search className ="" onChange={onSearchChange} />
            <div className='customer-table'>
            <Table  >
                  <thead>
                      <tr>
                      <th>Nombre</th>
                      <th></th>
                      </tr>
                  </thead>
                  <tbody>
                
                      {APIData.map((customer) => {
                              return (
                                <tr>
                                <td>{customer.name}</td>
                                <td><Button variant="primary"  onClick={() => customerSelected(customer.id,customer.name)}>
                                      Seleccionar
                                    </Button></td>
                                </tr>
                              
                              )
                          })}
                      
                  </tbody>
              </Table>
            </div>
         
            
          <div className='center'>
            <Button variant="danger" className='footer ' onClick={toggleModal}>
                                    Cancelar
                                  </Button>
          </div>
          
        </div>
      
      </Modal>
    </div>
   
  );
};

export default CustomerModal;