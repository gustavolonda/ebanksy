
import './App.css';
import TransactionRead from '../components/transaction/read/read';
import TransactionCreate from '../components/transaction/create/create';
import TransactionUpdate from '../components/transaction/update/update';

import CustomerRead from '../components/customer/read/read';
import CustomerCreate from '../components/customer/create/create';
import CustomerUpdate from '../components/customer/update/update';

import AccountRead from '../components/account/read/read';
import AccountCreate from '../components/account/create/create';
import AccountUpdate from '../components/account/update/update';
import Report from '../components/reports/report';

import { BrowserRouter as Router, Route, Redirect  } from 'react-router-dom'
import Menu from '../components/menu/menu.js';
import Logo from '../components/logo/logo.js';
import GrayLine from '../components/gray_line/gray_line.js';

function App() {
  return (
    <Router>
      <div className='main' >
        <Logo/>
        <GrayLine/>
        <div>
          <div className="row">
              <div className="col-sm-2">
              <Menu></Menu>
              </div>
              <div className="col-sm-10">
                <Route  path='/transaction/read' component={TransactionRead}  />
                <Route  path='/transaction/create' component={TransactionCreate} />
                <Route  path='/transaction/update/:id' component={TransactionUpdate} />
                
                <Route  path='/account/read' component={AccountRead} />
                <Route  path='/account/create' component={AccountCreate} />
                <Route  path='/account/update/:id' component={AccountUpdate} />

                <Route  path='/customer/create' component={CustomerCreate} />
                <Route  path='/customer/read' component={CustomerRead} exact={true}/>
                <Route  path='/customer/update/:id' component={CustomerUpdate}/>

                <Route  path='/reports/report' component={Report}/>

                <Route exact path="/" render={() => (<Redirect to="/customer/read" />)} /> 
              </div>
          </div>
        </div>
        
       
      </div>
    
    </Router>
  );
}

export default App;