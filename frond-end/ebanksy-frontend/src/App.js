
import './App.css';
import TransactionRead from './components/transaction/read';
import CustomerRead from './components/customer/read';
import AccountRead from './components/account/read';
import { BrowserRouter as Router, Route } from 'react-router-dom'
import Menu from './components/menu/menu.js';
import Logo from './components/logo/logo.js';
import GrayLine from './components/gray_line/gray_line.js';

function App() {
  return (
    <Router>
      <div >
        <Logo/>
        <GrayLine/>
        <div>
          <div className="row">
              <div className="col-sm-2">
              <Menu></Menu>
              </div>
              <div className="col-sm-10">
              <Route  path='/transaction/read' component={TransactionRead} />
                <Route  path='/account/read' component={AccountRead} />
                <Route  path='/customer/read' component={CustomerRead} />

              </div>
          </div>
        </div>
        
       
      </div>
    
    </Router>
  );
}

export default App;