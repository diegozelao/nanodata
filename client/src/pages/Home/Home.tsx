import React, { ReactNode } from "react";
import { Container } from "react-bootstrap";
import NavbarComponent from "./NavBar";
import FooterComponent from "./Footer";

import './Home.css'

interface HomeProps {
  children: ReactNode;
}
class Home extends React.Component<HomeProps> {

  render() {
    const { children } = this.props
    return (
      <>
        <NavbarComponent />
        <Container id="homePage" fluid>
          {children}
        </Container>
        <FooterComponent />
      </>
    )
  }

}

export default Home