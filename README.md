# LABELLA

is a labello done right.  
No idea what a labello is?  
Good.

## What is it

It is a templating and printing application for Zebra/EPL printers + anything you want to add to this project.   
You want to create a modificable image?: Just use '${name}' inside the svg file.  
You want to generate a qr code?: use qrCode attribute on image node in svg file.  
But images speak better:

![LABELLO](./img/ps.png)
![PRINT](./img/print.png)
![PRINTED](./img/PRINTED.jpg)

Just tinker with it and as things, so I can add to FAQ.

## RUNNING

See [backend readme](./backend/README.md) for more info.  
Normally run either by docker:
```
docker build . -t labella
docker run -v PRINTER_NAME=ZEBRA_NAME_IN_CUPS --net=host -v "`pwd`/data:/data" labella
```

or:

```
./gettobuild.sh
java -jar -Dspring.datasource.url=jdbc:h2:file:./db -DprinterName=EPL1_EPL1_Label_Printer -Dserver.port=8080 app.jar
```

### DEVELOPMENT


See [backend readme - Developer Start](./backend/README.md) for more info.  

and  

See [frontend dev](./froncik/DEV.md) for more info.  


### Prerequisites
- Node: v16.16.0
- Java: openjdk version "11.0.17" 2022-10-18
- Mvn: Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
- Linux
- Inkscape 1.2.1 (not matching this version may give your image glitches and stitches)
- Lp 

Or just run it via docker and save yourself du pain.


