import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Navbar from "../components/Navbar"

export default function Home() {
  return (
    <Container>
      <Row>
        <Col>
          <Navbar />
        </Col>
      </Row>
    </Container>
  );
}
