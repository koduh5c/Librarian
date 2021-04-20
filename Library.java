import java.util.*;
import java.io.*;

public class Library {

  private Map<String, Member> membersList;
  private Map<String, Book> archive;
  private int lastMemberNumber;
  public static final String HELP_STRING = "EXIT ends the library process\nCOMMANDS outputs this help string\n\nLIST ALL [LONG] outputs either the short or long string for all books\nLIST AVAILABLE [LONG] outputs either the short of long string for all available books\nNUMBER COPIES outputs the number of copies of each book\nLIST GENRES outputs the name of every genre in the system\nLIST AUTHORS outputs the name of every author in the system\n\nGENRE <genre> outputs the short string of every book with the specified genre\nAUTHOR <author> outputs the short string of every book by the specified author\n\nBOOK <serialNumber> [LONG] outputs either the short or long string for the specified book\nBOOK HISTORY <serialNumber> outputs the rental history of the specified book\n\nMEMBER <memberNumber> outputs the information of the specified member\nMEMBER BOOKS <memberNumber> outputs the books currently rented by the specified member\nMEMBER HISTORY <memberNumber> outputs the rental history of the specified member\n\nRENT <memberNumber> <serialNumber> loans out the specified book to the given member\nRELINQUISH <memberNumber> <serialNumber> returns the specified book from the member\nRELINQUISH ALL <memberNumber> returns all books rented by the specified member\n\nADD MEMBER <name> adds a member to the system\nADD BOOK <filename> <serialNumber> adds a book to the system\n\nADD COLLECTION <filename> adds a collection of books to the system\nSAVE COLLECTION <filename> saves the system to a csv file\n\nCOMMON <memberNumber1> <memberNumber2> ... outputs the common books in members\' history";

  public Library() {
    this.membersList = new LinkedHashMap<String, Member>();
    this.archive = new LinkedHashMap<String, Book>();
    this.lastMemberNumber = 100000;
  }

  public Member findMember(String memberNumber) {
    try {
      return this.membersList.get(memberNumber);
    } catch (NullPointerException e) {
      return null;
    }
  }

