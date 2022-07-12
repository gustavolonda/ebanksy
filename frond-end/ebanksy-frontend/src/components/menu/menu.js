import './menu.css';
import React from 'react';
import { Link } from "react-router-dom";
function Menu() {
  return (
  
        <nav className="navbar bg-light">
          <div className="container-fluid">
            <ul className="navbar-nav">
              <li  key="customer" className="nav-item">
                <Link className="nav-link" to="/customer/read">Clientes</Link>
              </li>
              <li key="custaccount" className="nav-item">
                <Link className="nav-link" to="/account/read">Cuentas</Link>
              </li>
              <li key="transactions" className="nav-item">
                <Link className="nav-link" to="/transaction/read">Movimientos</Link>
              
              </li>
              <li key="report" className="nav-item">
                <Link className="nav-link" to="/reports/report">Reportes</Link>
              </li>
            </ul>
          </div>
      </nav>
  );
}

export default Menu;