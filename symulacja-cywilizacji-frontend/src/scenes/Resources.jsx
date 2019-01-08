import React, {Component} from "react";
import "./Resources.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";
import SideBar from "../components/SideBar";

class Resources extends Component {
  render() {
    return (
      <div className='Resources'>
        <NavigateBar />
        <SideBar />
        <div className='MainComponent'>
          <img alt='resourcesMap' src='http://localhost:8080/map/resources' />
        </div>
      </div>
    );
  }
}

export default Resources;
