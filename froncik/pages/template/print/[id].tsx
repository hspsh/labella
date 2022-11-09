import { useState, useEffect } from "react";
import { useRouter } from "next/router";

import TemplatePrintForm from "../../../components/TemplatePrintForm";
import FormWithPreview from "../../../components/FormWithPreview";

import API from "../../../lib/api";
type QueryParams = {
  id: string;
};

type Props = {};

export default function Print({}: Props) {
  const router = useRouter();
  const id = parseInt((router.query as QueryParams).id);
  const [attributes, setAttributes] = useState<string[]>([]);

  useEffect(() => {
    if (!id) return;

    API.templates.attributes(id).then((fields) => {
      setAttributes(fields);
    });
  }, [id]);

  const form = <TemplatePrintForm attributes={attributes} id={id} />;

  return <FormWithPreview form={form} />;
}
