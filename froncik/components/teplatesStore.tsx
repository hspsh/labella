import {createContext, ReactNode, useCallback, useState} from "react";
import Template, {TemplateType} from "../common/Template";
import API from "../lib/api";
import {toastAlert} from "./Toaster";
import _ from "lodash";

const PRINTING_MINIMAL_TIMEOUT = 1000;

type Store = {
  templates: Template[];
  isLoading: number;
};

type Context = Store & {
  add: (name: string, content: string, type: TemplateType) => Promise<void>;
  delete: (id: number) => Promise<void>;
  update: (
    id: number,
    name: string,
    content: string,
    type: TemplateType
  ) => Promise<void>;
  fetch: () => Promise<void>;
  print: (id: number, attributes: Record<string, string>) => Promise<void>;
  withLoading: (x: () => Promise<void>) => Promise<void>;
};

type Props = {
  children?: ReactNode;
};

export const TemplatesContext = createContext<Context>({
  templates: [],
  isLoading: 0,
  async add() {
  },
  async delete() {
  },
  async update() {
  },
  async fetch() {
  },
  async print() {
  },
  async withLoading() {
  }
});

function timeout(milliseconds: number) {
  return new Promise((res: Function) => {
    setTimeout(() => {
      res();
    }, milliseconds);
  });
}

export default function TemplatesStore({children}: Props) {
  const [state, setState] = useState<Store>({
    templates: [],
    isLoading: 0,
  });


  const ctx: Context = {
      templates: state.templates,
      isLoading: state.isLoading,
      withLoading: async (func: () => Promise<void>) => {
        setState((s) => {
          return {
            ...s,
            isLoading: s.isLoading + 1,
          };
        });

        try {
          await func();
        } finally {
          setState((s) => {
            return {
              ...s,
              isLoading: s.isLoading - 1,
            };
          });
        }
      },
      add: async (name, content, type) => {
        return ctx.withLoading(async () => {
          try {
            const tpl = await API.templates.create(name, content, type);
            setState((s) => ({...s, templates: [...state.templates, tpl]}));
          } catch (e) {
            toastAlert("Nie pykło dodawanie szablonu");

            console.log(e);
          }
        });
      },
      delete: async (id) => {
        return ctx.withLoading(async () => {
          try {
            await API.templates.delete(id);
            const newTemplates = state.templates.filter((t) => t.id != id);
            setState((s) => ({...s, templates: newTemplates}));
          } catch (e) {
            toastAlert("Nie pykło usuwanie szablonu");
            console.log(e);
          }
        });
      },
      update: async (id, name, content, type) => {
        return ctx.withLoading(async () => {
          try {
            const tpl = await API.templates.update(id, name, content, type);
            setState((s) => ({...s, templates: [...state.templates, tpl]}));
          } catch (e) {
            toastAlert("Nie pykło aktualizowanie szablonu");
            console.log(e);
          }
        });
      },
      fetch: async () => {
        return ctx.withLoading(async () => {
          try {
            const templates = await API.templates.list();
            setState((s) => ({...s, templates: templates}));
          } catch (e) {
            toastAlert("Nie pykło pobieranie szablonów");
            console.log(e);
          }
        });
      },
      print: async (id: number, attributes: Record<string, string>) => {
        try {
          return ctx.withLoading(async () => {
            await Promise.all([
              timeout(PRINTING_MINIMAL_TIMEOUT),
              API.templates.print(id, attributes),
            ]);
          });
        } catch (e) {
          toastAlert("Nie pykło drukowanie :/");
          console.log(e);
        }
      },
    }
  ;

  return (
    <TemplatesContext.Provider value={ctx}>
      {children}
    </TemplatesContext.Provider>
  );
}
