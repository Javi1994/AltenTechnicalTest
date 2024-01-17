### Features

#### Lista de usuarios
Se trata de una aplicación que lo que hace es motrar un listado de usuarios recogidos de la pagina https://randomuser.me/api/
La pantalla de listado carga unos datos iniciales de 10 usuarios de esta pagina y si vas scrolleando y llegas al final del scroll se recogen 10 usuarios más, asi de forma infinita.

La pantalla dispone de un simple buscador que filtra los usuarios en local tanto por correo como por nombre. La función de scroll infinito no funciona mientras se buscan usuarios

![contact_list_image1](https://github.com/Javi1994/AltenTechnicalTest/blob/master/images/Captura.JPG)
![contact_list_image2](https://github.com/Javi1994/AltenTechnicalTest/blob/master/images/Captura2.JPG)

#### Detalle de usuario
Cuando desde la pantalla de listado de usuarios le damos a un usuario accederemos a la pantalla de detalle de usuario donde solo se trata de una pantalla que muestra la información del usuario.
Se ha implementado una forma de editar los campos clickando en el botón de editar pero no se guardan en el propio usuario ni está enlazado a ninguna api.

![contact_detail_image1](https://github.com/Javi1994/AltenTechnicalTest/blob/master/images/contact_detail.JPG)
![contact_detail_image2](https://github.com/Javi1994/AltenTechnicalTest/blob/master/images/contact_detail_edit.JPG)

### App Structure
Para la estructura del proyecto de ha optado por hacer la aplicación con Clean Architecture donde cada capa se trata de un modulo y hemos añadido el modulo de Common para cosas comunes a todas las capas.

![app_structure](https://github.com/Javi1994/AltenTechnicalTest/blob/master/images/Captura5.JPG)

### Librerias/tecnologias utilizadas
- Compose junto con material3 para las vistas
- Coil para la carga de imagenes asíncrona
- Koin para inyeccion de dependencias
- Retrofit para las peticiones
- Coroutinas para la emision asíncrona de datos
- Gradle catalogs para la gestión de dependencias

### Problemas encontrados durante el desarrollo
En la pantalla de detalle de usuario me ha costado mucho centrar la imagen entre la imagen de fondo y el contenido de la propia pantalla y he dado con una implementación que funciona pero podria haber problemas en dispositivos pequeños y habria que hacerlo por porcentaje de pantalla en vez de dp o con dimensiones.



