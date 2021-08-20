import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { plainToClass } from 'class-transformer';

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";
import { Point } from "@/model/Point";
import { Place } from "@/model/Place";

const placeUrl = "/folklores";

const placeApi = {
  castResponse(data: Place): Place {
    return plainToClass(Place, data);
  },

  castResponses(data: Place[]): Place[] {
    return data.map(val => this.castResponse(val));
  },

  getPlaceById(id: number): Promise<AxiosResponse<Place>> {
    return axiosApi.get(`${placeUrl}/${id}`);
  },

  getPlaceInfoById(id: number): Promise<AxiosResponse<Place>> {
    return axiosApi.get(`${placeUrl}/${id}/info`);
  },

  getPlacesByName(
    name: string,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    const params = new URLSearchParams();
    params.set("name", name);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${placeUrl}/search?${params.toString()}`);
  },

  getPlacesByTags(
    tags: Tag[] | null,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(`${placeUrl}?${params.toString()}`, tags);
  },

  getNerbyPlaces(
    point: Point,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    // FIXME: crete query
    return this.getPlacesByTags(null, size, page);
  },
};

export default placeApi;