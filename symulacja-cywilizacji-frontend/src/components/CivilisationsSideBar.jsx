import React from "react";
import "./SideBar.css";
import {SideNav, Nav} from "react-sidenav";

import "bootstrap/dist/css/bootstrap.css";

const theme = {
  hoverBgColor: "yellow",
  selectionBgColor: "#03A9F4",
  selectionIconColor: "#03A9F4"
};

const maps = [{id: "current", name: "Current Map"}];

export default class CivilisationsSideBar extends React.Component {
  constructor(props) {
    super(props);
    this.setCurrentUrl = this.props.setCurrentUrl.bind(this);
  }

  render() {
    return (
      <div className='Sidebar'>
        <SideNav id='Sidenav' theme={theme} defaultSelectedPath='current'>
          {maps.map(map => (
            <Nav id={map.id} onClick={this.setCurrentUrl.bind(map.id)}>
              {map.name}
            </Nav>
          ))}
        </SideNav>
      </div>
    );
  }
}
