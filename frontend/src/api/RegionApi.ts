import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { Page } from "@/model/Page";
import { Region } from "@/model/Region";

const regionUrl = "/regions";

const regionApi = {
  getRegionById(id: number): Promise<AxiosResponse<Region>> {
    return axiosApi.get(`${regionUrl}/${id}`);
  },

  getRegionsByName(
    name: string,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Region>>> {
    const params = new URLSearchParams();
    params.set("name", name);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${regionUrl}/search?${params.toString()}`);
  },

  getRegions(size: number, page: number): Promise<AxiosResponse<Page<Region>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    // FIXME: create query
    return axiosApi.get(`${regionUrl}?${params.toString()}`);
  },
};

export default regionApi;
