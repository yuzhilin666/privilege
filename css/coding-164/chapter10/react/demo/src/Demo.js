import styled from 'styled-components';
import React, { Component } from 'react';
// import './Demo.css';

const Div = styled.div`
  color:red;
  font-size:36px;
  ${(props)=>props.big&&`
    font-size:72px;
  `}
`
const Div2 = styled(Div)`
  color:green;
`

class Demo extends Component {
  render() {
    return (
      <div>
        <Div big>Hello I'm demo</Div>
        <Div2>Hello I'm demo</Div2>
      </div>
    );
  }
}

export default Demo;
