# Librarian

## How to Run
To run the program, execute the following command in your terminal:

```java Library```

## Implementation
The Librarian program manages collections of Member accounts and Book objects. Each Member account is identified by a unique member number and stores their name, currently rented books, and rental history. Member numbers start at 100000 and increase sequentially.

A Book is identified by its serial number and contains information such as title, author, genre, current borrower, and rental history. Only one member can rent a book at a time. Books with the same title and author are considered copies but have different serial numbers.

Book collections can be read from and saved to CSV files, with each line representing a book in the format:

```
[serial number],[title],[author],[genre]
```

For example:

```
111111,Catch 22,Joseph Heller,Comedy
```

## Commands
- `EXIT`: Ends the library process
- `COMMANDS`: Displays a list of available commands
- `LIST ALL [LONG]`: Lists all books, optionally providing additional details
- `LIST AVAILABLE [LONG]`: Lists available books, optionally providing additional details
- `NUMBER COPIES`: Outputs the number of copies of each book
- `LIST GENRES`: Lists all genres in the system
- `LIST AUTHORS`: Lists all authors in the system
- `GENRE <genre>`: Lists books belonging to a specific genre
- `AUTHOR <author>`: Lists books written by a specific author
- `BOOK <serialNumber> [LONG]`: Displays information about a specific book
- `BOOK HISTORY <serialNumber>`: Displays the rental history of a specific book
- `MEMBER <memberNumber>`: Displays information about a specific member
- `MEMBER BOOKS <memberNumber>`: Lists books currently rented by a specific member
- `MEMBER HISTORY <memberNumber>`: Displays the rental history of a specific member
- `RENT <memberNumber> <serialNumber>`: Loans out a book to a member
- `RELINQUISH <memberNumber> <serialNumber>`: Returns a book from a member
- `RELINQUISH ALL <memberNumber>`: Returns all books rented by a member
- `ADD MEMBER <name>`: Adds a new member to the system
- `ADD BOOK <filename> <serialNumber>`: Adds a book to the system from a CSV file
- `ADD COLLECTION <filename>`: Adds a collection of books to the system from a CSV file
- `SAVE COLLECTION <filename>`: Saves the current system to a CSV file
- `COMMON <memberNumber1> <memberNumber2> ...`: Lists common books in members’ rental history
