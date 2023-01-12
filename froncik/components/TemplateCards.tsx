import { useEffect, useContext } from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import TemplateCard from "../components/TemplateCard";
import { TemplatesContext } from "../components/teplatesStore";

export default function TemplateCards() {
  const ctx = useContext(TemplatesContext);

  useEffect(() => {
    ctx.fetch();
  }, []);

  return (
    <Row>
      {ctx.templates.map((template) => (
        <Col key={template.id} xxl={3} lg={4} md={6} sm={12}>
          <TemplateCard template={template} onDelete={ctx.delete} />
        </Col>
      ))}
    </Row>
  );
}
