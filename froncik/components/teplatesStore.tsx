import { createContext, ReactNode, useState } from "react";
import Template, { TemplateType } from "../common/Template";
import API from "../lib/api";

type Store = {
  templates: Template[];
  isLoading: number;
};

type Context = Store & {
  add: (name: string, content: string, type: TemplateType) => Promise<void>;
  fetch: () => Promise<void>;
};

type Props = {
  children?: ReactNode;
};

export const TemplatesContext = createContext<Context>({
  templates: [],
  isLoading: 0,
  async add() {},
  async fetch() {},
});

export default function TemplatesStore({ children }: Props) {
  const [state, setState] = useState<Store>({
    templates: [],
    isLoading: 0,
  });

  const ctx: Context = {
    templates: state.templates,
    isLoading: state.isLoading,
    add: async (name, content, type) => {
      try {
        const tpl = await API.templates.create(name, content, type);
        setState({ ...state, templates: [...state.templates, tpl] });
      } catch (e) {
        alert("nie pykło dodawanie szablonu");
        console.log(e);
      }
    },
    fetch: async () => {
      try {
        const templates = await API.templates.list();
        setState({ ...state, templates: templates });
      } catch (e) {
        alert("nie pykło pobieranie szablonów");
        console.log(e);
      }
    },
  };

  return (
    <TemplatesContext.Provider value={ctx}>
      {children}
    </TemplatesContext.Provider>
  );
}
