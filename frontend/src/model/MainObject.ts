import { Tag } from "@/model/Tag";
import { Point } from "@/model/Point";
import { CardInfo } from "@/model/CardInfo";

export class MainObject implements CardInfo {
  id: number;
  name: string;
  address: string;
  point: Point;
  regionId: number;
  labelUrl: string;
  imageUrl: string;
  tags: Tag[];
  mediaUrls?: string[];
  description?: string;

  constructor(
    id: number,
    name: string,
    address: string,
    point: Point,
    regionId: number,
    labelUrl: string,
    imageUrl: string,
    tags: Tag[],
    mediaUrls?: string[],
    description?: string
  ) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.point = point;
    this.regionId = regionId;
    this.labelUrl = labelUrl;
    this.imageUrl = imageUrl;
    this.tags = tags;
    this.mediaUrls = mediaUrls;
    this.description = description;
  }

  get title(): string {
    return this.name ?? '';
  }

  get subtitle(): string {
    return this.address ?? '';
  }

  get image(): string {
    return this.imageUrl ?? '';
  }
}
