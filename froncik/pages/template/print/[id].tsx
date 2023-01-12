import {useState, useEffect, useCallback} from "react";
import {useRouter} from "next/router";
import debounce from "lodash/debounce";

import TemplatePrintForm from "../../../components/TemplatePrintForm";
import FormWithPreview from "../../../components/FormWithPreview";
import API from "../../../lib/api";

type QueryParams = {
  id: string;
};

type Props = {};
const debounceDelay = 400; //ms

export default function Print({}: Props) {
  const router = useRouter();
  const id = parseInt((router.query as QueryParams).id);
  const [attributes, setAttributes] = useState<string[]>([]);
  const [fields, setFields] = useState({});

  const updatePreview = useCallback(
    debounce((id: number, fields: Record<string, string>) => {
      setFields(fields)
    }, debounceDelay),
    []
  );

  const formChangeHandler = (fields: Record<string, string>) => {
    updatePreview(id, fields);
  };

  useEffect(() => {
    if (!id) return;

    API.templates.attributes(id).then((fields) => {
      setAttributes(fields);
    });
  }, [id]);

  const form = (
    <TemplatePrintForm
      attributes={attributes}
      id={id}
      onChange={formChangeHandler}
    />
  );

  return (!isNaN(id) && id) && <FormWithPreview form={form} id={id} fields={fields}/>;
}
