import Link from "next/link";
import { Card, Stack, Button, ButtonGroup, Dropdown } from "react-bootstrap";

import routes from "../lib/routes";
import Template from "../common/Template";
import API from "../lib/api";

type Props = {
  template: Template;
  onDelete: (id: number) => void;
};

export default function TemplateCard({ template, onDelete }: Props) {
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
      <Card.Header>
        <Stack direction="horizontal" gap={3}>
          <h4>{template.name}</h4>
          <Dropdown className="ms-auto">
            <Dropdown.Toggle
              variant="secondary"
              id="dropdown-basic"
            ></Dropdown.Toggle>
            <Dropdown.Menu>
              <Dropdown.Item href={`./api/templates/${template.id}/download`}>
                Pobierz
              </Dropdown.Item>
              <Dropdown.Item href={routes.edit(template.id)}>
                Edytuj
              </Dropdown.Item>
              <Dropdown.Item
                variant="danger"
                onClick={() => onDelete(template.id)}
              >
                Usuń
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </Stack>
      </Card.Header>
      <Stack direction="horizontal">
        <div style={{ display: "inline-table" }}>
          <Card.Img
            src={API.templates.previewSrc(template.id)}
            alt="Card image"
            style={
              { "--bs-card-inner-border-radius": 0 } as React.CSSProperties
            }
          />
        </div>
        <Stack>
          <ButtonGroup
            vertical
            style={{ height: "100%" }}
            className="side-btns"
          >
            <Link href={routes.print(template.id)} passHref legacyBehavior>
              <Button style={buttonStyle}>Drukuj</Button>
            </Link>
          </ButtonGroup>
        </Stack>
      </Stack>
    </Card>
  );
}