  public Book findBook(String serialNumber) {
    try {
      return this.archive.get(serialNumber);
    } catch (NullPointerException e) {
      return null;
    }
  }
  /**
   * If "archive" is empty, print error and return.
   * Initialise an int "cursor".
   * For each book in "archive", print it's long string if "fullString" is true, else print it's short string.
   * While "cursor" is less than "archive" size minus one, print new line if "fullString" is true.
   * Increment cursor at end of loop.
   */
  public void getAllBooks(boolean fullString) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    int cursor = 0;
    for (Book b : this.archive.values()) {
      if (fullString) {
        System.out.println(b.longString());
        if (cursor < this.archive.size() - 1) {
          System.out.println();
        }
      } else {
        System.out.println(b.shortString());
      }
      cursor++;
    }
  }
  
  /**
   * If "archive" is empty, print error and return.
   * Initialise a boolean to false and an int "cursor".
   * For each book in "archive", if book is not rented, set boolean to true and print it's long string if "fullString" is true, else print it's short string.
   * While "cursor" is less than "archive" size minus one, print new line if "fullString" is true.
   * Increment cursor at end of loop.
   * If boolean is false at end of method, print error.
   */
  public void getAvailableBooks(boolean fullString) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    boolean found = false;
    int cursor = 0;
    for (Book b : this.archive.values()) {
      if (!b.isRented()) {
        found = true;
        if (fullString) {
          System.out.println(b.longString());
          if (cursor < this.archive.size() - 1) {
            System.out.println();
          }
        } else {
          System.out.println(b.shortString());
        }
      }
      cursor++;
    }
    if (!found) {
      System.out.println("No books available.");
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Initialise a String, Integer HashMap "bookCounter" and a String ArrayList "list".
   * For each book in archive, increment value for each short string of book in "bookCounter".
   * If returned NullPointerException, put new key and value and add short string to to "list".
   * Sort list and for each String in "list", print formatted String.
   */
  public void getCopies() {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Map<String, Integer> bookCounter = new HashMap<String, Integer>();
    List<String> list = new ArrayList<String>();
    for (Book b : this.archive.values()) {
      try {
        bookCounter.put(b.shortString(), bookCounter.get(b.shortString()) + 1);
      } catch (NullPointerException e) {
        list.add(b.shortString());
        bookCounter.put(b.shortString(), 1);
      }
    }
    Collections.sort(list);
    for (String s : list) {
      System.out.printf("%s: %s\n", s, bookCounter.get(s));
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Initialise String HashSet "hset".
   * For each book in archive, add the genre to "hset".
   * Initialise String ArrayList "list" using "hset" and sort "list".
   * For each String in "list", print the String.
   */
  public void getGenres() {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Set<String> hset = new HashSet<String>();
    for (Book b : this.archive.values()) {
      hset.add(b.getGenre());
    }
    List<String> list = new ArrayList<String>(hset);
    Collections.sort(list);
    for (String s : list) {
      System.out.println(s);
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Initialise String HashSet "hset".
   * For each book in archive, add the author to "hset".
   * Initialise String ArrayList "list" using "hset" and sort "list".
   * For each String in "list", print the String.
   */
  public void getAuthors() {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Set<String> hset = new HashSet<String>();
    for (Book b : this.archive.values()) {
      hset.add(b.getAuthor());
    }
    List<String> list = new ArrayList<String>(hset);
    Collections.sort(list);
    for (String s : list) {
      System.out.println(s);
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Use static "filterGenre" method from Book class to retrieve "list" of books from genre.
   * If "list" is empty, print error and return.
   * Else, for each book in list, print it's short string.
   */
  public void getBooksByGenre(String genre) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    List<Book> list = Book.filterGenre(new ArrayList<Book>(this.archive.values()), genre);
    if (list.isEmpty()) {
      System.out.println("No books with genre " + genre + ".");
      return;
    }
    for (Book b : list) {
      System.out.println(b.shortString());
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Use static "filterAuthor" method from Book class to retrieve "list" of books from author.
   * If "list" is empty, print error and return.
   * Else, for each book in list, print it's short string.
   */
  public void getBooksByAuthor(String author) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    List<Book> list = Book.filterAuthor(new ArrayList<Book>(this.archive.values()), author);
    if (list.isEmpty()) {
      System.out.println("No books by " + author + ".");
      return;
    }
    for (Book b : list) {
      System.out.println(b.shortString());
    }
  }

  /**
   * If "archive" is empty, print error and return.
   * Find "serialNumber" and initialise "book" object.
   * If "book" is null, print error and return.
   * If "fullString" is true, print long string of book, else print short string.
   */
  public void getBook(String serialNumber, boolean fullString) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Book book = this.findBook(serialNumber);
    if (book == null) {
      System.out.println("No such book in system.");
      return;
    }
    if (fullString) {
      System.out.println(book.longString());
    } else {
      System.out.println(book.shortString());
    }
  }

  /**
   * Find "serialNumber" and initialise "book" object.
   * If "book" is null, print error and return.
   * Else, for each member in "book" rent history, print member number.
   */
  public void bookHistory(String serialNumber) {
    Book book = this.findBook(serialNumber);
    if (book == null) {
      System.out.println("No such book in system.");
      return;
    }
    if (book.renterHistory().isEmpty()) {
      System.out.println("No rental history.");
      return;
    }
    for (Member m : book.renterHistory()) {
      System.out.println(m.getMemberNumber());
    }
  }

  /**
   * If "bookFile" doesn't exist, print error and return.
   * Intialise "book" with static "readBook" method from Book class.
   * If "book" is null, print error and return.
   * If "book" exists, print error and return.
   * Add "book" to "archive".
   */
  public void addBook(String bookFile, String serialNumber) {
    if (!(new File(bookFile).exists())) {
      System.out.println("No such file.");
      return;
    }
    Book book = Book.readBook(bookFile, serialNumber);
    if (book == null) {
      System.out.println("No such book in file.");
      return;
    }
    if (this.findBook(book.getSerialNumber()) != null) {
      System.out.println("Book already exists in system.");
      return;
    }
    this.archive.put(book.getSerialNumber(), book);
    System.out.println("Successfully added: " + book.shortString() + ".");
  }

  /**
   * If "memberList" is empty, print error and return.
   * If "archive" is empty, print error and return.
   * Find "memberNumber" and initialise "member" object.
   * If "member" is null, print error and return.
   * Find "serialNumber" and initialise "book" object.
   * If "book" is null, print error and return.
   * If "book" is rented, print error and return.
   * Else rent "book" to "member".
   */
  public void rentBook(String memberNumber, String serialNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    Book book = this.findBook(serialNumber);
    if (book == null) {
      System.out.println("No such book in system.");
      return;
    }
    if (book.isRented()) {
      System.out.println("Book is currently unavailable.");
      return;
    }
    member.rent(book);
    System.out.println("Success.");
  }

  /**
   * If "memberList" is empty, print error and return.
   * If "archive" is empty, print error and return.
   * Find "memberNumber" and initialise "member" object.
   * If "member" is null, print error and return.
   * Find "serialNumber" and initialise "book" object.
   * If "book" is null, print error and return.
   * If "member" relinquishes "book" and returns true, return with 'Success'.
   * Else print error and return.
   */
  public void relinquishBook(String memberNumber, String serialNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    Book book = this.findBook(serialNumber);
    if (book == null) {
      System.out.println("No such book in system.");
      return;
    }
    if (member.relinquish(book)) {
      System.out.println("Success.");
      return;
    }
    System.out.println("Unable to return book.");
  }

  /**
   * If "memberList" is empty, print error and return.
   * Find member and initialise a "member" object.
   * If "member" is null, print error and return.
   * Else, relinquish all books for "member".
   */
  public void relinquishAll(String memberNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    member.relinquishAll();
    System.out.println("Success.");
  }

  /**
   * If "memberList" is empty, print error and return.
   * Find member and initialise a "member" object.
   * If "member" is null, print error and return.
   * Else, print formatted string of member information.
   */
  public void getMember(String memberNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    System.out.println(member.getMemberNumber() + ": " + member.getName());
  }

  /**
   * If "memberList" is empty, print error and return.
   * Find member and initialise a "member" object.
   * If "member" is null, print error and return.
   * If "member" renting is empty, print error and return.
   * Else, for each book in "member" renting, print it's short string.
   */
  public void getMemberBooks(String memberNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    if (member.renting().isEmpty()) {
      System.out.println("Member not currently renting.");
      return;
    }
    for (Book b : member.renting()) {
      System.out.println(b.shortString());
    }
  }

  /**
   * If "memberList" is empty, print error and return.
   * Find member and initialise a "member" object.
   * If "member" is null, print error and return.
   * If "member" history is empty, print error and return.
   * Else, for each book in "member" history, print it's short string.
   */
  public void memberRentalHistory(String memberNumber) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    Member member = this.findMember(memberNumber);
    if (member == null) {
      System.out.println("No such member in system.");
      return;
    }
    if (member.history().isEmpty()) {
      System.out.println("No rental history for member.");
      return;
    }
    for (Book b : member.history()) {
      System.out.println(b.shortString());
    }
  }

  /**
   * Parse "lastMemberNumber" to string and add new Member object to "memberList".
   * Increment lastMemberNumber.
   */
  public void addMember(String name) {
    this.membersList.put(Integer.toString(this.lastMemberNumber), new Member(name, Integer.toString(this.lastMemberNumber)));
    System.out.println("Success.");
    this.lastMemberNumber++;
  }

  /**
   * If "archive" is empty, print error and return.
   * Use static "saveBookCollection" method from Book class.
   */
  public void saveCollection(String filename) {
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Book.saveBookCollection(filename, new ArrayList<Book>(this.archive.values()));
    System.out.println("Success.");
  }

  /**
   * Retrieve ArrayList "books" using static "readBookCollection" method from Book class.
   * Initialise an int "counter".
   * For each book in "books", if book does not exist, add book to archive and increment "counter".
   */
  public void addCollection(String filename) {
    List<Book> books = Book.readBookCollection(filename);
    if (books == null) {
      System.out.println("No such collection.");
      return;
    }
    int counter = 0;
    for (Book b : books) {
      if (this.findBook(b.getSerialNumber()) == null) {
        this.archive.put(b.getSerialNumber(), b);
        counter++;
      }
    }
    if (counter == 0) {
      System.out.println("No books have been added to the system.");
      return;
    }
    System.out.println(counter + " books successfully added.");
  }

  /**
   * If "memberList" is empty, print error and return.
   * If "archive" is empty, print error and return.
   * Initialise a "duplicateChecker" HashSet, a "memberArray" and an int "cursor".
   * For each string in "memberNumbers", return if adding to "duplicateChecker" returns false, else add to "duplicateChecker"
   * Check for "member", return if null, else set "member" to "cursor" position in "memberArray" and increment cursor.
   * Retrieve a book ArrayList using "memberArray" in static "commonBooks" method from Member class and print the short string for each book.
   */
  public void common(String[] memberNumbers) {
    if (this.membersList.isEmpty()) {
      System.out.println("No members in system.");
      return;
    }
    if (this.archive.isEmpty()) {
      System.out.println("No books in system.");
      return;
    }
    Set<String> duplicateChecker = new HashSet<String>();
    Member[] memberArray = new Member[memberNumbers.length];
    int cursor = 0;
    for (String s : memberNumbers) {
      if (!duplicateChecker.add(s)) {
        System.out.println("Duplicate members provided.");
        return;
      }
      duplicateChecker.add(s);
      Member member = this.findMember(s);
      if (member == null) {
        System.out.println("No such member in system.");
        return;
      }
      memberArray[cursor] = member;
      cursor++;
    }
    List<Book> result = Member.commonBooks(memberArray);
    if (result.isEmpty()) {
      System.out.println("No common books.");
      return;
    }
    for (Book b : result) {
      System.out.println(b.shortString());
    }
  }

  public static void main(String[] args) {
    Library lib = new Library();
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("user: ");
      String[] line = sc.nextLine().split(" ");
      if (line.length == 1) {
        if (line[0].equalsIgnoreCase("EXIT")) {
          System.out.println("Ending Library process.");
          sc.close();
          return;
        } else if (line[0].equalsIgnoreCase("COMMANDS")) {
          System.out.println(HELP_STRING);
        }
      } else if (line.length == 2) {
        if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("ALL")) {
          lib.getAllBooks(false);
        } else if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("AVAILABLE")) {
          lib.getAvailableBooks(false);
        } else if (line[0].equalsIgnoreCase("NUMBER") && line[1].equalsIgnoreCase("COPIES")) {
          lib.getCopies();
        } else if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("GENRES")) {
          lib.getGenres();
        } else if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("AUTHORS")) {
          lib.getAuthors();
        } else if (line[0].equalsIgnoreCase("BOOK")) {
          lib.getBook(line[1], false);
        } else if (line[0].equalsIgnoreCase("MEMBER")) {
          lib.getMember(line[1]);
        }
      } else if (line.length == 3) {
        if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("ALL") && line[2].equalsIgnoreCase("LONG")) {
          lib.getAllBooks(true);
        } else if (line[0].equalsIgnoreCase("LIST") && line[1].equalsIgnoreCase("AVAILABLE") && line[2].equalsIgnoreCase("LONG")) {
          lib.getAvailableBooks(true);
        } else if (line[0].equalsIgnoreCase("BOOK") && line[2].equalsIgnoreCase("LONG")) {
          lib.getBook(line[1], true);
        } else if (line[0].equalsIgnoreCase("BOOK") && line[1].equalsIgnoreCase("HISTORY")) {
          lib.bookHistory(line[2]);
        } else if (line[0].equalsIgnoreCase("MEMBER") && line[1].equalsIgnoreCase("BOOKS")) {
          lib.getMemberBooks(line[2]);
        } else if (line[0].equalsIgnoreCase("MEMBER") && line[1].equalsIgnoreCase("HISTORY")) {
          lib.memberRentalHistory(line[2]);
        } else if (line[0].equalsIgnoreCase("RENT")) {
          lib.rentBook(line[1], line[2]);
        } else if (line[0].equalsIgnoreCase("RELINQUISH") && !line[1].equalsIgnoreCase("ALL")) {
          lib.relinquishBook(line[1], line[2]);
        } else if (line[0].equalsIgnoreCase("RELINQUISH") && line[1].equalsIgnoreCase("ALL")) {
          lib.relinquishAll(line[2]);
        } else if (line[0].equalsIgnoreCase("ADD") && line[1].equalsIgnoreCase("COLLECTION")) {
          lib.addCollection(line[2]);
        } else if (line[0].equalsIgnoreCase("SAVE") && line[1].equalsIgnoreCase("COLLECTION")) {
          lib.saveCollection(line[2]);
        }
      } else if (line.length == 4) {
        if (line[0].equalsIgnoreCase("ADD") && line[1].equalsIgnoreCase("BOOK")) {
          lib.addBook(line[2], line[3]);
        }
      }
      if (line[0].equalsIgnoreCase("COMMON")) {
        lib.common(Arrays.copyOfRange(line, 1, line.length));
      }
      if (line[0].equalsIgnoreCase("ADD") && line[1].equalsIgnoreCase("MEMBER")) {
        lib.addMember(String.join(" ", Arrays.copyOfRange(line, 2, line.length)));
      }
      if (line[0].equalsIgnoreCase("AUTHOR")) {
        lib.getBooksByAuthor(String.join(" ", Arrays.copyOfRange(line, 1, line.length)));
      }
      if (line[0].equalsIgnoreCase("GENRE")) {
        lib.getBooksByGenre(String.join(" ", Arrays.copyOfRange(line, 1, line.length)));
      }
      System.out.println();
    }
  }

}
