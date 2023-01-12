import {useRouter} from "next/router";

import TemplateForm from "../../../components/TemplateForm";
import FormWithPreview from "../../../components/FormWithPreview";

import API from "../../../lib/api";

type QueryParams = {
  id: string;
};

export default function Edit() {
  const router = useRouter();
  const id = parseInt((router.query as QueryParams).id);

  const form = (
    <TemplateForm
      submitText="Aktualizuj"
      submitCallback={(template) => {
        API.templates.update(
          id,
          template.name,
          template.template,
          template.type
        );
      }}
    />
  );

  return (
    (!isNaN(id) && id) && <FormWithPreview form={form} id={id} fields={{}}/>
  );
}
