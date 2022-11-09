import React, { useState } from "react";
import { Button, Card, Form } from "react-bootstrap";

import API from "../lib/api";

type Props = {
  attributes: string[];
  id: number;
};

const arrayOfStringToObjectKeys = (arr: string[]) => {
  return arr.reduce((acc, value) => {
    return {
      [value]: "",
      ...acc,
    };
  }, {});
};

export default function TemplatePrinterForm({ attributes, id }: Props) {
  const [fields, setFields] = useState<Record<string, string>>(
    arrayOfStringToObjectKeys(attributes)
  );

  const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log("Dzieje sieee");

    const name = event.target.name;
    setFields((lastFields) => {
      console.log(`Zmien wartosc ${name} na ${event.target.value}`);
      return {
        ...lastFields,
        [name]: event.target.value,
      };
    });
  };

  return (
    <Card>
      <Card.Body>
        <Form
          onSubmit={(event) => {
            event.preventDefault();

            API.templates.print(id, fields);
          }}
        >
          <>
            {attributes.map((fieldName) => (
              <Form.Group className="mb-3" key={fieldName}>
                <Form.Label>{fieldName}</Form.Label>
                <Form.Control
                  type="text"
                  name={fieldName}
                  placeholder={`wprowadź ${fieldName}`}
                  onChange={handleFieldChange}
                />
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
