import React, {Component} from "react";
import "./Resources.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";
import ResourcesSideBar from "../components/ResourcesSideBar";

export default class Resources extends Component {
  constructor(props) {
    super(props);

    this.state = {
      map: null,
      current_url: "http://localhost:8080/map/resources"
    };
  }

  setCurrentUrl(event) {
    console.log("Map selected: ", event.id);
    document.getElementById("ResourceImage").src = "http://localhost:8080/map/" + event.id;
  }

  render() {
    return (
      <div className='Resources'>
        <NavigateBar />
        <ResourcesSideBar setCurrentUrl={this.setCurrentUrl} />
        <div className='MainComponent'>
          <img alt='ResourceImage' id='ResourceImage' src={this.state.current_url} />
        </div>
      </div>
    );
  }
}
