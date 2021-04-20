Commands

Commands with [LONG] contain optional items that modify the functionality.
EXIT ends the library process
COMMANDS outputs this help string
LIST ALL [LONG] outputs either the short or long string for all books
LIST AVAILABLE [LONG] outputs either the short of long string for all available books
NUMBER COPIES outputs the number of copies of each book
LIST GENRES outputs the name of every genre in the system
LIST AUTHORS outputs the name of every author in the system
GENRE <genre> outputs the short string of every book with the specified genre
AUTHOR <author> outputs the short string of every book by the specified author
BOOK <serialNumber> [LONG] outputs either the short or long string for the specified book
BOOK HISTORY <serialNumber> outputs the rental history of the specified book
MEMBER <memberNumber> outputs the information of the specified member
MEMBER BOOKS <memberNumber> outputs the books currently rented by the specified member
MEMBER HISTORY <memberNumber> outputs the rental history of the specified member
RENT <memberNumber> <serialNumber> loans out the specified book to the given member
RELINQUISH <memberNumber> <serialNumber> returns the specified book from the member
RELINQUISH ALL <memberNumber> returns all books rented by the specified member
ADD MEMBER <name> adds a member to the system
ADD BOOK <filename> <serialNumber> adds a book to the system
ADD COLLECTION <filename> adds a collection of books to the system
SAVE COLLECTION <filename> saves the system to a csv file
COMMON <memberNumber1> <memberNumber2> ... outputs the common books in members’ history
