import {Carousel, CarouselItem} from "react-bootstrap";
import API from "../lib/api";
import React, {useEffect, useState} from "react";


export default function Image({id, fields}: { id: number, fields: Record<string, string> }) {

  const [images, loadImages] = useState<undefined | string[]>(undefined)

  useEffect(() => {
    API.labels.previewImages(id, fields)
      .then((x) => {
        x.json().then(x => loadImages(x.images))
      })
  }, [])


  return images ?
    <Carousel>{
      images.map(image =>
        <CarouselItem key={image}>
          <img style={{width: "100%"}} src={`data:image/jpeg;base64,${image}`}/>
        </CarouselItem>
      )
    }</Carousel>
    : <div>Loading...</div>
}



