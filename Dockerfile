# OpenJDK 17 기반의 이미지를 사용 (버전은 프로젝트에 맞게 변경)
FROM openjdk:17-jdk-slim

ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

# 작업 디렉토리 생성
WORKDIR /app

# JAR 파일 복사
COPY build/libs/*.jar app.jar

# 컨테이너 실행 시 Spring Boot 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]

# 기본 포트 설정
EXPOSE 8080