import { useContext } from "react";
import { useRouter } from "next/router";

import TemplateForm from "../../../components/TemplateForm";
import FormWithPreview from "../../../components/FormWithPreview";
import { TemplatesContext } from "../../../components/teplatesStore";

import API from "../../../lib/api";
type QueryParams = {
  id: string;
};

export default function Edit() {
  const router = useRouter();
  const ctx = useContext(TemplatesContext);
  const id = parseInt((router.query as QueryParams).id);

  const form = (
    <TemplateForm
      submitText="Aktualizuj"
      submitCallback={(template) => {
        ctx.update(
          id,
          template.name,
          template.template,
          template.type
        );
      }}
    />
  );

  return (
    <FormWithPreview form={form} previewSrc={API.templates.previewSrc(id)} />
  );
}
