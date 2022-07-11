import React, { useEffect, useState } from 'react';
import CustomerForm from '../customer_form/customer_form';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const CustomerCreate = ({ history }) => {
const { id } = useParams();
const [APIData, setAPIData] = useState([]);
useEffect(() => {
    axios.get(`http://localhost:8080/api/customers/${id}`)
        .then((response) => {
            var result = response.data.result;
            setAPIData(result);
        })
}, []);
const handleCustomerOk = (customerBO,personBO) => {
  customerBO.personBO = personBO;
  axios.put(`http://localhost:8080/api/customers`, customerBO).then((response) => {
    console.log(response);
    history.push('/customer/read');
})
};

  return (
    <div >
      {APIData.map((customer) => {
        return (
          <React.Fragment>
             <CustomerForm key={customer.id}   customer={customer} handleCustomerOk={handleCustomerOk} />

          </React.Fragment>
            
        )
    })}
    </div>
    
  );
};

export  default CustomerCreate;