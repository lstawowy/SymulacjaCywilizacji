import React from "react";
import {Progress} from "reactstrap";
import "./ProgressComponent.css";
import {MDBBtn} from "mdbreact";

class ProgressComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      percentage: 0
    };
    this.nextStep = this.nextStep.bind(this);
  }

  nextStep() {
    if (this.state.percentage === 100) return;
    this.setState({percentage: this.state.percentage + 5});
  }

  render() {
    return (
      <div className='ProgressComponent'>
        <MDBBtn className='ProgressButton' color='primary' rounded onClick={this.nextStep}>
          Next Step
        </MDBBtn>
        <Progress sstriped value={this.state.percentage} />
      </div>
    );
  }
}

export default ProgressComponent;
