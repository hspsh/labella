import { useState } from "react";
import { InputGroup, Button, Form } from "react-bootstrap";
import FileReaderInput from "react-file-reader-input";

import type FileInput from "react-file-reader-input";

export type Handler = (
  event: React.ChangeEvent<HTMLInputElement>,
  results: FileInput.Result[]
) => void;

export default function FormFile({ onChange, ...props }: FileInput.Props) {
  const [name, setName] = useState("");

  const changeWrapper: Handler = (event, results) => {
    const file = results[0][1];
    setName(file.name);

    onChange && onChange(event, results);
  };

  return (
    <FileReaderInput onChange={changeWrapper} {...props}>
      <InputGroup>
        <Button variant="secondary">Prześlij plik</Button>
        <Form.Control
          aria-label="Image name"
          aria-describedby="Image name"
          placeholder="anime-cat-girl.svg"
          value={name}
          readOnly
        />
      </InputGroup>
    </FileReaderInput>
  );
}
