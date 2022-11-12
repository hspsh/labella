import TemplateForm from "../../components/TemplateForm";

import API from "../../lib/api";
import routes from "../../lib/routes";
import {useRouter} from "next/router";

type Props = {};

export default function Add({}: Props) {
  const router = useRouter()
  return (
    <TemplateForm
      submitText="Dodaj"
      submitCallback={(template) => {
        API.templates.create(template.name, template.template, template.type)
          .then(() => router.push(routes.home()));
      }}
    />
  );
}
