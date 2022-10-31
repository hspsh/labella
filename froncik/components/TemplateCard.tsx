import Link from "next/link";
import { Card, Stack, Button, ButtonGroup } from "react-bootstrap";

import routes from "../lib/routes";

type Props = {};

export default function TemplateCard({}: Props) {
  return (
    <Card
      border="primary"
      key="Primary"
      text="dark"
      style={{ minWidth: "18rem" }}
      className="mb-2"
    >
      <Card.Header>Nazwa szablonu</Card.Header>
      <Stack direction="horizontal">
        <Card.Img
          src="https://via.placeholder.com/150x90?text=elo%20mordzie"
          alt="Card image"
        />
        <Stack>
          <ButtonGroup vertical style={{ height: "100%" }}>
            <Button>Usuń</Button>
            <Link href={routes.edit(1)} passHref legacyBehavior><Button>Edytuj</Button></Link>
            <Link href={routes.print(1)} passHref legacyBehavior><Button>Drukuj</Button></Link>
          </ButtonGroup>
        </Stack>
      </Stack>
    </Card>
  );
}
