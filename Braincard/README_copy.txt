Braincard - A Credit Card Provider
------------------------------------------------------------------------------------------

This is a simple credit card provider written in Java with basic functionalities of adding new cards and charging/crediting amounts to cards.

Assumptions: (other than input assumption that were provided)

- all input will be space delimited
- credit card numbers may vary in length, up to 19 characters
- credit card numbers will always be numeric
- amounts will always be prefixed with "$" and will be in whole dollars (no decimals)
- Customer names are unique. In case an add-card request comes with an existing name, the new card is stored under the same name, treating it as 2 cards of a same customer. So for the same reason, when a charge/credit request comes for a customer with multiple cards, the action is not performed as it cannot be determined which card is to be charged/credited. This will be modified in later version of program, maybe to take in name and card number for a request.


Application Modules
------------------------------------------------------------------------------------------

The application consists of the following modules. Although a simple program, the
separation into these modules have been done keeping mind a future extension of the
program to support different kinds of payment cards and repositories. The design takes
care of object-oriented paradigms of abstraction and flexibility, and so future extension
on it will be much easier.


Card
	- AbstractCard.java
	An abstract class representing a payment card with class variables and abstract functions that a payment card is expected to have. An abstract class was chosen over an interface, as i thought its better design to have class variables in this class rather than duplicating it in each implementing subclass.


	- CreditCard.java
	An implementation of AbstractCard, representing a credit card. 
	Provides the following functionalities, 
		- isValid() - checking validity of a card number using Luhn algorithm. In addition, it assumes that card number lengths of 14, 15, 16 and 19 are the only valid ones. I wasn't sure of which all lengths are to be included as it wasn't mentioned in the requirements, but this can be changed later to include more/less.
		- chargeAmount() - charges an input amount to a card's balance, if the card had been determined as valid.
		- creditAmount() - credits an input amount to a card's balance, if the card had been determined as valid.

Repository
	- IRepository.java
	An interface representing a repository. The idea behind having an interface is so that the program can support several types of repositories (a database, flat file, etc) with ease.

	- HashRepository.java
	An implementation of IRepository, where the internal repository is a HashMap with a customer name as key, and a List of cards as the value. A List of cards is maintained because there are chances that a user can have multiple cards. The class also follows a singleton pattern prohibiting access to its constructor and having a static method returning an instance.

	Provides following functionalities,
        - getInstance() - returns instance of this repository. creates new if its null.
        - getCard() - returns a card instance, given a name as key.
        - getAllCards() - returns list of all cards in repository.
        - addCard() - adds a new card into repository.
        - deleteCard() - deletes card(s) for a given card holder name.

	- RepositoryFactory.java
	A factory class that retrieves an instance of a repository, given a repository type.


Manager
	- CardManager.java
	A central manager that interacts with the repository and card instances. This class was included in the design so that other modules (if required) need to interact with the manager alone, and be abstracted out from repository or card functionalities. The manager has a repository instance within itself and deals with a repo directly. The repo type is saved during its initialization. Basically, a manager is required for a repo type that manages all its functions.

    Provides following functionalities,
        addNewCard() - delegates add-card function to a repository.
        chargeAmountToCard() - delegates charge-card function to a repository.
        creditAmountToCard() - delegates credit-card function to a repository.
        generateReport() - gets all cards from repository and prints their reports.

Braincard
    - Braincard.java
    The class that represents the application as it is. By design, this class further abstracts all the inner implementations of payment cards and repositories from the manager.

    Provides following functions,
        processInput() - validates the input parameters
        chargeAmountToCard() - delegates charge-card function to a manager.
        creditAmountToCard() - delegates credit-card function to a manager.
        generateReport() - delegates print-report function to a manager.

Main
	- CCInputProcessor.java
	This class has the main method that parses input requests from a file and submits the same to Braincard application. As the input source changes, a new class or method can be written to parse the new input. Then it should submit it to the Braincard.processInput() to process the actions.

Validator
	- InputValidator.java
	For each action specified, it validates whether the input parameters are null or empty.

Test
    - BraincardTest.java
    A JUnit test class that tests for,
        successful add, charge and credit card requests
        unsuccessful add, charge, credit requests with missing input parameters
        valid credit card numbers (testing the Luhn checker in CreditCard class)

Running the application
------------------------------------------------------------------------------------------

The entry point for the application is the CCInputProcessor class which has the main method. 
Run it as,

    cd braintree 
    javac braincard/main/CCInputProcessor.java 
    java braincard/main/CCInputProcessor

Each action/request is processed and notified by a message in the console such as, "Added
card...". At the end of processing the input file, a summary report is generated for all
cards in the repository.


Running application tests
------------------------------------------------------------------------------------------

I have used junit library to write my test cases in eclipse. To run the same in command line, normal execution of the test/BraincardTest.java should work. If any assertion error
occurs, AssertionFailureException will be raised.

    cd braintree 
    javac braincard/test/Braincard.java 
    java braincard/test/Braincard
