# EasyBooking

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Spanish

Repo con una aerolínea ficticia llamada "EasyBooking". El objetivo de este trabajo es el de empaparnos en diversos conceptos:

- Uso de [Apache Ant](https://ant.apache.org/ "Página principal de Apache Ant").
- Uso de patrones de Diseño de Software:

## En el lado cliente

	- Controller (+ Singleton).
	- ServiceLocator.

## En el lado servidor

	- Remote Façade.
	- DAO (+ Factory).
	- DTO.
	- Gateway.
	- Singleton en todos los Assemblers.
	- Strategy (variación hecha con interfaces en vez de con clases abstractas).

- Uso de herramientas de comunicación entre proyectos (RMI, Sockets).
- Uso de acceso a Base de Datos relacional MySQL con DataNucleus.

## Ejecución

Para que todo vaya bien, debéis hacer los siguientes pasos (después de hacer el pull o clone correspondiente; se asume uso de IDE Eclipse para Java en SO Windows):

1) Abrir el MySQL Workbench.
2) Abrir el script de SQL ubicado en ```EasyBooking_Server/db```.
3) Correr el script usando el rayito vía MySQL Workbench. Recargar en Schemas.
4) Lanzar el objetivo ```createschema``` del fichero ```build.xml``` de la carpeta ```EasyBooking_Server```.
5) Eliminar el JAR en la carpeta ```EasyBooking_Server/dist```.
6) Lanzar el objetivo ```export``` del fichero ```build.xml``` de la carpeta ```EasyBooking_Server```.
7) Pulsar F5 sobre la carpeta ```EasyBooking_Server/dist```.
8) Lanzar el objetivo ```export``` del fichero ```build.xml``` de la carpeta ```EasyBooking_RMIAirServer```.
9) Pulsar F5 sobre la carpeta ```EasyBooking_Server/dist```.
10) Eliminar los JARs en la carpeta ```EasyBooking_Client/lib```.
11) Copiar los nuevos JARs de las respectivas carpetas ```EasyBooking_Server/dist``` y ```EasyBooking_RMIAirServer/dist``` a la carpeta ```EasyBooking_Client/lib```.

if (No es la primera ejecución, habrá que matar ciertos procesos corriendo en segundo plano)
{

	- Ir al Administrador de Tareas (Ctrl + Alt + Supr).
	- Abrir Procesos en "Más Detalles".
	- Buscar y Finalizar Tarea en "Procesos en segundo plano" todos los que tengan como nombre:
	- Java(TM) Platform SE binary.

}

12) Abrir el fichero ```EasyBooking_Server/registry.bat```.
13) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Gateway ```EasyBooking_GoogleAuthServer```.
14) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Gateway ```EasyBooking_FBAuthServer```.
15) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Gateway ```EasyBooking_PayCardServer```.
16) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Gateway ```EasyBooking_PayPalServer```.
17) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Gateway ```EasyBooking_SocketAirServer```.
18) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Servidor ```EasyBooking_RMIAirServer```.
19) Iniciar el objetivo ```server``` del fichero ```build.xml``` del Servidor ```EasyBooking_Server```.
20) Iniciar el objetivo ```client``` del fichero ```build.xml``` del Cliente ```EasyBooking_Client```.

# Autores

- Garikoitz Bereciartua ([garibere13](https://github.com/garibere13 "Perfil de GitHub de Gari Bereciartua"))
- Imanol Echeverría ([Echever](https://github.com/Echever "Perfil de GitHub de Imanol Echeverría"))
- Beñat Galdós ([Benny96](https://github.com/Benny96 "Perfil de GitHub de Beñat Galdós"))
- Anne Idigoras ([anneidigoras](https://github.com/anneidigoras "Perfil de GitHub de Anne Idigoras"))

## English

Repo containing a fictional airline called "EasyBooking". The aim of this project is to delve into certain topics:

- [Apache Ant](https://ant.apache.org/ "Apache Ant landing page") usage.
- Software Design pattern usage:

## On the Client side:

	- Controller (+ Singleton).
	- ServiceLocator.

## On the Server side:

	- Remote Façade.
	- DAO (+ Factory).
	- DTO.
	- Gateway.
	- Singleton on every single Assembler.
	- Strategy (variation built with interfaces instead of abstract classes).

- Interproject communication tools usage (RMI, Sockets).
- MySQL Relational Database access management with DataNucleus.

## Running the code

After cloning or pulling this project, several steps must be followed to run the code properly (tested for Eclipse IDE in Windows OS):

1) Open MySQL Workbench tool.
2) Open the SQL script located in ```EasyBooking_Server/db```.
3) Run the script via MySQL Workbench and reload to check if the Schema was generated.
4) Run the ```createschema``` goal of the ```build.xml``` file of the ```EasyBooking_Server``` folder.
5) Remove the JAR file located in ```EasyBooking_Server/dist```.
6) Run the ```export``` goal of the ```build.xml``` file of the ```EasyBooking_Server``` folder.
7) Press F5 to reload the content located in ```EasyBooking_Server/dist```.
8) Run the ```export``` goal of the ```build.xml``` file of the ```EasyBooking_RMIAirServer``` folder.
9) Press F5 to reload the content located in ```EasyBooking_RMIAirServer/dist```.
10) Remove the JAR files located in ```EasyBooking_Client/lib```.
11) Copy the newly generated JARs from ```EasyBooking_Server/dist``` and ```EasyBooking_RMIAirServer/dist``` to the ```EasyBooking_Client/lib``` folder.

if (It is not the first execution, you will need to kill certain processes running on the background)
{

	- Move to the Task Manager (Ctrl + Alt + Del).
	- Open More Details/Processes.
	- Find and Kill tasks running on the background called "Java(TM) Platform SE binary". It is assumed that no other Java executables are running.

}

12) Open the ```EasyBooking_Server/registry.bat``` file.
13) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_GoogleAuthServer``` Gateway.
14) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_FBAuthServer``` Gateway.
15) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_PayCardServer``` Gateway.
16) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_PayPalServer``` Gateway.
17) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_SocketAirServer``` Gateway.
18) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_RMIAirServer``` Server.
19) Run the ```server``` goal of the ```build.xml``` file of the ```EasyBooking_Server``` Server.
20) Run the ```client``` goal of the ```build.xml``` file of the ```EasyBooking_Client``` Client.

# Authors

- Garikoitz Bereciartua ([garibere13](https://github.com/garibere13 "Gari Bereciartua's GitHub Profile")).
- Imanol Echeverría ([Echever](https://github.com/Echever "Imanol Echeverría's GitHub Profile")).
- Beñat Galdós ([Benny96](https://github.com/Benny96 "Beñat Galdós' GitHub Profile")).
- Anne Idigoras ([anneidigoras](https://github.com/anneidigoras "Anne Idigoras' GitHub Profile")).
