import React, { useState } from 'react';
import {Button } from 'react-bootstrap';
import './transaction_form.css';
import AccountModal from '../../account/account_modal/account_modal'
const TransactionForm =  ({
  transaction,
  handleOK
}) =>  {
  const [transactionBO, setTransaction] = useState(() => {
    return {
        id: transaction ? transaction.id : '',
        transactionDate: transaction ? transaction.transactionDate : '',
        customerId: transaction ? transaction.customerId : '',
        customerName: transaction ? transaction.customerName : '',
        accountId:  transaction ? transaction.accountId : '',
        accountNum: transaction ? transaction.accountNum : '',
        accountType: transaction ? transaction.accountType : '',
        initialBalance: transaction ? transaction.initialBalance : '',
        value: transaction ? transaction.value : 0,
        status: transaction ? transaction.status : true,
        transactionType: transaction ? transaction.transactionType : 'W',
        availableBalance:transaction ? transaction.availableBalance : '',
        accountBO: null
    };
  });
  const {   id, 
            transactionDate,
            customerId, 
            customerName,
            accountId,
            accountNum, 
            accountType, 
            initialBalance, 
            value ,
            status,
            transactionType,
            availableBalance} = transactionBO;
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setTransaction((prevState) => ({
        ...prevState,
        [name]: value
      }));
  };

const [isOpenAccount, setIsOpenAccount] = useState(false);

function toggleModalAccount() {
  setIsOpenAccount(!isOpenAccount);
}
const handleAccountSelected = (transactionBO) => {
  setIsOpenAccount(false);
  setTransaction(transactionBO);
};
  return (
    <div className="main-form container-form">
      <form>
        <div className="mb-3">
            <label className="form-label">Numero de cuenta</label>
            <input type="hidden" id="accountId" name="accountId" onClick={toggleModalAccount} value={accountId} onChange={handleInputChange} readOnly/>
            <input type="text" placeholder="Seleecionar Numero de cuenta" className="form-control" id="accountNum" name="accountNum" onClick={toggleModalAccount} value={accountNum} onChange={handleInputChange} readOnly/>

        </div>
        <div className="mb-3">
            <label  className="form-label" >Tipo de Movimiento</label>
            <select className="form-select" value={transactionType}  id="transactionType" name="transactionType" onChange={handleInputChange} >            
                <option value="W">Retiro</option>
                <option value="D">Deposito</option>
             </select>
        </div>
        <div className="mb-3">
            <label  className="form-label status-label" >Estado</label>
            <select className="form-select status" value={status}  id="status" name="status" onChange={handleInputChange} >            
                <option value={true}>True</option>
                <option value={false}>False</option>
             </select>
        </div>
        <div className="mb-3">
            <label className="form-label">Movimiento</label>
            <input type="number" min="0.00" className="form-control" id="value" name="value"  value={value} onChange={handleInputChange}/>
        </div>
        <Button variant="primary"  onClick={() => handleOK(transactionBO)} className="btn btn-primary">
                Aceptar
          </Button>
      
        
      </form>
      <AccountModal isOpenAccount = {isOpenAccount} toggleModalAccount = {toggleModalAccount}  handleAccountSelected = {handleAccountSelected} transaction = {transactionBO} /> 
    </div>
  );
};

export default TransactionForm;