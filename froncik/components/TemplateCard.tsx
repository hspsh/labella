import Card from "react-bootstrap/Card";
import Stack from "react-bootstrap/Stack";
import Button from "react-bootstrap/Button";

type Props = {};

export default function TemplateCard({}: Props) {
  return (
    <Card
      border="primary"
      key="Primary"
      text="dark"
      style={{ width: "18rem" }}
      className="mb-2"
    >
      <Card.Header>Nazwa szablonu</Card.Header>
      <Stack direction="horizontal">
        <Card.Img
          src="https://via.placeholder.com/150x90?text=elo%20mordzie"
          alt="Card image"
        />
        <Stack style={{ justifyContent: "space-between" }}>
          <Button>Usuń</Button>
          <Button>Edytuj</Button>
          <Button>Dodaj</Button>
        </Stack>
      </Stack>
    </Card>
  );
}
