import React, {Component} from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import NavigateBar from "./components/NavigateBar";

class App extends Component {
  render() {
    return (
      <div className='App'>
        <NavigateBar />
      </div>
    );
  }
}

export default App;
