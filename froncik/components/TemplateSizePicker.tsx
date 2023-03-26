import { useEffect, useState } from "react";
import { uniqueId } from "lodash";

import NavDropdown from "react-bootstrap/NavDropdown";

import API from "../lib/api";

const possibilities = [
  { width: 465, height: 356 },
  { width: 320, height: 240 },
  { width: 450, height: 108 },
];

export default function TemplateSizePicker() {
  const [size, setSize] = useState({ width: 0, height: 0 });

  useEffect(() => {
    API.templates.readLabelSize().then((s) => setSize(s));
  }, []);

  const title = `Rozmiar Naklejki (${size.width}x${size.height})`;

  return (
    <NavDropdown title={title} className="sizePicker">
      {possibilities.map((s) => {
        return (
          <NavDropdown.Item
            key={uniqueId()}
            onClick={() => {
              setSize({ width: s.width, height: s.height });
              API.templates.updateLabelSize(s.width, s.height);
            }}
          >
            {s.width} x {s.height}
          </NavDropdown.Item>
        );
      })}
    </NavDropdown>
  );
}
