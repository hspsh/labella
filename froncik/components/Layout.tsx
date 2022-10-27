import { ReactNode } from "react";
import Container from "react-bootstrap/Container";
import Navbar from "./Navbar";

type Props = {
  children?: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <Container>
      <Navbar />
      {children}
    </Container>
  );
}
