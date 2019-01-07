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
        <div className='Sidebar'>
          <SideBar>Sidebar</SideBar>
        </div>
        <div className='MainComponent'>
          <img
            src='https://lh4.googleusercontent.com/-1wzlVdxiW14/USSFZnhNqxI/AAAAAAAABGw/YpdANqaoGh4/s1600-w400/Google%2BSydney'
            height='200px'
          />
          This is only temporary for Resources
        </div>
      </div>
    );
  }
}

export default Resources;
