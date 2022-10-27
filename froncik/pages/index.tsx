import Container from "react-bootstrap/Container";

import Navbar from "../components/Navbar";
import TemplateCards from "../components/TemplateCards";

export default function Home() {
  return (
    <Container>
      <Navbar />
      <TemplateCards />
    </Container>
  );
}
