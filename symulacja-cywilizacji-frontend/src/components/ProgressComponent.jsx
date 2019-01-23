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
    this.reset = this.reset.bind(this);
    this.progressMap = this.props.progressMap.bind(this);
  }

  nextStep() {
    if (this.state.percentage === 100) return;
    this.setState({percentage: this.state.percentage + 1});
    this.progressMap(this.state.percentage);
  }

  reset() {
    this.setState({percentage: 0});
    this.progressMap(this.state.percentage);
  }

  render() {
    return (
      <div className='ProgressComponent'>
        <MDBBtn className='ProgressButton' color='primary' rounded onClick={this.nextStep}>
          Next step
        </MDBBtn>
        <MDBBtn className='ProgressButton' color='primary' rounded onClick={this.reset}>
          Reset
        </MDBBtn>
        <Progress sstriped value={this.state.percentage} />
      </div>
    );
  }
}

export default ProgressComponent;
