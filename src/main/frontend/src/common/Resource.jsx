export default class Resource {
  links;
  constructor(links) {
    this.links = links ? links : [];
  }

  link(key) {
    let link = this.links[key].href;
    return link ? link : null;
  }
}
