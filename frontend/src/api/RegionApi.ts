import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { plainToClass } from 'class-transformer';

import { Page } from "@/model/Page";
import { Region } from "@/model/Region";

const regionUrl = "/regions";

const regionApi = {
  castResponse(data: Region): Region {
    data.points = JSON.parse(data.points.toString());
    return plainToClass(Region, data);
  },

  castResponses(data: Region[]): Region[] {
    return data.map((val) => this.castResponse(val));
  },

  findRegionById(id: number): Promise<AxiosResponse<Region>> {
    return axiosApi.get(`${regionUrl}/${id}`);
  },

  findRegionsByName(
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
    return axiosApi.get(`${regionUrl}?${params.toString()}`);
  },
};

export default regionApi;
