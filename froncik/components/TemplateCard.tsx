import Link from "next/link";
import { Card, Stack, Button, ButtonGroup } from "react-bootstrap";

import routes from "../lib/routes";
import Template from "../common/Template";
import API from "../lib/api";

type Props = {
  template: Template;
};

export default function TemplateCard({ template }: Props) {
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
      <Card.Header>{template.name}</Card.Header>
      <Stack direction="horizontal">
        <Card.Img
          src={API.templates.previewSrc(template.id)}
          alt="Card image"
          style={{ "--bs-card-inner-border-radius": 0 } as React.CSSProperties}
        />
        <Stack>
          <ButtonGroup
            vertical
            style={{ height: "100%" }}
            className="side-btns"
          >
            <Button
              style={buttonStyle}
              onClick={() => API.templates.delete(template.id)}
            >
              Usuń
            </Button>
            <Link href={routes.edit(template.id)} passHref legacyBehavior>
              <Button style={buttonStyle}>Edytuj</Button>
            </Link>
            <Link href={routes.print(template.id)} passHref legacyBehavior>
              <Button style={buttonStyle}>Drukuj</Button>
            </Link>
          </ButtonGroup>
        </Stack>
      </Stack>
    </Card>
  );
}
