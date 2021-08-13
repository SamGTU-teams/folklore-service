import axios, { AxiosResponse } from "axios";
import Tag from "@/model/Tag";
import Page from "@/model/Page";
import Building from "@/model/Building";

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
const buildingUrl = "/api/folklores";

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

const buildingApi = {
  getBuildingById(id: number): Promise<AxiosResponse<Building>> {
    return axios.get(`${buildingUrl}/${id}`);
  },

  getBuildingInfoById(id: number): Promise<AxiosResponse<Building>> {
    return axios.get(`${buildingUrl}/${id}/info`);
  },

  getBuildingsByName(
    name: string,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Building>>> {
    const params = new URLSearchParams();
    params.set("name", name);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${buildingUrl}/search?${params.toString()}`);
  },

  getBuildingsByTags(
    tags: Tag[] | null,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Building>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(`${buildingUrl}?${params.toString()}`, tags);
  },

  getNerbyBuildings(
    lat: number,
    lon: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Building>>> {
    return this.getBuildingsByTags(null, size, page);
  },
};

export { tagApi, buildingApi, Tag, Building, Page };
