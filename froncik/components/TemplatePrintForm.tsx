import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

type Props = {
  variables: string[];
};

export default function add({ variables }: Props) {
  return (
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
  );
}
