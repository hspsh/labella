import React, { useState, useEffect, useContext } from "react";
import { Button, Card, Form } from "react-bootstrap";

import { TemplatesContext } from "./teplatesStore";
import API from "../lib/api";

type Props = {
  attributes: string[];
  id: number;
  onChange?: (fields: Record<string, string>) => void;
};

const arrayOfStringToObjectKeys = (arr: string[]) => {
  return arr.reduce((acc, value) => {
    return {
      [value]: "",
      ...acc,
    };
  }, {});
};

export default function TemplatePrinterForm({
  attributes,
  id,
  onChange,
}: Props) {
  const [fields, setFields] = useState<Record<string, string>>(
    arrayOfStringToObjectKeys(attributes)
  );
  const ctx = useContext(TemplatesContext);

  const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const name = event.target.name;
    setFields((lastFields) => {
      return {
        ...lastFields,
        [name]: event.target.value,
      };
    });
  };

  useEffect(() => {
    onChange && onChange(fields);
  }, [fields, onChange]);

  return (
    <Card>
      <Card.Body>
        <Form action={API.templates.download(id)} target="_blank"
          onSubmit={(event) => {
            console.log(event)
            /* @ts-ignore */
            if(event.nativeEvent?.submitter?.name != "download"){
              event.preventDefault();
              ctx.print(id, fields);
            }
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

            <Button variant="secondary" name={"download"} className={"me-2"} type="submit">
              Pobierz
            </Button>

            <Button variant="primary" type="submit">
              Drukuj
            </Button>
          </>
        </Form>
      </Card.Body>
    </Card>
  );
}
