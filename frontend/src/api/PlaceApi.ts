import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { plainToClass } from 'class-transformer';

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";
import { Point } from "@/model/Point";
import { Place } from "@/model/Place";

const placeUrl = "/places";

const placeApi = {
  castResponse(data: Place): Place {
    return plainToClass(Place, data);
  },

  castResponses(data: Place[]): Place[] {
    return data.map(val => this.castResponse(val));
  },

  findPlacesByTags(
    tags: Tag[],
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    let tagIds = tags.map((tag) => tag.id);
    return this.findPlacesByTagsIds(tagIds, size, page);
  },

  findPlacesByTagsIds(
    tags: string[] | null,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    if (tags === null) tags = [];
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(`${placeUrl}?${params.toString()}`, tags);
  },

  findPlacesByName(
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

  findPlaceById(id: number): Promise<AxiosResponse<Place>> {
    return axiosApi.get(`${placeUrl}/${id}`);
  },

  findPlaceInfoById(id: number): Promise<AxiosResponse<Place>> {
    return axiosApi.get(`${placeUrl}/${id}/info`);
  },

  findPlacesByIds(ids: number[]): Promise<AxiosResponse<Place[]>> {
    return axiosApi.post(`${placeUrl}/ids`, ids);
  },

  findByRegionId(regionId: number): Promise<AxiosResponse<Place[]>> {
    return axiosApi.get(`${placeUrl}/region/${regionId}`)
  },

  findNearbyPlaces(
    point: Point,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    // FIXME: crete query
    return this.findPlacesByTagsIds(null, size, page);
  },
};

export default placeApi;
