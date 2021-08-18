import { Tag } from "@/model/Tag";
import { Place } from "@/model/Place";
import { Point } from "@/model/Point";
import { MainObject } from "@/model/MainObject";

export class Activity extends MainObject {
  place?: Place;

  constructor(
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
  ) {
    super(
      id,
      name,
      address,
      point,
      labelUrl,
      imageUrl,
      tags,
      mediaUrls,
      description
    );
    this.place = place;
  }
}
