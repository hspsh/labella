import { useContext, useEffect, ReactNode } from "react";

import { TemplatesContext } from "./teplatesStore";

type Props = {
  children?: ReactNode;
};

export default function ScreenLoader({ children }: Props) {
  const ctx = useContext(TemplatesContext);

  useEffect(()=>{
    if (ctx.isLoading)
      document.body.classList.add("loading");
    else
      document.body.classList.remove("loading");
  }, [ctx.isLoading])

  // const style = {
  //   cursor: "wait",
  //   width: "100vw",
  //   height: "100vh",
  //   position: "fixed",
  //   top: 0,
  //   left: 0,
  //   zIndex: 100,
  //   visibility: ctx.isLoading != 0 ? "visible" : "hidden",
  // } as React.CSSProperties;

  return <></>;
}
