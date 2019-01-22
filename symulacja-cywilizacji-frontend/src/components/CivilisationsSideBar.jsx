import React from "react";
import "./SideBar.css";
import {SideNav, Nav} from "react-sidenav";

import "bootstrap/dist/css/bootstrap.css";

const theme = {
  hoverBgColor: "yellow",
  selectionBgColor: "#03A9F4",
  selectionIconColor: "#03A9F4"
};

const maps = [
  {id: "current/0", name: "Current Map"},
  {id: "bc_600", name: "Map 600 BC"},
  {id: "bc_650", name: "Map 650 BC"},
  {id: "bc_700", name: "Map 700 BC"},
  {id: "bc_750", name: "Map 750 BC"},
  {id: "bc_800", name: "Map 800 BC"},
  {id: "run", name: "RUN!"}
];

export default class CivilisationsSideBar extends React.Component {
  constructor(props) {
    super(props);
    this.setCurrentUrl = this.props.setCurrentUrl.bind(this);
  }

  render() {
    return (
      <div className='Sidebar'>
        <SideNav id='Sidenav' theme={theme} defaultSelectedPath='current/0'>
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
