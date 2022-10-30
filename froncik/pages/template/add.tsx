import Container from "react-bootstrap/Container";
import { Row, Col, Card } from "react-bootstrap";

import TemplateForm from "../../components/TemplateForm";

type Props = {};

export default function add({}: Props) {
  return (
    <Container>
      <Row>
        <Col>
          <TemplateForm submitText="Dodaj" />
        </Col>
        <Col>
          <Card
            border="primary"
            key="Primary"
            text="dark"
            style={{ width: "18rem" }}
            className="mb-2"
          >
            <Card.Img
              src="https://via.placeholder.com/150x90?text=elo%20mordzie"
              alt="Card image"
            />
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
