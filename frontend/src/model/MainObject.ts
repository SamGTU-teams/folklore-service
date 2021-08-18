import { Tag } from "@/model/Tag";
import { Point } from "@/model/Point";

export interface MainObject {
  id: number;
  name: string;
  address: string;
  point: Point;
  labelUrl: string;
  imageUrl: string;
  tags: Tag[];
  mediaUrls?: string[];
  description?: string;
}
