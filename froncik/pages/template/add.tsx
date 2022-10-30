import { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

type Props = {};
enum TemplateType {
  SVG,
  MD,
}

export default function add({}: Props) {
  const [templateType, setTemplateType] = useState<TemplateType>(
    TemplateType.SVG
  );

  let contentInput;
  switch (templateType) {
    case TemplateType.SVG: {
      contentInput = (
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Form.Label>Plik SVG</Form.Label>
          <Form.Control type="file" />
        </Form.Group>
      );
      break;
    }
    case TemplateType.MD: {
      contentInput = (
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
          <Form.Control as="textarea" placeholder="Treść szablonu" />
        </Form.Group>
      );
      break;
    }
  }

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
              <Form.Check
                type="radio"
                name="templateType"
                label="Plik SVG"
                id="tt_svg"
                checked={templateType === TemplateType.SVG}
                onChange={() => setTemplateType(TemplateType.SVG)}
              />
              <Form.Check
                type="radio"
                name="templateType"
                label="Tekst Markdown"
                id="tt_md"
                checked={templateType === TemplateType.MD}
                onChange={() => setTemplateType(TemplateType.MD)}
              />
            </Form.Group>
            {contentInput}
            <Button variant="primary" type="submit">
              Dodaj
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}
