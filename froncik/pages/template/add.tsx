import TemplateForm from "../../components/TemplateForm";
import FormWithPreview from "../../components/FormWithPreview";

import API from "../../lib/api";

type Props = {};

export default function Add({}: Props) {
  return (
    <TemplateForm
      submitText="Dodaj"
      submitCallback={(template) => {
        API.templates.create(template.name, template.template, template.type);
      }}
    />
  );
}
