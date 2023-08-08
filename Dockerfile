FROM openjdk:17-oracle
LABEL authors="decagon"
COPY target/FashionBlogAPI-0.0.1-SNAPSHOT.jar fashionBlog-image.jar
EXPOSE 8089
EXPOSE 5432
ENTRYPOINT ["java", "-jar", "fashionBlog-image.jar"]