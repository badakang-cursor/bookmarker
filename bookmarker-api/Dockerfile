#FROM eclipse-temurin:17-jdk-alpine
FROM eclipse-temurin:17.0.11_9-jre-focal
#FROM eclipse-temurin:21-jre


#target 디렉터리에서 모든 jar 파일을 app.jar로 추가
ADD target/*.jar app.jar

#컨테이너 내부의 포트 8080을 외부 노출
EXPOSE 8080

#어플리케이션 실행 명령어
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]