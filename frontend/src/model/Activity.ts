import { Tag } from "@/model/Tag";
import { Place } from "@/model/Place";
import { Point } from "@/model/Point";
import { MainObject } from "@/model/MainObject";
import { CardWithRationgInfo } from "@/model/CardWithRationgInfo";

export class Activity extends MainObject implements CardWithRationgInfo {
  rating: number;
  place?: Place;

  constructor(
    id: number,
    name: string,
    address: string,
    point: Point,
    regionId: number,
    labelUrl: string,
    imageUrl: string,
    tags: Tag[],
    rating: number,
    mediaUrls?: string[],
    description?: string,
    place?: Place
  ) {
    super(
      id,
      name,
      address,
      point,
      regionId,
      labelUrl,
      imageUrl,
      tags,
      mediaUrls,
      description
    );
    this.rating = rating;
    this.place = place;
  }
}
