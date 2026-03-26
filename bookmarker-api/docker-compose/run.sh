declare dc_infra=docker-compose-db.yaml
declare dc_app=docker-compose-app.yaml

#애플리케이션 빌드
function build_api(){
   cd ../
   ./mvnw clean package -DskipTests
   cd docker-compose
}

#데이터베이스 인프라(postgresql)시작 
function start_infra(){
   echo "Starting db docker container"
   docker-compose -f ${dc_infra} up -d
   docker-compose -f ${dc_infra} logs -f
}

function stop_infra(){
   echo "Stopping db docker container"
   docker-compose -f ${dc_infra} stop
   docker-compose -f ${dc_infra} rm -f
}

#모든 서비스 시작
function start(){
  build_api
  echo "Starting DB and App container"
 docker-compose -f ${dc_infra} -f ${dc_app} up -d
 docker-compose -f ${dc_infra} -f ${dc_app}  logs -f
}


#모든 서비스 중지
function stop(){
echo "Stopping DB and App container"
 docker-compose -f ${dc_infra} -f ${dc_app} stop
docker-compose -f ${dc_infra} -f ${dc_app} rm -f
}

#모든 서비스 재식작
function restart(){
   stop
   sleep 3
   start
}

#action="start" 기본동작을 start 
action="start"

if [[ "$#" != "0" ]]
then
 action=$@
fi

#action 변수의 값을 평가하여 실행
eval ${action}