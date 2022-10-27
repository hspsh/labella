import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import NavbarBs from 'react-bootstrap/Navbar';

export default function Navbar() {
  return (
    <NavbarBs bg="light" expand="lg">
      <Container>
        <NavbarBs.Brand href="#home">Labella</NavbarBs.Brand>
        <NavbarBs.Toggle aria-controls="basic-navbar-nav" />
        <NavbarBs.Collapse id="basic-navbar-nav">
          <Nav className="me-auto justify-content-end flex-grow-1">
            <Nav.Link href="#clear" className="text-danger">Wyczyść kolejkę</Nav.Link>
            <Nav.Link href="#add" className="text-primary">Dodaj szablon</Nav.Link>
          </Nav>
        </NavbarBs.Collapse>
      </Container>
    </NavbarBs>
  )
}

