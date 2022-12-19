# QUICKSTART
Just use docker, not sure though if it will work.

To build: `docker build . -t labella`.  
To run: ```docker run -e printerName=ZPL_ZPL_Label_Printer -e LABEL_WIDTH=465 -e LABEL_HEIGHT=356  --net=host -v "`pwd`/data:/data:rw" labella```.  
You may need to use `--priviliged` in podman PLS FIX.


# Developer start
You should just run it as a normal java developer.  
You are not? Lol, use `mvn spring-boot:run -DprinterName=ZebraCups`.  
Change the `-DprinterName=???` variable to the printer name in your CUPS or something.  
See the code in LpCliLanguagePrinterService if it ain't working, lol.
Prolly need Inkscape, lp and whatsnot, errors will guide you gl;hf.

## Development

We are using Hexagon here.  
In model sleeps ddd.  
There are noun named services that are building bricks.  
There are verb named services which execute business case.  
Of course there are also controllers.  
TemplateCRUD is a controller, and a repository - Spring Data Rest magic.  
MainConfiguration constructs whole application + Spring Scanning magic for Controllers and Repositories.

The more you know Spring the better.

### Hexagonal: Convention in deep

Three layers
1. model - model + ports
2. application - application services + adapters, ports
3. peripherals - controllers + adapters

rules:
- layers below don't know anything about layers above
- layers below expose interfaces as ports
- layers above may implement these interfaces as adapters and pass them bellow

that results in:
- Model rules are as clean as possible without any additional logic that would fog the vision
- Application is world agnostic and made be run everywhere it needs
- Peripherals may use any database or framework - they need just to fulfill the ports

### Additional stuff that should be known

- We are using Spring magic heavily in controllers - it is used also in BeanConfiguration to create application services.  
- We are using `Spring Data Rest` here. See TemplateCRUD, SpringDataRestConfiguration and Swagger for behaviour.  
    - It was done to accelerate the shipping speed for frontend collaboration - now I see it as a code debt.  
    - Why? It is hard to understand what is going on here and non-intuitive in tweaking.  
- We have swagger here, but I forgot the url.

## Production

If you are running it outside the docker, then please run:  
`java -jar -Dspring.datasource.url=jdbc:h2:file:./path/to/labella/db -DprinterName=??? -Dserver.port=8080 -Dlabel.width=??? -Dlabel.height=??? app.jar`  
Best build by gettobuild.sh, it will include also frontend.

Of course docker build is also cool.  
However, labella was created to enable hosting on raspberry pies and such.  
(Yes, I know it is Java)
