import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { plainToClass } from 'class-transformer';

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";
import { Activity } from "@/model/Activity";

const activityUrl = "/activities";

const activityApi = {
  castResponse(data: Activity): Activity {
    return plainToClass(Activity, data);
  },

  castResponses(data: Activity[]): Activity[] {
    return data.map(val => this.castResponse(val));
  },

  getActivityById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}`);
  },

  findActivityInfoById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}/info`);
  },

  getActivitiesByIds(ids: number[]): Promise<AxiosResponse<Activity[]>> {
    return axiosApi.post(`${activityUrl}/ids`, ids);
  },

  findActivitiesByName(
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

  findActivitiesByTags(
    tags: Tag[],
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    let tagIds = tags.map((tag) => tag.id);
    return this.findActivitiesByTagsIds(tagIds, size, page);
  },

  findActivitiesByTagsIds(
    tags: string[] | null,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    if (tags === null) tags = [];
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(`${activityUrl}?${params.toString()}`, tags);
  },

  findActivitiesByPlaceId(
    placeId: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    // FIXME: create query
    return axiosApi.get(`${activityUrl}/place/${placeId}?${params.toString}`)
  },

  findScheduledActivitiesByActivityId(
    activityId: number,
    size: number,
    page: number
  ) {
    // TODO: create query
    return;
  },

  findActivitiesByDateTime(
    datetime: Date,
    size: number,
    page: number
  ) {
    const params = new URLSearchParams();
    params.set("from", datetime.toISOString())
    params.set("size", size.toString());
    params.set("page", page.toString());
    // TODO: create query
    return;
  }
};

export default activityApi;
export { Tag, Activity, Page };
