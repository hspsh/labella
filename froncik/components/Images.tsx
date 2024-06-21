import {CardImg, Carousel, CarouselItem} from "react-bootstrap";
import API from "../lib/api";
import React, {useCallback, useContext, useEffect, useState} from "react";
import _ from "lodash";
import {TemplatesContext} from "./teplatesStore";

const debounceTime = 2000;
export default function Images({id, fields}: { id: number, fields: Record<string, string> }) {

  const [images, loadImages] = useState<undefined | string[]>(undefined)
  const [updatingImages, setUpdatingImages] = useState(false);
  const ctx = useContext(TemplatesContext)

  const debouncedQuery = useCallback(_.debounce((fun) =>
    ctx.withLoading(() =>
      fun()
    ), debounceTime
  ), [])

  useEffect(() => {
    debouncedQuery(
      () => {
          setUpdatingImages(true)
          API.labels.previewImages(id, fields)
              .then((x) => {
                  x.json().then(x => loadImages(x.images))
              })
              .finally(() => setUpdatingImages(false))
      }
    )
  }, [id, fields])

  return images ?
      <>
          <div className={updatingImages ? 'updating-images' : ''}>
              {images.length == 1 ? <CardImg src={`data:image/jpeg;base64,${images[0]}`}/> : <Carousel variant={"dark"}>{
                  images.map(image =>
                      <CarouselItem key={image}>
                          <img style={{width: "100%"}} src={`data:image/jpeg;base64,${image}`}/>
                      </CarouselItem>
                  )
              }</Carousel>}
          </div>
          {updatingImages && <img className={"preview-preloader"} src={"/preloader.svg"}/>}
      </>
    : <div>Loading...</div>
}



