import React from "react";
import "./NavigateBar.css";
import {Navbar, NavbarBrand, NavbarNav, NavItem, NavLink} from "mdbreact";
import {BrowserRouter} from "react-router-dom";

import "bootstrap/dist/css/bootstrap.css";

class NavigateBar extends React.Component {
  refreshPage() {
    window.parent.location = window.parent.location.href;
  }

  render() {
    return (
      <div className='NavigateBar'>
        <Navbar light expand='md'>
          <BrowserRouter>
            <NavbarNav className='Nav'>
              <NavbarBrand className='Item' href='/home' onClick='this.refreshPage()'>
                Symulacja rozwoju cywilizacji
              </NavbarBrand>
              <NavItem className='Item'>
                <NavLink to='/resources' onClick='this.refreshPage()'>
                  Resources
                </NavLink>
              </NavItem>
              <NavItem className='Item'>
                <NavLink to='/civilisations' onClick='this.refreshPage()'>
                  Civilisations
                </NavLink>
              </NavItem>
              <NavItem className='Item'>
                <NavLink to='/about' onClick='this.refreshPage()'>
                  About
                </NavLink>
              </NavItem>
            </NavbarNav>
          </BrowserRouter>
        </Navbar>
      </div>
    );
  }
}

export default NavigateBar;
