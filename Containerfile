FROM eclipse-temurin:17-jdk-jammy

RUN apt update && \
    adduser -m appuser

USER appuser

WORKDIR /matrix-multiplication

COPY . .

RUN gradle clean && \
    gradle build

CMD ["java", "-cp", "build/classes/java/main/", "csx55.threads.MatrixThreads 8 3000 31459"]
