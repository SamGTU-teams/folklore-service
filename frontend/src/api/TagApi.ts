import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";

const tagUrl = "/tags";

const tagApi = {
  getTags(size: number, page: number): Promise<AxiosResponse<Page<Tag>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${tagUrl}?${params.toString()}`);
  },

  getTagsByName(
    name: string,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Tag>>> {
    const params = new URLSearchParams();
    params.set("name", name);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${tagUrl}/search?${params.toString()}`);
  },

  getTagById(id: number): Promise<AxiosResponse<Tag>> {
    return axiosApi.get(`${tagUrl}/${id}`);
  },
};

export default tagApi;
export { Tag, Page };
