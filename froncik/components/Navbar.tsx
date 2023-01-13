import Link from "next/link";
import { Row, Col, Container, Nav } from "react-bootstrap";
import NavbarBs from "react-bootstrap/Navbar";

import routes from "../lib/routes";
import TemplateSizePicker from "./TemplateSizePicker";

export default function Navbar() {
  return (
    <Row className="mb-2">
      <Col>
        <NavbarBs bg="light" expand="lg">
          <Container>
            <NavbarBs.Brand as={Link} href={routes.home()}>
              Labella
            </NavbarBs.Brand>
            <NavbarBs.Toggle aria-controls="basic-navbar-nav" />
            <NavbarBs.Collapse id="basic-navbar-nav">
              <Nav className="me-auto justify-content-end flex-grow-1">
                <TemplateSizePicker />
                <Nav.Link
                  as={Link}
                  href={routes.add()}
                  className="text-primary"
                >
                  Dodaj szablon
                </Nav.Link>
              </Nav>
            </NavbarBs.Collapse>
          </Container>
        </NavbarBs>
      </Col>
    </Row>
  );
}
