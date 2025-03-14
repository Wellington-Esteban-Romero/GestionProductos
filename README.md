# GestionProductos

GestionProductos es una aplicación web desarrollada en **Java JakartaEE**, utilizando **JSP, JDBC y CDI**. Su función principal es gestionar productos, pedidos, categorías y usuarios con roles diferenciados.

## Tecnologías Utilizadas

- **Jakarta EE**
- **JSP (JavaServer Pages)**
- **JDBC (Java Database Connectivity)**
- **CDI (Contexts and Dependency Injection)**
- **HTML, CSS y JavaScript** para la interfaz de usuario

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

### Backend (**gestion/productos**)

- `configs/` - Configuraciones del proyecto
- `controllers/` - Controladores que manejan la lógica de negocio
- `dto/` - Objetos de transferencia de datos
- `exceptions/` - Manejo de excepciones
- `filters/` - Filtros de peticiones
- `interceptors/` - Interceptores CDI
- `listeners/` - Listeners para eventos del sistema
- `models/` - Modelos de datos
- `repositories/` - Acceso a base de datos con JDBC
- `services/` - Servicios que implementan la lógica de negocio
- `utils/` - Clases utilitarias

### Frontend (**resources/webapp**)

- `css/` - Hojas de estilo
- `js/` - Scripts JavaScript
- `layout/` - Plantillas de diseño
- `WEB-INF/` - Páginas JSP
    - `carro.jsp` - Vista del carrito de compras
    - `detalle.jsp` - Detalles de un producto
    - `detallesPedido.jsp` - Detalles de un pedido
    - `form.jsp` - Formulario genérico
    - `form_categoria.jsp` - Formulario para categorías
    - `index.jsp` - Página principal
    - `listar.jsp` - Listado genérico
    - `listar_categorias.jsp` - Listado de categorías
    - `listar_pedidos.jsp` - Listado de pedidos
    - `listar_usuarios.jsp` - Listado de usuarios
    - `login.jsp` - Formulario de inicio de sesión
    - `registrar.jsp` - Registro de nuevos usuarios
    - `ya_iniciado.jsp` - Mensaje cuando el usuario ya está autenticado

## Características Principales

### Autenticación y Roles

- **Usuario Administrador (`ROL_ADMINISTRADOR`)**
    - Puede gestionar productos, pedidos, categorías y usuarios.
- **Usuario Regular**
    - Puede ver y comprar productos, gestionar su carrito y visualizar sus pedidos.

### Funcionalidades

#### 1. Registro e Inicio de Sesión

- Formulario para registrar nuevos clientes (`registrar.jsp`)
- Formulario para iniciar sesión (`login.jsp`)

#### 2. Menú Principal

##### Productos

- Listar productos en una tabla.
- Agregar productos (**Solo Administrador**).
- Editar productos (**Solo Administrador**).
- Eliminar productos (**Solo Administrador**).
- Agregar productos al carrito.

##### Pedidos

- Los administradores pueden ver **todos** los pedidos.
- Los usuarios regulares solo pueden ver **sus propios** pedidos.

##### Categorías (**Solo Administrador**)

- Listar categorías.
- Agregar categorías.

##### Acceso (**Solo Administrador**)

- **Usuarios**
    - Listar usuarios registrados.
    - Agregar nuevos usuarios.
- **Roles**
    - Listar roles disponibles.

##### Carrito de Compras

- Muestra los productos agregados al carrito.
- Permite agregar más productos.
- Permite avanzar con el pedido.

## Instalación y Configuración

1. Clonar el repositorio con el siguiente comando:
   ```sh
   git clone https://github.com/Wellington-Esteban-Romero/GestionProductos.git
   ```
2. Importar el proyecto en tu IDE preferido (**Eclipse, IntelliJ IDEA, NetBeans**).
3. Crear la base de datos y ejecutar los scripts SQL necesarios.
4. Desplegar en un servidor Jakarta EE en Tomcat.
5. Acceder a la aplicación vía navegador web en `http://localhost:8080/GestionProductos`.

## Captura de Pantalla

