import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";
import { Activity } from "@/model/Activity";

const activityUrl = "/activities";

const activityApi = {
  getActivityById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}`);
  },

  getActivityInfoById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}/info`);
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

  getActivitiesByPlaceId(
    placeId: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    // FIXME: create query
    return axiosApi.get(`${activityUrl}/${params.toString}`)
  },
};

export default activityApi;
export { Tag, Activity, Page };
