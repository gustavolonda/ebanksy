import React, { useState } from 'react';
import {Button } from 'react-bootstrap';
import './account_form.css';
import CustomerModal from '../../customer/customer_modal/customer_modal'

const AccountForm = ({
  account,
  handleOK
}) => {
  const [accountBO, setAccountBO] = useState(() => {
    return {
        id: account ? account.id : '',
        accountId:  account ? account.accountId : '',
        accountNum: account ? account.accountId : '',
        accountType: account ? account.accountType : '',
        initialBalance: account ? account.initialBalance : '',
        status: account ? account.status : '',
        customerId: account ? account.customerId : '',
        customerName: account ? account.customerName : '',
        availableBalance: account ? account.availableBalance : '',
        customerBO: null

    };
  });
  const { id,
          accountId, 
          accountNum, 
          accountType, 
          initialBalance, 
          status, 
          customerName,
          customerId, 
          availableBalance } = accountBO;
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setAccountBO((prevState) => ({
        ...prevState,
        [name]: value
      }));
  };
  const handleCustomerSelected = (accountBO) => {
      setIsOpen(false);
  };
  const [isOpen, setIsOpen] = useState(false);

  function toggleModal() {
    setIsOpen(!isOpen);
  }

  
  return (
    <div className="main-form container-form">
      <form>
        <div className="mb-3">
            <label >Numero de cuenta</label>
            <input type="hidden" id="accountId" name="accountId" onClick={toggleModal} value={accountId} onChange={handleInputChange} readOnly/>
            <input type="text"  placeholder="Se genera automaticamente" className="form-control" id="accountNum" name="accountNum"  value={accountNum} onChange={handleInputChange} readOnly/>
        </div>
        <div className="mb-3">
            <label  className="form-label" >Tipo de cuenta</label>
            <select className="form-select" value={accountType.length === 0 ? 'S':accountType}  id="accountType" name="accountType" onChange={handleInputChange} >            
                <option value="S">Ahorros</option>
                <option value="C">Corriente</option>
             </select>
        </div>
        <div className="mb-3">
            <label className="form-label">Saldo Inicial</label>
            <input type="text" className="form-control" id="initialBalance" name="initialBalance"  value={initialBalance} onChange={handleInputChange}/>
        </div>
        <div className="mb-3">
            <label  className="form-label status-label" >Estado</label>
            <select className="form-select status" value={status}  id="status" name="status" onChange={handleInputChange} >            
                <option value={true}>True</option>
                <option value={false}>False</option>
             </select>
        </div>

        <div className="mb-3">
            <label className='form-label' >Cliente</label>
            <input type="hidden" id="customerId" name="customerId" onClick={toggleModal} value={customerId} onChange={handleInputChange} readOnly/>
            <input type="text" placeholder="Seleecionar Cliente" className="form-control" id="customerName" name="customerName" onClick={toggleModal} value={customerName} onChange={handleInputChange} readOnly/>
        </div>
      

        <Button variant="primary"  onClick={() => handleOK(accountBO)} className="btn btn-primary">
                Aceptar
          </Button>
        
      </form>
      <CustomerModal isOpen = {isOpen} toggleModal = {toggleModal}  handleCustomerSelected = {handleCustomerSelected} account = {accountBO} /> 
     
    </div>
  );
};

export default AccountForm;