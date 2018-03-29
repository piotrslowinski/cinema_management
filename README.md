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
