import { useState, useEffect } from "react";
import { uniqueId } from "lodash";

import ToastContainer from "react-bootstrap/ToastContainer";
import Toast from "react-bootstrap/Toast";

const TOAST_SHOW_TIMEOUT = 5000;

type Kind = "error" | "ok";
type Tost = {
  message: string;
  kind: Kind;
  id: string;
};

let tosty: Tost[] = [];
let subscription = () => {};

export function toastAlert(message: string, kind: Kind = "error") {
  tosty = [
    ...tosty,
    {
      message,
      kind,
      id: uniqueId("toast"),
    },
  ];
  subscription();
}

function removeToast(id: string) {
  tosty = tosty.filter((t) => t.id != id);
  subscription();
}

export default function Toaster() {
  const [state, setState] = useState<Tost[]>([]);

  useEffect(() => {
    subscription = () => {
      setState(tosty);
    };
  }, []);

  return (
    <ToastContainer className="p-3" position="bottom-end">
      {state.map((t) => (
        <Toast
          key={t.id}
          onClose={() => removeToast(t.id)}
          delay={TOAST_SHOW_TIMEOUT}
          autohide
        >
          <Toast.Header>
            <strong className="me-auto">
              {t.kind == "error" ? "ɐłɐᴉzp ǝᴉu śoƆ" : "Spoko jest"}
            </strong>
          </Toast.Header>
          <Toast.Body>{t.message}</Toast.Body>
        </Toast>
      ))}
    </ToastContainer>
  );
}
