import TemplateForm from "../../components/TemplateForm";
import FormWithPreview from "../../components/FormWithPreview";

type Props = {};

export default function edit({}: Props) {
  const form = <TemplateForm submitText="Aktualizuj" />;

  return <FormWithPreview form={form} />;
}
