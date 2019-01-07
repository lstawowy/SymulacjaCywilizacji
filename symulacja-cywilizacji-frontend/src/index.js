import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import {BrowserRouter, Route} from "react-router-dom";
import * as serviceWorker from "./serviceWorker";

import Home from "./scenes/Home";
import Resources from "./scenes/Resources";
import Civilisations from "./scenes/Civilisations";
import About from "./scenes/About";

const router = (
  <BrowserRouter>
    <React.Fragment>
      <Route path='/' component={App} exact />
      <Route path='/home' component={Home} exact />
      <Route path='/resources' component={Resources} exact />
      <Route path='/civilisations' component={Civilisations} exact />
      <Route path='/about' component={About} exact />
    </React.Fragment>
  </BrowserRouter>
);

ReactDOM.render(router, document.getElementById("root"));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
