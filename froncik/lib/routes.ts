const routes = {
  home: () => '/',
  add: () => '/template/add',
  edit: (id: number) => `/template/edit/${id}`,
  print: (id: number) => `/template/print/${id}`,
}

export default routes;
