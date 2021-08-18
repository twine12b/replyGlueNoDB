##ReplyGlue Tech Challenges
Data stores is handled with List’s. Repositories Implementations populate a payments list and a `Registered Users List`.  Communication with the list is done via a repository layer which exposes an interface to the service layer.  Controllers and services communicate in the usual fashion  (there is no database in this application).
###Tech Stack
```
Java , Spring Boot, Lombok, Junit, Gson
```
Running the application
Extract the zip file to a directory of your choice.  Open your favourite IDE and navigate to `src / main / java / ` and run the `AppAplication.java` file.
Test the endpoints as specified in the brief using a web browser or preferably use a tool like `postman`.   

registration endpoint  `GET`
 `/users`	- gives a list of all current users
`/users?CreditCard=yes` - All users who have registered credit cards.
`/users?CreditCard=no`   - All users that have not registered a credit card

registration endpoint  `POST`
`/user/		- Validates then registers a new user.
###Sample data
```
{
    "username":"PhilCollin2",
    "password":"passWord1",
    "email":"phil@me.com",
    "dob": "2001-05-09",
    "card": "1111111111111112"
}

{
    "payment_card":"1111111111111111",
    "amount":"109"
}
```
###Status code
Try add the user and then adding the sample user. ` 201 Created` status code is sent.
Try add the user and then adding the same user again. ` 409 Conflict` status code is sent.
Removing the 2 at the end of the username key sends a `400 Bad Request`.  Modify the rest of the fields to violate the briefs instructions and status `400  Bad Request` will be shown.  
Users can be added to the system with or without a credit card achieved by simply using the `@Nullable` annotation. 
Entering a `payment_card` that is NOT on he system responds with a 404 Not Found status code.

###Testing
There are multiple test cases written which drove the development of this application.  Missing from the testing is Integration testing (No external sauces e.g.Database), performance testing has been omitted.  I am aware of caching and the speed advantages but in this case as the data is in memory I have omitted this test. 

###Things todo
```
Relate payment to user to payments via a `userPayments` class which has 4 attributes.  
-	Date / Reference Number / User / Payment.
Create a separate `CreditCard` object.  Currently the relationship between users and credit cards is oneToOne.  A user may have multiple cards that they wish to register, moving the Credit card to its own table/class will facilitate multiple cards for a single user.
```
Notes
- The instruction of 3 digits for a payment was taken literally and should have been implemented using a more suitable primitive data type that would represent values in the real world.
-Validation methods are convoluted and readability would have been improved had I used the combinator pattern.  I am adopting Functional programming hence the code is a mixture of old and new styles. This was a really good challenge. 
- Thank you for evaluation this project.  i look forward to your feedback.


