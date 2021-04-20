## How to run
<br />
<br />java Library

## Info    
<br />
<br />Librarian stores collections of Member accounts and Book objects. Member accounts are identified by their member number, and store their name, the books they are currently renting, and their rental history.
<br />Member accounts start at 100000 and increment up (such that the second Member account will have member number 100001).
<br />A Book is identified by its serial number, and store the title, author, genre, member currently, and rental history. Members are able to rent and return books, with only one member able to rent a book at any time.
<br />Books are considered copies of each other if they have the same title and author (note that copies of a book all have different serial numbers).
<br />Book collections can be read from and archived in a CSV (comma-separate values) file, stored in order of serial number in the following form:
<br />
<br />[serial number],[title],[author],[genre]
<br />
<br />Example:
<br />111111,Catch 22,Joseph Heller,Comedy 
  
## Commands
<br />
<br />Commands with [LONG] contain optional items that modify the functionality.<br />
<br />EXIT ends the library process
<br />COMMANDS outputs this help string
<br />LIST ALL [LONG] outputs either the short or long string for all books
<br />LIST AVAILABLE [LONG] outputs either the short of long string for all available books
<br />NUMBER COPIES outputs the number of copies of each book
<br />LIST GENRES outputs the name of every genre in the system
<br />LIST AUTHORS outputs the name of every author in the system
<br />GENRE <genre> outputs the short string of every book with the specified genre
<br />AUTHOR <author> outputs the short string of every book by the specified author
<br />BOOK <serialNumber> [LONG] outputs either the short or long string for the specified book
<br />BOOK HISTORY <serialNumber> outputs the rental history of the specified book
<br />MEMBER <memberNumber> outputs the information of the specified member
<br />MEMBER BOOKS <memberNumber> outputs the books currently rented by the specified member
<br />MEMBER HISTORY <memberNumber> outputs the rental history of the specified member
<br />RENT <memberNumber> <serialNumber> loans out the specified book to the given member
<br />RELINQUISH <memberNumber> <serialNumber> returns the specified book from the member
<br />RELINQUISH ALL <memberNumber> returns all books rented by the specified member
<br />ADD MEMBER <name> adds a member to the system
<br />ADD BOOK <filename> <serialNumber> adds a book to the system
<br />ADD COLLECTION <filename> adds a collection of books to the system
<br />SAVE COLLECTION <filename> saves the system to a csv file
<br />COMMON <memberNumber1> <memberNumber2> ... outputs the common books in members’ history
