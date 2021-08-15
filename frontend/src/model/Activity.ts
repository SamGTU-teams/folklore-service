import { Tag } from "@/model/Tag";

export default interface Activity {
  id: number;
  name: string;
  address: string;
  lat: number;
  lon: number;
  labelUrl: string;
  imageUrl: string;
  tags: Tag[];
  mediaUrls?: string[];
  description?: string;
}