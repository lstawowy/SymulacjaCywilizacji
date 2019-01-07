import React, {Component} from "react";
import "./About.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";

class About extends Component {
  render() {
    return (
      <div className='About'>
        <NavigateBar />
        <h1>About page</h1>
      </div>
    );
  }
}

export default About;
