# EasyBooking

Repo con una aerolínea ficticia llamada "EasyBooking". El objetivo de este trabajo es el de empaparnos en diversos conceptos:

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

Para que todo vaya bien, debéis hacer los siguientes pasos (después de hacer el pull o clone correspondiente):

1) Abrir el MySQL Workbench.
2) Abrir el script de SQL que está en la carpeta db de EasyBooking_Server.
3) Correr el script usando el rayito vía MySQL Workbench. Recargar en Schemas.
4) En Eclipse, usar mediante el build.xml del EasyBooking_Server el createschema.
5) Eliminar el JAR en la carpeta "dist" del EasyBooking_Server.
6) En Eclipse, usar mediante el build.xml del EasyBooking_Server el export.
7) Pulsar F5 sobre la carpeta "dist" del EasyBooking_Server.
8) En Eclipse, usar mediante el build.xml del EasyBooking_RMIAirServer el export.
9) Pulsar F5 sobre la carpeta "dist" del EasyBooking_RMIAirServer.
10) Eliminar los JARs en la carpeta "lib" del EasyBooking_Client.
11) Copiar los nuevos JARs de las respectivas carpetas "dist" del EasyBooking_Server y del EasyBooking_RMI
AirServer a la carpeta "lib" del EasyBooking_SD_Client.

if (No es la primera ejecución)
{

	- Ir al Administrador de Tareas (Ctrl + Alt + Supr).
	- Abrir Procesos en "Más Detalles".
	- Buscar y Finalizar Tarea en "Procesos en segundo plano" todos los que tengan como nombre:
	- Java(TM) Platform SE binary.

}

12) Abrir el registry.bat en el EasyBooking_Server.
13) Iniciar el "server" en el build.xml del Gateway EasyBooking_GoogleAuthServer.
14) Iniciar el "server" en el build.xml del Gateway EasyBooking_FBAuthServer.
15) Iniciar el "server" en el build.xml del Gateway EasyBooking_PayCardServer.
16) Iniciar el "server" en el build.xml del Gateway EasyBooking_PayPalServer.
17) Iniciar el "server" en el build.xml del Gateway Easybooking_SocketAirServer.
18) Iniciar el "server" en el build.xml del Servidor EasyBooking_RMIAirServer.
19) Pulsar "server" en el build.xml de EasyBooking_Server.
20) Pulsar "client" en el build.xml de EasyBooking_Client.

# Autores

- Garikoitz Bereciartua (garibere13) 
- Imanol Echeverría (Echever)
- Anne Idigoras (anneidigoras)
- y yo, Beñat Galdós (Benny96). 