# Room Occupancy Optimization Service

Microservice which is responsible for:
- calculating how many rooms of each category will be occupied and how much
  money they will make in total.

Based on following assumptions:
- system will not book guests who pay EUR 100 or more for the night in economy rooms.
- system will book customers paying less than 100 in premium rooms if these rooms are empty and all Economy
  rooms are occupied with low paying customers.

## Usage

To get desired result reach following endpoint

```sh
http://domain-name/optimizations
```

To get required occupancy optimization the POST request needs to be sent with following body:

```shell
{
  "amountOfPremiumRooms": 4,
  "amountOfEconomyRooms": 3,
  "guestPayments": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

### Response example

The response will be following:

```shell
{
    "premiumRoomDetails": {
        "roomUsed": 4,
        "totalAmount": 853.00
    },
    "economyRoomDetails": {
        "roomUsed": 3,
        "totalAmount": 167.99
    }
}
```

### Project setup

Project uses maven and openapi itself. Project uses spring boot framework.
To run the service, first please run maven command:

```shell
mvn clean install
```

