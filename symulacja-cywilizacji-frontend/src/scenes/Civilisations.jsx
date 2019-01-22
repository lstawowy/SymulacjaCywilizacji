import React, {Component} from "react";
import "./Civilisations.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";
import CivilisationsSideBar from "../components/CivilisationsSideBar";
import ProgressComponent from "../components/ProgressComponent";

export default class Civilisations extends Component {
  constructor(props) {
    super(props);

    this.state = {
      data: null,
      current_url: "http://localhost:8080/map/current/0"
    };
  }

  setCurrentUrl(event) {
    console.log("Civilisation selected: ", event.id);
    document.getElementById("CivilisationsImage").src = "http://localhost:8080/map/" + event.id;
  }

  progressMap(event) {
    console.log("Civilisation selected: ", event);
    document.getElementById("CivilisationsImage").src =
      "http://localhost:8080/map/current/" + event;
  }

  render() {
    return (
      <div className='Civilisations'>
        <NavigateBar />
        <CivilisationsSideBar setCurrentUrl={this.setCurrentUrl} />
        <div className='MainComponent'>
          <img
            className='CivilisationsImage'
            alt='CivilisationsImage'
            id='CivilisationsImage'
            src={this.state.current_url}
          />
          <ProgressComponent progressMap={this.progressMap} />
        </div>
      </div>
    );
  }
}
