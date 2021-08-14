import axios, { AxiosResponse } from "axios";
import Tag from "@/model/Tag";
import Page from "@/model/Page";
import Place from "@/model/Place";
import Activity from "@/model/Activity";

const axiosApi = axios.create({
  baseURL: `/api`,
  timeout: 1000,
  headers: {
    "Content-Type": "application/json",
    // CORS params
    // "Access-Control-Allow-Origin": [
    //   "http://localhost:8080",
    //   "http://localhost:8081",
    //   "https://rassafel-folklore-service.herokuapp.com/",
    // ],
    // "Access-Control-Allow-Methods": "GET,POST,DELETE,PUT,OPTIONS",
    // "Access-Control-Allow-Headers": "*",
    // "Access-Control-Allow-Credentials": true,
  },
});

const tagUrl = "/api/tags";
const placeUrl = "/api/folklores";
const activityUrl = "/api/activities";

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

const placeApi = {
  getPlaceById(id: number): Promise<AxiosResponse<Place>> {
    return axios.get(`${placeUrl}/${id}`);
  },

  getPlaceInfoById(id: number): Promise<AxiosResponse<Place>> {
    return axios.get(`${placeUrl}/${id}/info`);
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
    lat: number,
    lon: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Place>>> {
    return this.getPlacesByTags(null, size, page);
  },
};

const activityApi = {
  getActivityById(id: number): Promise<AxiosResponse<Activity>> {
    return axios.get(`${activityUrl}/${id}`);
  },

  getActivityInfoById(id: number): Promise<AxiosResponse<Activity>> {
    return axios.get(`${activityUrl}/${id}/info`);
  },

  getActivitiesByName(
    name: string,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("name", name);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${activityUrl}/search?${params.toString()}`);
  },

  getActivitiesByTags(
    tags: Tag[] | null,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(`${activityUrl}?${params.toString()}`, tags);
  },
};

export { tagApi, placeApi, activityApi, Tag, Place, Page };
