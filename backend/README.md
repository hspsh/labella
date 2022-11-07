# QUICKSTART
Just use docker, not sure though if it will work.  
Your printer has to be named `printerName=EPL1_EPL1_Label_Printer`.  
FIXME LOL.

# Developer start
You should just run it as a normal java developer.  
You are not? Lol, use `mvn spring-boot:run -DprinterName=ZebraCups`.  
Change the `-DprinterName=???` variable to the printer name in your CUPS or something.  
See the code in LanguagePrinterServiceImpl if it ain't working, lol.

## Development

We are using Hexagon here.  
In model sleeps ddd.  
There are noun named services that are building bricks.  
There are verb named services which execute business case.  
Of course there are also controllers.  
TemplateCRUD is a controller, and a repository - Spring Data Rest magic.  
MainConfiguration constructs whole application + Spring Scanning magic.

The more you know Spring the better.

