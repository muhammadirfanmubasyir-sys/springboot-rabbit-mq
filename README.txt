docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:4.3.0-management

RABBIT MQ MANAGEMENT
user/pwd = guest/guest
========================
http://localhost:15672/
=========================
Send JSON Message
=================
POST:http://localhost:8080/api/v1/publish
REQUEST:
{
  "id": 5,
  "firstName": "Syarifah",
  "lastName" : "Nur Aisyahrani"
}
RESPONSE: 200 OK: Json Message sent to Rabbit MQ...
=================================================
c.i.m.r.publisher.RabbitMQJsonProducer   : Json Message sent => User {id=5, firstName='Syarifah', lastName='Nur Aisyahrani'}
c.i.m.r.consumer.RabbitMQJsonConsumer    : Received JSON message -> User {id=5, firstName='Syarifah', lastName='Nur Aisyahrani'}
=========================================
SEND STRING MESSAGE
===================
GET : http://localhost:8080/api/v1/publish?message=SYUKUR
RESPONSE: 200 OK : Message sent to Rabbit MQ...
c.i.m.r.publisher.RabbitMQProducer       : Message sent => SYUKUR
c.i.m.r.consumer.RabbitMQConsumer        : Received message -> SYUKUR