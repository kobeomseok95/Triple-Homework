FROM adoptopenjdk/openjdk11

ARG JAR_FILE=./build/libs/homework-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT [ \
            "java", \
            "-Duser.timezone=${TZ}", \
            "-jar", \
            "/app.jar" \
]
