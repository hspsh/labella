import {useState, useEffect, useCallback} from "react";
import {useRouter} from "next/router";
import debounce from "lodash/debounce";

import TemplatePrintForm from "../../../components/TemplatePrintForm";
import FormWithPreview from "../../../components/FormWithPreview";
import API from "../../../lib/api";
import { toastAlert } from "../../../components/Toaster";

type QueryParams = {
  id: string;
};

type Props = {};

export default function Print({}: Props) {
  const router = useRouter();
  const id = parseInt((router.query as QueryParams).id);
  const [attributes, setAttributes] = useState<string[]>([]);
  const [fields, setFields] = useState({});

  const updatePreview =
    (fields: Record<string, string>)=>setFields(fields)

  const formChangeHandler = (fields: Record<string, string>) => {
    updatePreview(fields);
  };

  useEffect(() => {
    if (!id) return;

    API.templates
      .attributes(id)
      .then((fields) => {
        setAttributes(fields);
      })
      .catch((e) => {
        toastAlert("Nie pykło pobieranie szczegółów szablonu");
        console.log(e);
      });
  }, [id]);

  const form = (
    <TemplatePrintForm
      attributes={attributes}
      id={id}
      onChange={formChangeHandler}
    />
  );

  return (!isNaN(id) && id) && <FormWithPreview key={id} form={form} id={id} fields={fields}/>;
}
