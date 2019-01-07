import React, {Component} from "react";
import "./Civilisations.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";
import SideBar from "../components/SideBar";

class Civilisations extends Component {
  constructor(props) {
    super(props);

    this.state = {
      data: null,
    };
  }

  render() {
    return (
      <div className='Civilisations'>
        <NavigateBar />
        <SideBar />
        <div className='MainComponent'>
          <img src='http://localhost:8080/map/current' height='200px' />
          This is only temporary for Civilisations
        </div>
      </div>
    );
  }
}

export default Civilisations;
