import TemplateForm from "../../components/TemplateForm";
import FormWithPreview from "../../components/FormWithPreview";

type Props = {};

export default function add({}: Props) {
  const form = <TemplateForm submitText="Dodaj" />;

  return <FormWithPreview form={form} />;
}
