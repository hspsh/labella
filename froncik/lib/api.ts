import Template, {TemplateType} from "../common/Template";

const API_PATH = process.env.NEXT_PUBLIC_API_PATH || "/api";

const myFetch = (
  input: RequestInfo | URL,
  init?: RequestInit
): Promise<Response> => {
  return fetch(input, {
    mode: "cors",
    credentials: "same-origin",

    ...init,
  });
};

const API = {
  templates: {
    list(): Promise<Template[]> {
      return myFetch(`${API_PATH}/templates`)
        .then((req) => req.json())
        .then((data) => data._embedded.templates as Template[]);
    },
    create(
      name: string,
      content: string,
      type: TemplateType
    ): Promise<Template> {
      const typeStr: String = TemplateType[type];
      return myFetch(`${API_PATH}/templates`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name,
          template: content,
          type: typeStr,
        }),
      }).then((req) => req.json());
    },
    read(id: number): Promise<Template> {
      return myFetch(`${API_PATH}/templates/${id}`, {
        method: "GET",
      }).then((req) => req.json());
    },
    update(
      id: number,
      name: string,
      content: string,
      type: TemplateType
    ): Promise<Template> {
      const typeStr: String = TemplateType[type];

      return myFetch(`${API_PATH}/templates/${id}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: name || undefined,
          template: content || undefined,
          type: typeStr || undefined,
        }),
      }).then((req) => req.json());
    },
    delete(id: number) {
      return myFetch(`${API_PATH}/templates/${id}`, {
        method: "DELETE",
      });
    },
    attributes(id: number): Promise<string[]> {
      return myFetch(`${API_PATH}/templates/${id}/attributes`)
        .then((res) => res.json())
        .then((data) => data.fields);
    },
    print(id: number, attributes: Record<string, string>) {
      return myFetch(`${API_PATH}/templates/${id}/print`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          fields: attributes,
        }),
      });
    },

    previewSrc(id: number) {
      return `${API_PATH}/templates/${id}/preview`;
    },
    fetchImage(url: string) {
      return myFetch(url, {headers: {"Accept": "image/png"}})
        .then(response => response.blob())
    },

    download(id: number) {
      return `${API_PATH}/templates/${id}/templated/download`;
    },

    readLabelSize(): Promise<{ width: number; height: number }> {
      return myFetch(`${API_PATH}/configuration/size`).then((req) =>
        req.json()
      );
    },
    updateLabelSize(width: number, height: number) {
      return myFetch(`${API_PATH}/configuration/size`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          width,
          height,
        }),
      });
    },
  },
  labels: {
    previewSrc(id: number, fields: Record<string, string>) {
      const queryString = Object.entries(fields)
        .map(([key, value]) => `${encodeURI(key)}=${encodeURI(value)}`)
        .join("&");

      return `${API_PATH}/templates/${id}/preview?${queryString}`;
    },
    previewImages(id: number, fields: Record<string, string>) {
      const url = this.previewSrc(id, fields);

      return myFetch(url, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });
    },
  },
};

export default API;
