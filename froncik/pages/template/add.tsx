import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

type Props = {};

export default function add({}: Props) {
  return (
    <Container>
      <Row>
        <Col>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>Nazwa</Form.Label>
              <Form.Control type="text" placeholder="wprowadź nazwę" />
              <Form.Text className="text-muted">
                Fajną nazwę daj, taką unikalną.
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Rodzaj szablonu</Form.Label>
              <Form.Check type="radio" name="templateType" label="Plik SVG" />
              <Form.Check
                type="radio"
                name="templateType"
                label="Tekst Markdown"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
              <Form.Control as="textarea" placeholder="Treść szablonu" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
              <Form.Label>Plik SVG</Form.Label>
              <Form.Control type="file" />
            </Form.Group>
            <Button variant="primary" type="submit">
              Dodaj
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}
