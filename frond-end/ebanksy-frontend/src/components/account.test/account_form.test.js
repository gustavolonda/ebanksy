import React from 'react';
import '@testing-library/jest-dom/extend-expect'
import { render } from '@testing-library/react';
import AccountForm from '../account/account_form/account_form';
test('renders content',() => {
    const account ={
        id: "02a9fe47-b3ae-4a71-ab99-7f1096916a8a",
        accountNum: "23455677",
        accountType: "S",
        initialBalance: 1200.0,
        status: false,
        customerName: "Gustavo Londa M.",
        customerId: "60ce6d88-bea3-4a83-a3f8-f6c2ed4804e7",
        availableBalance: 1200.0
    }
    const view = render( <AccountForm account={account} />);
    //console.log(view);
    //view.debug();
    //view.findAllByText('Gustavo Londa M.');

})