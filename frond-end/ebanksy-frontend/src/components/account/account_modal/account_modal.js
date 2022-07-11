import React, { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Modal from "react-modal";
import Search from '../../search/search'
import './account_modal.css'
import GrayLine from '../../gray_line/gray_line'
import axios from 'axios';
Modal.setAppElement("#root");
const AccountModal = ({
    handleAccountSelected,
    isOpenAccount,
    toggleModalAccount,
    transaction
}) => {
  const accountSelected = (id,accountNum) => {
    transaction.accountId=id;
    transaction.accountNum=accountNum;
    handleAccountSelected(transaction)
  };
  const [APIData, setAPIData] = useState([]);
  const [accounts, setAccounts] = useState([]);
  useEffect(() => {
    axios.get(`http://localhost:8080/api/accounts/getTopTeen`)
        .then((response) => {
            console.log(response.data)
            setAPIData(response.data.result);
        })
}, []);
  const onSearchChange = (searchText) => {
      if(searchText.length > 2 ){
        axios.get(`http://localhost:8080/api/accounts/searchText/${searchText}`)
            .then((response) => {
                setAPIData(response.data.result);
            })
      }
      
    };
  return (
    <div className="container-modal">
      
      <Modal
        isOpen={isOpenAccount}
        onRequestClose={toggleModalAccount}
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
                      <th>Numero de cuenta</th>
                      <th>Cliente</th>
                      <th></th>
                      </tr>
                  </thead>
                  <tbody>
                
                      {APIData.map((account) => {
                              return (
                                <tr>
                                <td>{account.accountNum}</td>
                                <td>{account.customerName}</td>
                                <td><Button variant="primary"  onClick={() => accountSelected(account.id,account.accountNum)}>
                                      Seleccionar
                                    </Button></td>
                                </tr>
                              
                              )
                          })}
                      
                  </tbody>
              </Table>
            </div>
         
            
          <div className='center'>
            <Button variant="danger" className='footer ' onClick={toggleModalAccount}>
                                    Cancelar
                                  </Button>
          </div>
          
        </div>
      
      </Modal>
    </div>
   
  );
};

export default AccountModal;