import { createContext, ReactNode, useState } from "react";
import Template, { TemplateType } from "../common/Template";
import API from "../lib/api";

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
};

type Props = {
  children?: ReactNode;
};

export const TemplatesContext = createContext<Context>({
  templates: [],
  isLoading: 0,
  async add() {},
  async delete() {},
  async update() {},
  async fetch() {},
  async print() {},
});

function timeout(milliseconds: number) {
  return new Promise((res: Function) => {
    setTimeout(() => {
      res();
    }, milliseconds);
  });
}

export default function TemplatesStore({ children }: Props) {
  const [state, setState] = useState<Store>({
    templates: [],
    isLoading: 0,
  });

  async function withLoading(func: () => Promise<void>) {
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
  }

  const ctx: Context = {
    templates: state.templates,
    isLoading: state.isLoading,
    add: async (name, content, type) => {
      return withLoading(async () => {
        try {
          const tpl = await API.templates.create(name, content, type);
          setState((s) => ({ ...s, templates: [...state.templates, tpl] }));
        } catch (e) {
          alert("nie pykło dodawanie szablonu");
          console.log(e);
        }
      });
    },
    delete: async (id) => {
      return withLoading(async () => {
        try {
          await API.templates.delete(id);
          const newTemplates = state.templates.filter((t) => t.id != id);
          setState((s) => ({ ...s, templates: newTemplates }));
        } catch (e) {
          alert("nie pykło usuwanie szablonu");
          console.log(e);
        }
      });
    },
    update: async (id, name, content, type) => {
      return withLoading(async () => {
        try {
          const tpl = await API.templates.update(id, name, content, type);
          setState((s) => ({ ...s, templates: [...state.templates, tpl] }));
        } catch (e) {
          alert("nie pykło aktualizowanie szablonu");
          console.log(e);
        }
      });
    },
    fetch: async () => {
      return withLoading(async () => {
        try {
          const templates = await API.templates.list();
          setState((s) => ({ ...s, templates: templates }));
        } catch (e) {
          alert("nie pykło pobieranie szablonów");
          console.log(e);
        }
      });
    },
    print: async (id: number, attributes: Record<string, string>) => {
      try {
        return withLoading(async () => {
          await Promise.all([
            timeout(PRINTING_MINIMAL_TIMEOUT),
            API.templates.print(id, attributes),
          ]);
        });
      } catch (e) {
        alert("nie pykło drukowanie :/");
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
