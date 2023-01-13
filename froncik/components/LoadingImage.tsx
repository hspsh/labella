import {useContext, useEffect, useState} from "react";
import {TemplatesContext} from "./teplatesStore";
import API from "../lib/api";
import {Card, CardImg, Spinner} from "react-bootstrap";

export function LoadingImage({src}: { src: string }) {

  const context = useContext(TemplatesContext)
  const [state, setState] = useState<undefined | string>(undefined)

  useEffect(() => {
    context.withLoading(() =>
      API.templates.fetchImage(src)
        .then(url => setState(url))
    )
  }, [])

  return state == undefined ? <Spinner animation="border"/> : <Card.Img
    src={src}
    alt="Card image"
    style={
      {"--bs-card-inner-border-radius": 0} as React.CSSProperties
    }
  />
}