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

  return <></>;
}
