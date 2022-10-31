import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import TemplateCard from "../components/TemplateCard";

type Props = {};

export default function TemplateCards({}: Props) {
  return (
    <Row>
      {[1, 2, 3].map((num) => (
        <Col key={num} xxl={3} lg={4} md={6} sm={12} >
          <TemplateCard/>
        </Col>
      ))}
    </Row>
  );
}
