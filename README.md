\# Productos API – Prueba Técnica

Proyecto base para la prueba técnica de desarrollador \*\*Java Spring Boot + JavaScript\*\*.  

Por favor, una vez termines, responde al correo con:

\- El enlace a tu repositorio (rama con tu nombre).

\- Documento con las respuestas a las preguntas del punto 3.

\---

\## 1. Cómo clonar el proyecto

\### 🔹 Opción 1 – Usando Git (recomendado)

1\. Clonar el proyecto:

`   ````bash

`   `git clone https://github.com/MI-USUARIO/productos-api.git

`   `cd productos-api

2. Crear rama con tu nombre, a partir de develop:
2. git checkout -b nombre-apellido

**🔹 Opción 2 – Descargar ZIP**

1. Ir al repositorio en GitHub.
1. Clic en **Code → Download ZIP**.
1. Descomprimir y abrir en tu IDE.

**2. Requerimientos técnicos**

|**Herramienta**|**Versión recomendada**|
| :-: | :-: |
|Java|17+|
|Maven|3\.8+|
|IDE|Eclipse, IntelliJ o VS Code|
|Postman|Opcional (para pruebas REST)|

-----
**3. Cómo ejecutar el proyecto (Backend)**

**🔹 Opción 1: Desde IDE (recomendado)**

1. Importar como proyecto **Maven**.
1. Ejecutar la clase principal:
1. com.evaluacion.productosapi.ProductosApiApplication
1. La API estará disponible en:
1. http://localhost:8080

**🔹 Opción 2: Desde consola**

mvn spring-boot:run

**4. Instrucciones frontend**

El proyecto incluye un pequeño frontend en frontend/index.html para interactuar con la API.

**4.1. Cómo abrir el frontend**

1. Asegúrate de que el **backend esté corriendo** en http://localhost:8080.
1. Desde el explorador de archivos o desde tu IDE, abre:
1. frontend/index.html

   en un navegador web (Chrome, Firefox, etc.).

**4.2. Funcionalidades del frontend**

Al abrir index.html:

- Se realiza una llamada a GET /productos y se muestra la **lista completa de productos** en una tabla con las columnas:
  - ID
  - Nombre
  - Precio
  - Descripción
  - Cantidad Disponible

Además, se incluyen dos secciones de búsqueda:

**🔹 Buscar producto por ID** 

- Campo de entrada numérico para el ID.
- Botón **“Buscar”**.
- También se puede presionar **Enter** dentro del campo para ejecutar la búsqueda.
- Llama al endpoint:
- GET /productos/{id}
- Comportamiento:
  - Si el producto existe, la tabla se actualiza mostrando **solo ese producto**.
  - Si el producto no existe, se muestra un mensaje de error amigable usando el JSON de error del backend y la tabla puede quedar vacía o con el estado correspondiente.

**🔹 Buscar productos por categoría**

- Campo de texto para la categoría (por ejemplo: Tecnología, Accesorios, Oficina).
- Botón **“Buscar”**.
- También se puede presionar **Enter** dentro del campo para ejecutar la búsqueda.
- Llama al endpoint:
- GET /productos/categoria/{categoria}
- Comportamiento:
  - Si la categoría es válida y hay resultados, la tabla se actualiza con los productos de esa categoría y se muestra un mensaje con el número de productos encontrados.
  - Si la categoría es válida pero no hay productos, se muestra un mensaje indicando que no se encontraron resultados.
  - Si la categoría es inválida (no es Tecnología, Accesorios u Oficina), se muestra el mensaje de error enviado por el backend.
  - Si se deja el campo vacío y se pulsa **Buscar**, se vuelve a cargar la **lista completa de productos**.

Todos los mensajes de éxito o error se muestran en el elemento <p id="mensaje"></p> al final de la página.

**5. Preguntas teóricas**

**5.1. Diferencias entre @Controller, @RestController y @Service**

**@Controller**\
Se usa en aplicaciones web basadas en Spring MVC. Los métodos suelen devolver **vistas** (plantillas HTML, JSP, Thymeleaf, etc.).\
Si se quiere devolver JSON o texto, es necesario anotar el método con @ResponseBody.

**@RestController**\
Es una especialización de @Controller pensada para **APIs REST**. Internamente equivale a @Controller + @ResponseBody, por lo que todos los métodos devuelven directamente el cuerpo de la respuesta (JSON, texto, etc.) en lugar de una vista.\
Es la anotación más apropiada para controladores como ProductoController, que exponen endpoints HTTP que retornan datos.

**@Service**\
Marca una clase como componente de **lógica de negocio**. Encapsula reglas, validaciones y coordinación de operaciones con los repositorios.\
El controlador atiende la petición HTTP y delega el “qué hay que hacer” en la capa Service, lo que promueve una buena separación de responsabilidades.

**5.2. Qué es un DTO** 

Un **DTO (Data Transfer Object)** es un objeto diseñado para **transportar datos** entre capas (por ejemplo, entre backend y frontend) sin exponer directamente la entidad de base de datos.

Uso recomendado:

- Evitar exponer campos internos o sensibles de las entidades JPA.
- Crear DTOs específicos por caso de uso:
  - ProductoCreateDTO para la creación (solo campos de entrada necesarios).
  - ProductoResponseDTO para las respuestas (formato pensado para el cliente).
- Permitir validaciones específicas de entrada y salida.
- Desacoplar el modelo de persistencia del contrato público de la API, de manera que cambios en la base de datos no rompan necesariamente las integraciones existentes.

**5.3. Explicar Promesa en JavaScript**

Una **Promesa** en JavaScript es un objeto que representa el resultado futuro de una operación **asíncrona**. Puede estar en tres estados:

- pending (pendiente)
- fulfilled (resuelta con éxito)
- rejected (rechazada con error)

Se utilizan para manejar código asíncrono (por ejemplo, llamadas HTTP con fetch) sin caer en “callback hell”.

Ejemplo con then/catch:

fetch('/productos')

.then(response => response.json())

.then(data => console.log(data))

.catch(error => console.error(error));

Ejemplo equivalente con async/await:

async function cargarProductos() {

`  `try {

`    `const response = await fetch('/productos');

`    `const data = await response.json();

`    `console.log(data);

`  `} catch (error) {

`    `console.error(error);

`  `}

}

**5.4. Ventaja de usar H2 en pruebas locales**

Usar **H2** como base de datos en desarrollo/pruebas locales tiene varias ventajas:

- Es una base de datos **embebida**: no requiere instalar ni levantar un servidor adicional.
- Arranca muy rápido y se integra fácilmente con Spring Boot.
- Permite ejecutar pruebas sin afectar una base de datos real.

**5.5. Qué mejorarías para un entorno productivo**

Algunas mejoras clave para llevar esta API a producción serían:

1. **Base de datos de producción**
   1. Usar PostgreSQL en lugar de H2.
   1. Definir índices, claves foráneas y constraints apropiados.
1. **Perfiles de configuración**
   1. Usar application-dev, application-test, application-prod.
   1. Guardar credenciales y secretos en variables de entorno o un vault seguro, no en el repositorio.
1. **Seguridad**
   1. Implementar autenticación y autorización con Spring Security (por ejemplo, JWT).
   1. Restringir el acceso a endpoints de creación, actualización y eliminación.
   1. Configurar CORS solo para dominios permitidos.
1. **DTOs y validaciones avanzadas**
   1. Usar DTOs para requests/responses en lugar de exponer directamente entidades JPA.
   1. Incorporar validaciones más completas (rangos de precio, longitudes máximas, formatos, etc.).
1. **Observabilidad y estabilidad**
   1. Configurar logs estructurados y niveles adecuados (INFO, ERROR, DEBUG en desarrollo).
1. **Despliegue y CI/CD**
   1. Contenerizar la aplicación con Docker.

