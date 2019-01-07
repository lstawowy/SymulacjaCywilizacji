import React, {Component} from "react";
import "./Civilisations.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";
import SideBar from "../components/SideBar";

class Civilisations extends Component {
  render() {
    return (
      <div className='Civilisations'>
        <NavigateBar />
        <SideBar />
        <div className='MainComponent'>
          <img
            src='https://lh4.googleusercontent.com/-1wzlVdxiW14/USSFZnhNqxI/AAAAAAAABGw/YpdANqaoGh4/s1600-w400/Google%2BSydney'
            height='200px'
          />
          This is only temporary for Civilisations
        </div>
      </div>
    );
  }
}

export default Civilisations;
