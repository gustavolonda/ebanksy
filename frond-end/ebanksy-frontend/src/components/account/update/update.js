import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import AccountForm from '../account_form/account_form';
import axios from 'axios';

const AccountUpdate = ({ history }) => {
const { id } = useParams();
useEffect(() => {
  axios.get(`http://localhost:8080/api/accounts/${id}`)
      .then((response) => {
          console.log(id)
          setAPIData(response.data.result);
          console.log(response.data)
      })
}, []);
const handleOK = (customerBO,personBO) => {
  customerBO.personBO = personBO;
  axios.put(`http://localhost:8080/api/accounts`, customerBO).then((response) => {
    console.log(response);
    history.push('/account/read');
})
  

  };
  const [APIData, setAPIData] = useState([]);
 
  return (
    <div >
      {APIData.map((data) => {
        return (
                <React.Fragment>
                    <AccountForm key ={data.id} account ={data} handleOK={handleOK} />
                </React.Fragment>
            
        )
    })}
    </div>
  
    
  );
};

export default AccountUpdate;