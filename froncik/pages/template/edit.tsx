import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import TemplateForm from "../../components/TemplateForm";

type Props = {};

export default function add({}: Props) {
  return (
    <Container>
      <Row>
        <Col>
          <TemplateForm submitText="Aktualizuj" />
        </Col>
      </Row>
    </Container>
  );
}
