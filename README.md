# Kafka first look

This is a practice project with my first look at Kafka and trying to understand how it functions.

I set up a system using this chain of events: API -> Kafka -> Database
The idea is that once it's in Kafka it can be sent to any one who wants to consume the stock data, this allows for 
faster processing as the API doesn't have to wait for the database store.

I'm using a CSV for the stock data as it made it easier to mimic a large amount of data coming in and process.

### Requirements and how to run

Requirements for this project are Java 17 and Docker.

To run the project simple run:

`.\mvnw spring-boot:run` OR `mvn spring-boot:run`

This will run the `compose.yaml` file which boots up the Kafka manager, zookeeper, MySQL docker and runs the application.

### APIs

Using these APIs you can test out Kafka in action:

I've added 2 sizes for the datasets:  
`SMALL` which has 100 stock transactions.  
`LARGE` which has 100k stock transactions. 


`curl --location --request POST 'http://localhost:8080/api/v1/stock/start/{size}'`

To get all the stock transactions as a JSON call the API below.

`curl --location --request GET 'http://localhost:8080/api/v1/user/stock'`

If you want data from a specific stock in the list you can call the API below which will get all transactions for that sepcific stock.

`SMALL` data set only has `HSI` as a stock.
`LARGE` data set has the following stocks: `HSI, NYA, IXIC, 000001.SS, N225, N100, 399001.SZ, GSPTSE, NSEI, GDAXI, SSMI, TWII, J203.JO`


`curl --location --request GET 'http://localhost:8080/api/v1/user/stock/{symbol}'`


### Things to note

The large data API call takes around 100ms to complete locally but the actual data processing will take around 30 mins.

If you call it and want to stop it, terminate the project in the terminal, stop the docker containers and delete the Zookeeper and Kafka folder created in the directory.

If you don't delete the folders kafka will resume to process the data set as it's stored in a queue.




