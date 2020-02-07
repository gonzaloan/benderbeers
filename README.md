# BENDER Microservicios Test

Este es una API de prueba. 

Está hosteada en AWS por lo que se pueden probar los endpoints a través de la ruta:

`ec2-18-231-123-190.sa-east-1.compute.amazonaws.com`


Es un proyecto realizado con Spring Boot y Java 8, y una base de dato en memoria H2.
Tiene un par de datos que se almacenan automáticamente al levantar la aplicación,
por lo que se puede visualizar info al hacer un get del endpoint BEERS.

Ejemplo: 

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" ec2-18-231-123-190.sa-east-1.compute.amazonaws.com/beers`

Retorna información sobre las cervezas que existen. 


### Generación de Docker en AWS.

Como se indica más arriba, este proyecto ya está hosteado en un EC2 de AWS.

Para generar el contenedor y correrlo se realiza lo siguiente:

- Ingresar a la ruta del proyecto y clonar el repositorio de Bender Beers
- Generar el Package del proyecto mediante un mvn package.
- ```docker build -t bender-api .```
- ```docker run -d -p 80:8080 bender-api```


