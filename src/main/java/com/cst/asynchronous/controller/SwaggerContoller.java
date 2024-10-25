package com.cst.asynchronous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerContoller {

	@GetMapping("/")
	public String getSwagger() {
		return "redirect:asynchronous/cts/swagger-ui/index.html";
	}
	/*
	 1. GET Request

To retrieve all users:

javascript

const getUsers = async () => {
    const url = 'http://localhost:9002/users';
    
    try {
        const response = await fetch(url);
        const data = await response.json();
        console.log('GET Users:', data);
    } catch (error) {
        console.error('Error fetching users:', error);
    }
};

// Call the function
getUsers();
	 
	 */
	
	/*
	 
	  Go to the Console Tab and use the following JavaScript
	   code to simulate sending 
	  requests. Adjust the URL and payload as needed:
	  
	  
	  const url = 'http://localhost:8080/api/users';
      const payload = {
    name: "John Doe",
    email: "john.doe@example.com"
};

for (let i = 0; i < 20; i++) {
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
}

	  
			*/
}
