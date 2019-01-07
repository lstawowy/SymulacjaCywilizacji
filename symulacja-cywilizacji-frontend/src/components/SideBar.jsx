import React from "react";
import "./SideBar.css";
import {SideNav, Nav} from "react-sidenav";

import "bootstrap/dist/css/bootstrap.css";

const theme = {
  hoverBgColor: "yellow",
  selectionBgColor: "#03A9F4",
  selectionIconColor: "#03A9F4"
};

class SideBar extends React.Component {
  render() {
    return (
      <div className='Sidebar'>
        <SideNav id='Sidenav' theme={theme} defaultSelectedPath='1'>
          <Nav id='1'>Show map</Nav>
          <Nav id='2'>Show other map</Nav>
          <Nav id='3'>Do something else</Nav>
        </SideNav>
      </div>
    );
  }
}

export default SideBar;
