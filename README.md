# Personalized Data API

### API service that provides shoppers personalized information to eCommerce servers.

This is a personalized data service. We receive requests from e-commerce services, which include the shopper ID and the shelf containing product IDs along with their relevancy scores. What our personalized data service does is retrieve a comprehensive list of products, sorting them based on relevancy score.

### Technology
1. Java 17
2. Spring Boot 3
3. Maven 
4. MySQL
5. Docker


### How to test

1. First, please ensure that the above-mentioned technology is installed on your PC and check its compatibility.

2. Clone the project.

3. Run the query script provided in the resource directory of the project.

4. Configure ports and database credentials accordingly.

5. Start the Spring Boot application.


### Sample Payload

``{
"shopperId": "S-1000",
"shelf": [
            {
                "productId": "MB-2093193398",
                "relevancyScore": 31.089209569320897
            },
            {
                "productId": "BB-2144746855",
                "relevancyScore": 55.16626010671777
            },
            {
                "productId": "MD-543564697",
                "relevancyScore": 73.01492966268303
            },
            {
                "productId": "MD-543564699",
                "relevancyScore": 75.01492966268303
            }
        ]
}``

``localhost:8080/personalized-data/?page=&size=``