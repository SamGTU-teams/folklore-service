import { Tag } from "@/model/Tag";
import { Place } from "@/model/Place";
import { Point } from "@/model/Point";

export interface Activity {
  id: number,
  name: string,
  address: string,
  point: Point,
  labelUrl: string,
  imageUrl: string,
  tags: Tag[],
  mediaUrls?: string[],
  description?: string,
  place?: Place
}