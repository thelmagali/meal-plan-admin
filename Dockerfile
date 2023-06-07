FROM azul/zulu-openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY . /app
WORKDIR /app
RUN ./gradlew build --no-daemon
ENTRYPOINT ["java","-jar","build/libs/meal-plan-admin.jar"]