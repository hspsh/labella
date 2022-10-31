import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import TemplateCard from "../components/TemplateCard";
import Template from "../common/Template"

type Props = {
  templates: Template[]
};

export default function TemplateCards({templates}: Props) {
  return (
    <Row>
      {templates.map((template) => (
        <Col key={template.id} xxl={3} lg={4} md={6} sm={12} >
          <TemplateCard template={template}/>
        </Col>
      ))}
    </Row>
  );
}
