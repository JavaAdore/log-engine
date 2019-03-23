# log-engine
engine listens on queue footPrintQueue in rabbitMQ and blindly save messages in mongo db <br/>

mongo document has list for all users each user has list of days each day has list of requests each request has unique id, so all actions done by user could be tracked up<br/> 

All Methods annotated by @FootPrint("EVENT NAME") in all other microservices pushs logs for event and it's result (event success or throwing busniness of unhandled exception) into the exchange, this engine will be responsible for listening  on a queue "footPrintQueue" ( the final destination of exchange logExchange with routing key footPrint and then logs the message in mongo db  <br/>
 

# prerequisites
config server should be up and run<br/>
<a href="https://github.com/JavaAdore/config-server">https://github.com/JavaAdore/config-server</a> <br/>
eureka server should be up and run<br/>
<a href="https://github.com/JavaAdore/eureka-server">https://github.com/JavaAdore/eureka-server</a> <br/>
 

zipkin server nice be up and run<br/>
<a href="https://github.com/JavaAdore/zipkin-server">https://github.com/JavaAdore/zipkin-server</a> <br/>

MONGO DB <br/>
RabbitMQ <br/>
Ensure rabbitMQ has exchange called "notification.exchange" <br/>
Ensure exchange "notification.exchange" has valid routing for messages with routing key "email" and "sms" ;<br/>
 


 

environment variables should be added

# ZIPKIN_SERVER_IP = 127.0.0.1
127.0.0.1 the ip of machine where zipkin server runs
# SLEUTH_LOGGING_LEVEL=info

# RABBITMQ_LISTENER_IP = 127.0.0.1
# RABBITMQ_LISTENER_PORT = 5672
# RABBITMQ_DEFAULT_USER = user
change user to username of rabbitmq
# RABBITMQ_DEFAULT_PASS = password
change password to username of rabbitmq


# MONGO_DB_IP=mongo
# MONGO_DB_USER_NAME=testProduct
 
 
# AUTHORIZATION_SERVER_IP=127.0.0.1
# AUTHORIZATION_SERVER_PORT=8888

# EUREKA_SERVER_IP      = 127.0.0.1
# EUREKA_SERVER_PORT    = 8761




# log-engine provides the following functionalities

void populateAndSendNotification(NotificationModel verificationStatusModel) throws ServiceException;


# build
as root/Administration <br/>
mvn clean install docker:removeImage docker:build
# run
java -jar target/log-engine.jar
