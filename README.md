# example-amqp
Examples of separate RabbitMQ Sender and Receiver (using Spring).
 
This builds upon the Spring Guides RabbitMQ/AMQP example except that the Sender & Receiver are in separate processes
and hence, their respective Bean dependencies are identified.


## Requirements

The fully fledged server uses the following:

* Java JDK 8
* RabbitMQ 



## Building the project
You will need:

* Git
* Maven 3.1.1 or higher
* JDK 8 (e.g. jdk1.8.0_60)

Clone the project and use Maven to build the sender and the receiver 

	$ mvn clean install


## Running the Sender and the Receiver 
Ensure you have RabbitMQ installed. Instructions for download and installation are details on their website [here](https://www.rabbitmq.com/download.html).

RabbitMQ was built using Erlang and the server requires the Erlang runtime environment to be installed, which is 
detailed [here](https://www.erlang.org/downloads).

The repo contains two separate projects, representing the message 'sender' and 'receiver' respectively. Run the message-receiver, as follows:

	$ java -jar rabbitmq-listener\target\rabbitmq-listener-0.1.0.jar

Run the message-sender, as follows:

	$ java -jar rabbitmq-publisher\target\rabbitmq-publisher-0.1.0.jar



### Notes 

This builds upon the RabbitMQ example from the Spring 'Getting Started' guides. You can find the original demo here 
[Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq) and you can find the full set of 'Getting 
Started' guides [here](https://spring.io/guides). 

