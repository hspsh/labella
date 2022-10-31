import Link from "next/link";
import { Card, Stack, Button, ButtonGroup } from "react-bootstrap";

import routes from "../lib/routes";

type Props = {};

export default function TemplateCard({}: Props) {
  const buttonStyle = {
    borderTopLeftRadius: 0,
    borderBottomLeftRadius: 0,
  };

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
          style={{ "--bs-card-inner-border-radius": 0 } as React.CSSProperties}
        />
        <Stack>
          <ButtonGroup
            vertical
            style={{ height: "100%" }}
            className="side-btns"
          >
            <Button style={buttonStyle}>Usuń</Button>
            <Link href={routes.edit(1)} passHref legacyBehavior>
              <Button style={buttonStyle}>Edytuj</Button>
            </Link>
            <Link href={routes.print(1)} passHref legacyBehavior>
              <Button style={buttonStyle}>Drukuj</Button>
            </Link>
          </ButtonGroup>
        </Stack>
      </Stack>
    </Card>
  );
}
