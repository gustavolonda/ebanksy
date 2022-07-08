import React from 'react';
import CustomerForm from '../customer/customer_form/customer_form';
import axios from 'axios';
const CustomerCreate = ({ history }) => {

  const handleOnSubmit = (customer) => {
    axios.post(`http://localhost:8080/api/customers`, customer).then(() => {
            history.push('/customer/read/read')
        })
  };

  return (
    <React.Fragment>
      <CustomerForm handleOnSubmit={handleOnSubmit} />
    </React.Fragment>
  );
};

export default CustomerCreate;