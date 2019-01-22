import React, {Component} from "react";
import "./About.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "../components/NavigateBar";

class About extends Component {
  render() {
    return (
      <div className='About'>
        <NavigateBar />
        <div className='AboutText'>
          <p id='start'>
            Projekt z przedmiotu Modelowanie i symulacja systemów <br />
            Temat: Modelowanie rozwoju cywilizacji <br />
            Autorzy: <br />
            Katarzyna Rugiełło, <br />
            Jakub Kleszcz, <br />
            Łukasz Stawowy <br />
            Projekt wykonany w technologii Java(BE) + React(FE), <br />
            połączenie między serwisami zostało wykonane przy użyciu REST
          </p>
        </div>
      </div>
    );
  }
}

export default About;
