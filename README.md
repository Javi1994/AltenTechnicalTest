### Features

#### Lista de usuarios
Se trata de una aplicación que lo que hace es motrar un listado de usuarios recogidos de la pagina https://randomuser.me/api/
La pantalla de listado carga unos datos iniciales de 10 usuarios de esta pagina y si vas scrolleando se recogen 10 usuarios más, asi de forma infinita.

La pantalla dispone de un simple buscador que filtra los usuarios en local tanto por correo como por nombre. La función de scroll infinito no funciona mientras se buscan usuarios

![alt text]([http://url/to/img.png](https://drive.google.com/file/d/1M37n1vg1ajNtcN3D7RYwzy6Onp_sYZEh/view?usp=drive_link))
![alt text]([http://url/to/img.png](https://drive.google.com/file/d/14rgrvA-vjqcoQ_3JFdTfcJ2KT8d-7ZEw/view?usp=drive_link))

#### Detalle de usuario
Cuando desde la pantalla de listado de usuarios le damos a un usuario accederemos a la pantalla de detalle de usuario donde solo se trata de una pantalla que muestra la información del usuario.
Se ha implementado una forma de editar los campos clickando en el botón de editar pero no está enlazado al propio usuario ni a ninguna api.

![alt text]([http://url/to/img.png](https://drive.google.com/file/d/1Z-cVVpiF9--w2CoRqPZ0rI1g_ar1zO8m/view?usp=drive_link))
![alt text]([http://url/to/img.png](https://drive.google.com/file/d/1rOQXvXpHawVY2mRHBTvnHlWszYmfPsqw/view?usp=drive_link))

### App Structure
Para la estructura del proyecto de ha optado por hacer la aplicación con Clean Architecture donde cada capa se trata de un modulo y hemos añadido el modulo de Common para cosas comunes a todas las capas.

### Librerias/tecnologias utilizadas
- Compose junto con material3 para las vistas 
- Koin para inyeccion de dependencias
- Retrofit para las peticiones
- Coroutinas para la emision asincrona de datos
- Gradle catalogs para la gestión de dependencias


