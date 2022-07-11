import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import TransactionForm from '../transaction_form/transaction_form';
import axios from 'axios';
const TransactionUpdate = ({ history }) => {

  const { id } = useParams();
  const [APIData, setAPIData] = useState([]);
useEffect(() => {
  axios.get(`http://localhost:8080/api/transactions/${id}`)
      .then((response) => {
          console.log(id)
          setAPIData(response.data.result);
          console.log(response.data)
      })
}, []);

  const handleOK = (transactionBO) => {
    var accountBO = { id: transactionBO.accountId };
    transactionBO.accountBO = accountBO;
    axios.put(`http://localhost:8080/api/transactions`, transactionBO).then(() => { 
            history.push('/transaction/read')
        })
  };

  return (
    <div >
    {APIData.map((data) => {
      return (
     
                   <TransactionForm key ={data.id} transaction ={data}  handleOK={handleOK} />
          )
  })}
     </div>

  );
};

export default TransactionUpdate;