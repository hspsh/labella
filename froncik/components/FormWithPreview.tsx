import { Container, Row, Col, Card } from "react-bootstrap";

type Props = {
  form: React.ReactNode;
  previewSrc?: string
};

export default function FormWithPreview({ form, previewSrc }: Props) {
  return (
    <Container>
      <Row>
        <Col lg={8} md={6}>
          {form}
        </Col>
        <Col lg={4} md={6}>
          <Card
            border="primary"
            key="Primary"
            text="dark"
            style={{ minWidth: "18rem" }}
            className="mb-2"
          >
            <Card.Header>Podgląd</Card.Header>
            <Card.Img
              src={previewSrc}
              alt="Card image"
            />
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
