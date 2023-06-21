FROM gradle:7.6.1-jdk17
VOLUME /tmp
EXPOSE 8080
COPY . /app
WORKDIR /app
RUN gradle build
ENTRYPOINT ["java","-jar","build/libs/meal-plan-admin.jar"]