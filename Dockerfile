# Stage 1: Build the Sprint Boot service
FROM maven:3.8.3-openjdk-17 AS spring-boot-build
WORKDIR /app
COPY ./employeeservice /app
RUN mvn clean install

# Stage 2: Build the Angular frontend
FROM node:14 AS angular-build
WORKDIR /app
COPY ./employeeui/angularclient /app
RUN npm install
RUN  ./node_modules/.bin/ng build

# Stage 3: Setup NGINX for Angular frontend
FROM yobasystems/alpine-nginx AS frontend
COPY --from=angular-build /app/dist/angularclient /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf

# Stage 4: Setup Spring Boot backend
FROM yobasystems/alpine-nginx AS backend
COPY --from=spring-boot-build /app/target/employee-0.0.1-SNAPSHOT.jar /app.jar

# Stage 5: Combine frontend and backend
FROM yobasystems/alpine-nginx
RUN apk add openjdk17-jre
COPY --from=frontend /etc/nginx /etc/nginx
COPY --from=frontend /usr/share/nginx/html /usr/share/nginx/html
COPY --from=backend /app.jar /app.jar
COPY ./multiWrapper.sh /multiWrapper.sh

# Run the java service and start nginx
CMD ["sh", "-c", "./multiWrapper.sh"]
EXPOSE 8080 4200

