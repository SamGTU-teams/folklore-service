import { Point } from "@/model/Point";

export class Region {
  id: number;
  name: string;
  points: Point[];
  imageUrl: string;

  constructor(id: number,
    name: string,
    points: Point[],
    imageUrl: string) {
      this.id = id;
      this.name = name;
      this.points = points;
      this.imageUrl = imageUrl;
    }
}
