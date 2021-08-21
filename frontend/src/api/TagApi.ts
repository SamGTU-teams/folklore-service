import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";

const tagUrl = "/tags";

const tagApi = {
  findTagsByName(
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

  findChildrenById(
    id: string,
    size: number,
    page: number): Promise<AxiosResponse<Page<Tag>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${tagUrl}/${id}/children?${params.toString()}`);

  },

  findTagById(id: string): Promise<AxiosResponse<Tag>> {
    return axiosApi.get(`${tagUrl}/${id}`);
  },
};

export default tagApi;
