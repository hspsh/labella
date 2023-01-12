import { ReactNode } from "react";
import Container from "react-bootstrap/Container";
import Navbar from "./Navbar";

import ScreenLoader from "./ScreenLoader";

type Props = {
  children?: ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <>
      <ScreenLoader />
      <Container>
        <Navbar />
        {children}
      </Container>
    </>
  );
}
