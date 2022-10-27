import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import Navbar from "../components/Navbar";
import TemplateCards from "../components/TemplateCards";

export default function Home() {
  return (
    <Container>
      <Row>
        <Col>
          <Navbar />
        </Col>
      </Row>
      <Row>
        <Col>
          <TemplateCards />
        </Col>
      </Row>
    </Container>
  );
}
