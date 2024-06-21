import {Container, Row, Col, Card} from "react-bootstrap";
import React from "react";
import Images from "./Images"

type Props = {
  form: React.ReactNode;
  id: number,
  fields: Record<string, string>
};

export default function FormWithPreview({form, id, fields}: Props) {
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
            style={{minWidth: "18rem"}}
            className="mb-2"
          >
            <Card.Header>Podgląd</Card.Header>
            <Images key={id} id={id} fields={fields}></Images>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
