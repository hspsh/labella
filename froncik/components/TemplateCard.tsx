import { Card, Stack, Button, ButtonGroup } from "react-bootstrap";

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
            <Button>Edytuj</Button>
            <Button>Dodaj</Button>
          </ButtonGroup>
        </Stack>
      </Stack>
    </Card>
  );
}
