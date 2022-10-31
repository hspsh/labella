import { Button, Card, Form } from "react-bootstrap";

type Props = {
  variables: string[];
};

export default function add({ variables }: Props) {
  return (
    <Card>
      <Card.Body>
        <Form>
          <>
            {variables.map((v) => (
              <Form.Group className="mb-3" key={v}>
                <Form.Label>{v}</Form.Label>
                <Form.Control type="text" placeholder={`wprowadź ${v}`} />
              </Form.Group>
            ))}
            <Button variant="primary" type="submit">
              Drukuj
            </Button>
          </>
        </Form>
      </Card.Body>
    </Card>
  );
}
