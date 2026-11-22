# Imagen base ligera con OpenJDK 17
FROM eclipse-temurin:17-jre-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el ejecutable generado por Maven
COPY target/seguridadcalidad-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto HTTP estándar (puerto 80)
EXPOSE 80

# Iniciar la aplicación en el puerto 80
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=80"]