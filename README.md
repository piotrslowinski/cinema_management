# Cinema Management System
---
Used technologies:

1. Java 8
2. Maven
3. Spring Framework
	- Spring Boot
	- Spring Web MVC for REST API
4. MySQL DB

---

## 1. Features

1. As an admin You can add a new cinema to the system, so that users can look for movies in that cinema


	*PUT/cinemas*

...
	

		{
			"name": "Felicity",
			"city": "Lublin"
		}
...

2. As an admin You can add a new movie to the system, so that users can see what is played in chosen cinemas

	*PUT/movies*

...

		{
			"title": "Pulp Fiction",
			"description": "You have to see this ;)",
			"actors": ["John Travolta", "Samuel L. Jackson", "Uma Thurman"],
			"genres": ["Crime", "Drama"],
			"minAge": 16,
			"length": 120
		}
		
...
	
3. As a customer You can see a list of cinemas, so that You can pick a cinema and see the movies it offers

	*PUT/movies*

	- example respone:
		
...

		{
			[
				"id": 1,
				"name": "Plaza",
				"city": "Lublin"
			],
			[
				"id": 2,
				"name": "Olimp",
				"city": "Lublin"
			]
				
		}
		
...

4. As an admin You can enter information about movie shows, so that users can see what movies are displayed in cinemas and when to purchase tickets

	*PUT/cinemas/:cinemaId/shows*
	
	- the shows can be added two ways:
	
...
	

		{
			"movieId": 1,
			"dates": ["yyyy/MM/dd hh:mm", "yyyy/MM/dd hh:mm", "yyyy/MM/dd hh:mm"]
		}
...

	- or:
	
...

		{
			"movieId": 1,
			"calendar": {
				"fromDate": "yyyy/MM/dd hh:mm",
				"untilDate": "yyyy/MM/dd hh:mm",
				"weekDays": ["Monday", "Tuesday", "Wednesday"],
				"hours": ["hh:mm", "hh:mm", "hh:mm"]
			}
		}
		
...

5. As a customer You can see movies available on a given day, so that You can pick up a movie and buy tickets

	*GET/cinemas/:cinemaId/movies?date=YYYY/MM/dd*
	
	
6. As an admin You can define shows pricing, so that users now how much they have to pay for the tickets
	(one movie has the same price of shows in every city, if you put price again you'll overwrite old prices with new).
	
	*PUT/movies/:movieId/prices*
	
...
	
		{
			"regular": 999.99,
			"student": 999.99,
			"school": 999.99,
			"children": 999.99
		}
...

7. As a customer You can make a reservation, so that You can purchase the tickets online or onsite

	*PUT/reservations*
	
...
	
	{
		"showId":1,
		"ticekts": [
			{
			"kind": "regular",
			"count": 2
			},
			{
			"kind": "school",
			"count": 1
			}
		],
		"seats": [
			{"row": 5, "seat": 10},
			{"row": 5, "seat": 11},
			{"row": 5, "seat": 12}
		],
		"customer": {
			"firstName": "John",
			"lastName": "Doe",
			"email": "john.doe@test.com",
			"phone": "99999999999"
		}
	}
	
...

8. As a customer You can see how much You have to pay for the selected tickets, so that You know if You want to proceed with your reservation

	*POST/reservations/price_calculator*
	
...

	{
		"showId": 1,
		"ticekts": [
			{
			"kind": "regular",
			"count": 2
			},
			{
			"kind": "school",
			"count": 1
			}
		]
	}
	
...

	- return calculated price in following format:
	
...

	{
		"ticekts": [
		{
			"kind": "regular",
			"count": 2,
			"unitPrice": 10.00,
			"totalPrice": 20.00
		},
		{
			"kind": "school",
			"count": 1,
			"unitPrice": 10.00,
			"totalPrice": 10.00
		}
		],
		"totalPrice": 30.00
	}
	
...

9. As a customer You can see which seats are available for a given show, so that You can pick up seats that You like

	*GET/shows/:showId/seats*
	
...

	{
		"free": [
		 {"row": 1, "seat": 1}, {"row": 1, "seat": 2}, ...
		],
		"occupied": [
		 {"row": 2, "seat": 2}, {"row": 1, "seat": 3}, ...
		]
	}
	
...

10. As a cashier You can browse the list of reservations, so that You can accept payment

	*GET/reservations?query=q&status=s*
	
	
11. As a customer You can pay for the ticket by a credit card, so that You can receive the tickets

	*PUT/reservations/:reservationNumber/payment*
	
	- app accepts following json:
	
...

	{
	"type": "credit_card",
	"creditCard": {
		"number": "4111111111111111",
		"expirationMonth": 10,
		"expirationYear": 2017,
		"cvc": 123
		}
	}
	
...