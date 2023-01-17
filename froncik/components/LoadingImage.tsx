import {useContext, useEffect, useState} from "react";
import {TemplatesContext} from "./teplatesStore";
import API from "../lib/api";
import {Card, CardImg, Spinner} from "react-bootstrap";

export function LoadingImage({src}: { src: string }) {

  const context = useContext(TemplatesContext)
  const [state, setState] = useState<undefined | string>(undefined)

  useEffect(() => {
    state && URL.revokeObjectURL(state)
  }, [src])

  useEffect(() => {
    context.withLoading(async () => {
        return API.templates.fetchImage(src)
          .then(blob => URL.createObjectURL(blob))
          .then(url => setState(url))
      }
    )
  }, [])

  useEffect(() => () => {
    state && URL.revokeObjectURL(state)
  })


  return state == undefined ? <Spinner animation="border"/> : <Card.Img
    src={state}
    alt="Card image"
    style={
      {"--bs-card-inner-border-radius": 0} as React.CSSProperties
    }
  />
}