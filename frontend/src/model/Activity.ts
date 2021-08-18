import { Tag } from "@/model/Tag";
import { Place } from "@/model/Place";
import { Point } from "@/model/Point";
import { MainObject } from "@/model/MainObject";

export interface Activity extends MainObject {
  place?: Place
}