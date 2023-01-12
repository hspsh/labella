import { useContext } from "react";
import { useRouter } from "next/router";
import TemplateForm from "../../components/TemplateForm";

import routes from "../../lib/routes";
import { TemplatesContext } from "../../components/teplatesStore";

type Props = {};

export default function Add({}: Props) {
  const router = useRouter();
  const ctx = useContext(TemplatesContext);

  return (
    <TemplateForm
      submitText="Dodaj"
      submitCallback={(template) => {
        ctx
          .add(template.name, template.template, template.type)
          .then(() => router.push(routes.home()));
      }}
    />
  );
}
