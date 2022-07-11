import React from 'react';
import CustomerForm from '../customer_form/customer_form';
import axios from 'axios';


const CustomerCreate = ({ history }) => {
const handleCustomerOk = (customerBO,personBO) => {
  customerBO.personBO = personBO;
  axios.post(`http://localhost:8080/api/customers`, customerBO).then(() => {
        history.push('/customer/read');
      })
};

  return (

          <CustomerForm handleCustomerOk={handleCustomerOk} />  
  );
};

export  default CustomerCreate;