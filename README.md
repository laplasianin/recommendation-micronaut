## Test task 

## getting recommendations by given product

Requirements:
- Java 11 or higher
- Postgers (will be run in docker during setup)


The idea is to have 3 microservices:
- Data ETL is responsible for fetching, processing and saving recommendations and products;
- Recommendations Service is responsible for calculation and retrieving recommendations from DB;  
- WEB-API is an API gateway;


## Data ETL Microservice (klevu-data-etl)

### Tech stack
- Micronaut
- Postgres
- Jooq
- Flyway
- Gradle
- JUnit 5
- Testcontainers for tests

The idea is to save precalculate recommendation weights for each permutation of products and save precomputed data into DB. This makes getting calcuation is a simple operation of simple select from DB

TODOS:  
- The test data source is small so I did not implement any performance improvements. I only do sync batch inserts and thats is enough for task purposes
- API does not support retries. If you will try to extract data again - it will fail if exception. Just did not ahve enough time to implement upsert logic

## Recommendation Microservice (klevu-data-recommendation)

### tech stack
- Micronaut
- Postgres
- Micronaut DATA (ORM)
- Flyway
- Gradle
- Testcontainers for tests
- JUnit 5


TODOS:
- Add caching (in memory or distributed). Just did not have enough time 
  
### WEB-API microservice (klevu-web-api)

### tech stack
- Micronaut
- Gradle
- Micronait Circuit Breaker
- Swagger for documentation


## Hot to run:

**Step 1.**

`./postgres-up.sh` - this will:
- Run postgres in docker

NOTE: skip if you have up and running postgres on localhost in 5432 port

**Step 2.**

Connect to DB and create database klevu

**Step 3.**

`./install.sh` - this will:
- compile, build microservices.  
- Run flyway migrations
- Prepare docker images

**Step 4.**

`./compose-up.sh` - this will:
- Run data-etl in docker
- Run data-recommendation in docker
- Run web-api in docker

NOTE: if you are running postgres locally - set DATASOURCE_URL in compose-up.sh file

## API:
http://localhost:8080/swagger-ui - swagger API

## Hot to stop:

`./compose-down.sh` - this will:
- Stop and remove data-etl
- Stop and remove data-recommendation
- Stop and remove web-api
- Stop and remove postgres



## TODO:
- No caching mechanics implemented
- No add new purchase API implemented (no needed by requirements but good to have). This is easy in current architecture - just upsert logic
- Improve resilience logic - set rate limiters, configure circuit breaker more carefully
- Shared DB pre DATA-ETL and RECOMMENDATION microservices - since DATA-ETL is one-time purpose microservice - I believe it is fine 