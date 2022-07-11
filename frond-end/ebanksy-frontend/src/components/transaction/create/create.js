import React from 'react';
import TransactionForm from '../transaction_form/transaction_form';
import axios from 'axios';
const TransactionCreate = ({ history }) => {

  const handleOK = (transactionBO) => {
    var accountBO = { id: transactionBO.accountId };
    transactionBO.accountBO = accountBO;
    console.log(transactionBO);
    axios.post(`http://localhost:8080/api/transactions`, transactionBO).then(() => {
            
            history.push('/transaction/read')
        })
  };

  return (
      <TransactionForm handleOK={handleOK} />

  );
};

export default TransactionCreate;