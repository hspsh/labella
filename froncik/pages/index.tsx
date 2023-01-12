import { useEffect, useContext } from "react";

import TemplateCards from "../components/TemplateCards";
// import Template from "../common/Template";
import { TemplatesContext } from "../components/teplatesStore";
import API from "../lib/api";

export default function Home() {
  // const [templates, setTemplates] = useState<Template[]>([]);
  const ctx = useContext(TemplatesContext);

  useEffect(() => {
    // API.templates.list()
    //   .then((temps) => {
    //     if ((temps as any)?.message) console.error(temps);
    //     if (temps?.length) setTemplates(temps);
    //   })
    // .catch(console.error);
    ctx.fetch();
  }, []);

  return <TemplateCards templates={ctx.templates} />;
}
