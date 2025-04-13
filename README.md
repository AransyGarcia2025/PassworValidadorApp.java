# Proyecto Java - Validación Concurrente de Contraseñas

**Autor:** Aransy García  
**Actividad #10**  
**Tema:** Validación concurrente de contraseñas con expresiones regulares, expresiones lambda y manejo de archivos.

## Descripción

Este proyecto es una aplicación de consola en Java que permite validar múltiples contraseñas de forma concurrente. Cada contraseña es evaluada según criterios de seguridad definidos, y los resultados son almacenados en un archivo de registro llamado `validacion_resultados.txt`.

La validación incluye:
- Longitud mínima de 8 caracteres.
- Al menos 1 carácter especial.
- Al menos 2 letras mayúsculas.
- Al menos 3 letras minúsculas.
- Al menos 1 número.

El programa utiliza:
- **Hilos** para la validación concurrente.
- **Expresiones Lambda** para simplificar la salida de resultados.
- **Manejo de archivos** para registrar las contraseñas y su estado de validación.

## Requisitos

- Java JDK 8 o superior.
- Editor de código o IDE (Eclipse, IntelliJ, VSCode, etc.).

## Ejecución

1. Clona o descarga este repositorio.
2. Compila los archivos Java:

bash javac PasswordValidatorApp.java PasswordValidator.java
