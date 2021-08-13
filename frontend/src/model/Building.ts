interface Building {
  id: number;
  name: string;
  address: string,
  lat: number;
  lon: number;
  labelUrl: string;
  imageUrl: string;
  mediaUrls?: Array<string>;
  description?: string;
}

export { Building }