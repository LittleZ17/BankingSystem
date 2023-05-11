# :bank: BankingSystem

This is a midterm project developed for the IronHack Bootcamp in collaboration with Accenture. The application is built using the Spring framework and provides a set of functionalities for managing different types of bank accounts.

## :books: Features
The application includes the following features:

Admin: who can create all accounts, delete them and modify their status and balance.

AccountHolder: who can consult the balance of his account. That AccoultHolder can make money transfers.

There are 4 types of accounts and they all have their own special characteristics and attributes:

Account Types: There are four types of accounts: StudentChecking, Checking, Savings, and CreditCard. Each account type has specific attributes and behaviors.

User Types: The system supports three types of users: Admins, AccountHolders, and ThirdParty. Each user type has different privileges and access rights.

Account Creation: Admins can create new accounts for AccountHolders. They can create Checking, Savings, or CreditCard accounts with specific configurations.

### :dollar: Checking Accounts:
That StudentCheckingAccounts should be created if customers registering are under 24 years old.
The minimum balance is 250.
The monthly maintenance fee is 12.

### :moneybag: Savings Account
The minimum balance must be between 100 and 1000.
Interest rate must be between 0 and 0.5.

### :credit_card: Credit Card Account
The credit limit is between 100 and 100000.
The interest rate must be between 0.1 and 0.2.

### Scalabilities
While the application meets most of the requirements, it's important to mention that the interest calculation functionality for each account is still under development and not fully completed. Efforts are being made to finalize this feature and ensure accurate interest calculation for savings accounts and credit cards.

Details:
That ThirdParty to be able to transfer money.

Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. 

Interest on credit cards is added to the balance monthly.

Account Access: Admins can access and modify the balance of any account. AccountHolders can access their own account balance and transfer money between their accounts or to other accounts.

Third-Party users can send and receive money to/from other accounts by providing their hashed key and necessary details.

Account Management: The application supports penalty fees for accounts that drop below the minimum balance. Interest rates are applied to savings accounts annually and credit card balances monthly.

##  CaseDiagram & ClassDiagram


## :woman_technologist: Technologies Used
The application is built using the following technologies:

Java with Spring framework

·Spring Boot

·Spring Test

·Spring MVC

·MySQL database

## :card_file_box: Database
A MySQL database is used to store the application data. The necessary database schema and sample data are provided.

## :orange_circle: Postman Collection
A Postman collection is provided that includes the API routes and sample requests for testing the application.

## :pushpin: Getting Started
To run the application, follow these steps:

Clone the repository to your local machine.
Set up a MySQL database and import the provided schema and sample data.
Configure the database connection details in the application's configuration files.
Build and run the application using your preferred Java IDE or Maven command.
Use the provided Postman collection to test the application's functionality.

# Happy banking!
