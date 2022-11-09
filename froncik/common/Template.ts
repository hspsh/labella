
export enum TemplateType {
  SVG,
  MD,
}
export default interface Template {
  id: number,
  name: string,
  template: string,
  type: TemplateType
}
