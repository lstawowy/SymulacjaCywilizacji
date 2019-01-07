import React, {Component} from "react";
import "./Home.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "./../components/NavigateBar";

class Home extends Component {
  render() {
    return (
      <div className='Home'>
        <NavigateBar />
        <h1>Home page</h1>
      </div>
    );
  }
}

export default Home;
