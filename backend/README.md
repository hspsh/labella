# QUICKSTART
Just use docker, not sure though if it will work.

To build: `docker build . -t labella`.  
To run: ```docker run -v PRINTER_NAME=ZEBRA_NAME_IN_CUPS --net=host -v "`pwd`/data:/data" labella```.

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
MainConfiguration constructs whole application + Spring Scanning magic.

The more you know Spring the better.

## Production

If you are running it outside the docker, then please run:
`java -Dspring.datasource.url=jdbc:h2:file:/path/to/labella/db -DprinterName=??? -Dserver.port=8080 -jar app.jar`
Best build by gettobuild.sh, it will build also frontend.
