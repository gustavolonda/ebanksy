import React from 'react';
import AccountForm from '../account_form/account_form';
import axios from 'axios';
const AccountCreate = ({ history }) => {

  const handleOK = (accountBO) => {
    var customerBO = { id: accountBO.customerId };
    accountBO.customerBO = customerBO;
    axios.post(`http://localhost:8080/api/accounts`, accountBO).then(() => {
        history.push('/account/read');
        })
  };

  return (
      <AccountForm handleOK={handleOK} />
  );
};

export default AccountCreate;