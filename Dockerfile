FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM openjdk:17
COPY --from=build /app/target/simpleJPA*.jar /usr/local/lib/simpleJPA.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/simpleJPA.jar"]