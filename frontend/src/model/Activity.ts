import { Tag } from "@/model/Tag";
import { Place } from "@/model/Place";
import { Point } from "@/model/Point";
import { MainObject } from "@/model/MainObject";
import { CardWithRationgInfo } from "@/model/CardWithRationgInfo";

export class Activity extends MainObject implements CardWithRationgInfo {
  rating: number;
  canVisit: boolean;
  place?: Place;
  duration: Date;

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
    canVisit: boolean,
    duration: Date,
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
    this.canVisit = canVisit;
    this.rating = rating;
    this.duration = duration;
    this.place = place;
  }
}
