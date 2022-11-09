import Template from "../common/Template";

const API_PATH = "http://localhost:8080";

const API = {
  templates: {
    list(): Promise<Template[]> {
      return fetch(`${API_PATH}/templates`)
        .then((req) => req.json())
        .then((data) => data._embedded.templates as Template[]);
    },
    create(name: string, content: string): Promise<Template> {
      return fetch(`${API_PATH}/templates`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name,
          template: content,
        }),
      }).then((req) => req.json());
    },
    read(id: number): Promise<Template> {
      return fetch(`${API_PATH}/templates/${id}`, {
        method: "GET",
      }).then((req) => req.json());
    },
    update(id: number, name: string, content: string): Promise<Template> {
      return fetch(`${API_PATH}/templates/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name,
          template: content,
        }),
      }).then((req) => req.json());
    },
    delete(id: number) {
      return fetch(`${API_PATH}/templates/${id}`, {
        method: "DELETE",
      });
    },
  },
};

export default API;
