
FILE_NAME=weather-data-ms.properties

APP_ENV=""
APP_ENV=`cat ${FILE_NAME} | grep "APP_ENV" | cut -d'=' -f2`
echo "APP ENV:${APP_ENV}"

SERVER_PORT=""
SERVER_PORT=`cat ${FILE_NAME} | grep "SERVER_PORT" | cut -d'=' -f2`
echo "SERVER PORT:${SERVER_PORT}"

DATASOURCE_URL=""
DATASOURCE_URL=`cat ${FILE_NAME} | grep "DATASOURCE_URL" | cut -d'=' -f2`
echo "DB URL:${DATASOURCE_URL}"

DATABASE_USERNAME=""
DATABASE_USERNAME=`cat ${FILE_NAME} | grep "DATABASE_USERNAME" | cut -d'=' -f2`
echo "DB USER:${DATABASE_USERNAME}"

DATABASE_PASSWORD=""
DATABASE_PASSWORD=`cat ${FILE_NAME} | grep "DATABASE_PASSWORD" | cut -d'=' -f2`
echo "DB PASS:${DATABASE_PASSWORD}"

START_EMBEDDED_BROKER=""
START_EMBEDDED_BROKER=`cat ${FILE_NAME} | grep "START_EMBEDDED_BROKER" | cut -d'=' -f2`
echo "START EMBEDDED BROKER:${START_EMBEDDED_BROKER}"

ENABLE_JMS_PLAINTEXT=""
ENABLE_JMS_PLAINTEXT=`cat ${FILE_NAME} | grep "ENABLE_JMS_PLAINTEXT" | cut -d'=' -f2`
echo "ENABLE JMS PLAINTEXT:${ENABLE_JMS_PLAINTEXT}"

ACTIVEMQ_HOST_NAME=""
ACTIVEMQ_HOST_NAME=`cat ${FILE_NAME} | grep "ACTIVEMQ_HOST_NAME" | cut -d'=' -f2`
echo "ACTIVEMQ HOST NAME:${ACTIVEMQ_HOST_NAME}"

ACTIVEMQ_TCP_PORT=""
ACTIVEMQ_TCP_PORT=`cat ${FILE_NAME} | grep "ACTIVEMQ_TCP_PORT" | cut -d'=' -f2`
echo "ACTIVEMQ TCP PORT:${ACTIVEMQ_TCP_PORT}"

ACTIVEMQ_STOMP_PORT=""
ACTIVEMQ_STOMP_PORT=`cat ${FILE_NAME} | grep "ACTIVEMQ_STOMP_PORT" | cut -d'=' -f2`
echo "ACTIVEMQ STOMP PORT:${ACTIVEMQ_STOMP_PORT}"

ACTIVEMQ_USER_NAME=""
ACTIVEMQ_USER_NAME=`cat ${FILE_NAME} | grep "ACTIVEMQ_USER_NAME" | cut -d'=' -f2`
echo "ACTIVEMQ USER NAME:${ACTIVEMQ_USER_NAME}"

ACTIVEMQ_PASSWORD=""
ACTIVEMQ_PASSWORD=`cat ${FILE_NAME} | grep "ACTIVEMQ_PASSWORD" | cut -d'=' -f2`
echo "ACTIVEMQ PASSWORD:${ACTIVEMQ_PASSWORD}"

java -Dserver.port=${SERVER_PORT} -DAPP_ENV=${APP_ENV} -DDATASOURCE_URL=${DATASOURCE_URL} -DDATABASE_USERNAME=${DATABASE_USERNAME} -DDATABASE_PASSWORD=${DATABASE_PASSWORD} -DSTART_EMBEDDED_BROKER=${START_EMBEDDED_BROKER} -DENABLE_JMS_PLAINTEXT=${ENABLE_JMS_PLAINTEXT} -DACTIVEMQ_HOST_NAME=${ACTIVEMQ_HOST_NAME} -DACTIVEMQ_TCP_PORT=${ACTIVEMQ_TCP_PORT} -DACTIVEMQ_STOMP_PORT=${ACTIVEMQ_STOMP_PORT} -DACTIVEMQ_USER_NAME=${ACTIVEMQ_USER_NAME} -DACTIVEMQ_PASSWORD=${ACTIVEMQ_PASSWORD} -jar *.jar
