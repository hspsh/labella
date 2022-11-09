import { useEffect, useState } from "react";

import TemplateCards from "../components/TemplateCards";
import Template from "../common/Template";
import API from "../lib/api";

export default function Home() {
  const [templates, setTemplates] = useState<Template[]>([]);

  useEffect(() => {
    API.templates.list()
      .then((temps) => {
        if ((temps as any)?.message) console.error(temps);
        if (temps?.length) setTemplates(temps);
      })
      .catch(console.error);
  }, []);

  return <TemplateCards templates={templates} />;
}
