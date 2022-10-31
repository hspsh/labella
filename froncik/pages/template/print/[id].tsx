import TemplatePrintForm from "../../../components/TemplatePrintForm";
import FormWithPreview from "../../../components/FormWithPreview";

type Props = {};

export default function print({}: Props) {
  const form = <TemplatePrintForm variables={["imię", "nick", "meme"]} />;

  return <FormWithPreview form={form} />;
}
