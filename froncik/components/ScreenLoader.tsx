import { useContext, ReactNode } from "react";

import { TemplatesContext } from "./teplatesStore";

type Props = {
  children?: ReactNode;
};

export default function ScreenLoader({ children }: Props) {
  const ctx = useContext(TemplatesContext);

  const style = {
    cursor: "wait",
    width: "100vw",
    height: "100vh",
    position: "fixed",
    top: 0,
    left: 0,
    zIndex: 100,
    visibility: ctx.isLoading != 0 ? "visible" : "hidden",
  } as React.CSSProperties;

  return <div style={style}>{children}</div>;
}
