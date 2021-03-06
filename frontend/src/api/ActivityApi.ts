import axiosApi, { AxiosResponse } from "@/api/AxiosApi";

import { plainToClass } from "class-transformer";

import { Page } from "@/model/Page";
import { Tag } from "@/model/Tag";
import { Activity } from "@/model/Activity";
import { ActivityStatus } from "@/model/ActivityStatus";
import { ScheduledActivity } from "@/model/ScheduledActivity";

const activityUrl = "/activities";

const activityApi = {
  castResponse(data: Activity): Activity {
    return plainToClass(Activity, data);
  },

  castResponses(data: Activity[]): Activity[] {
    return data.map((val) => this.castResponse(val));
  },

  findActivitiesByTags(
    tags: Tag[],
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const tagIds = tags.map((tag) => tag.id);
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

  getActivities(
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    return this.findActivitiesByTagsIds([], size, page);
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

  findActivitiesByPlaceId(
    placeId: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(`${activityUrl}/place/${placeId}?${params.toString()}`);
  },

  findActivityById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}`);
  },

  findActivityInfoById(id: number): Promise<AxiosResponse<Activity>> {
    return axiosApi.get(`${activityUrl}/${id}/info`);
  },

  findScheduledActivitiesByActivityId(
    activityId: number,
    size: number,
    page: number,
    statuses: ActivityStatus[] = []
  ): Promise<AxiosResponse<ScheduledActivity>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(
      `${activityUrl}/${activityId}/scheduled?${params.toString()}`,
      statuses
    );
  },

  findActivitiesByDateTime(
    datetime: Date,
    size: number,
    page: number,
    statuses: ActivityStatus[] = []
  ): Promise<AxiosResponse<Page<ScheduledActivity>>> {
    const params = new URLSearchParams();
    params.set("from", datetime.toISOString().split('.')[0]);
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.post(
      `${activityUrl}/search/date?${params.toString()}`,
      statuses
    );
  },

  findActivitiesByIds(ids: number[]): Promise<AxiosResponse<Activity[]>> {
    return axiosApi.post(`${activityUrl}/ids`, ids);
  },

  findByRegionId(
    regionId: number,
    size: number,
    page: number
  ): Promise<AxiosResponse<Page<Activity>>> {
    const params = new URLSearchParams();
    params.set("size", size.toString());
    params.set("page", page.toString());
    return axiosApi.get(
      `${activityUrl}/region/${regionId}?${params.toString()}`
    );
  },
};

export default activityApi;
