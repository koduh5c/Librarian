import java.util.*;
import java.io.*;

public class Book implements Comparable<Book> {

  private String title;
  private String author;
  private String genre;
  private String serialNumber;
  private Member currentRenter;
  private List<Member> transactionHistory;

  public Book(String title, String author, String genre, String serialNumber) {
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.serialNumber = serialNumber;
    this.transactionHistory = new ArrayList<Member>();
  }

  public String getTitle() {
    return this.title;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getGenre() {
    return this.genre;
  }

  public String getSerialNumber() {
    return this.serialNumber;
  }

  public String longString() {
    if (currentRenter == null) {
      return String.format("%s: %s (%s, %s)\nCurrently available.", serialNumber, title, author, genre);
    }
    return String.format("%s: %s (%s, %s)\nRented by: %s.", serialNumber, title, author, genre, currentRenter.getMemberNumber());
  }

  public String shortString() {
    return String.format("%s (%s)", title, author);
  }

  public List<Member> renterHistory() {
    return this.transactionHistory;
  }

  public boolean isRented() {
    if (this.currentRenter == null) {
      return false;
    }
    return true;
  }

  public boolean rent(Member member) {
    if (member == null || this.currentRenter != null) {
      return false;
    }
    this.currentRenter = member;
    return true;
  }

  public boolean relinquish(Member member) {
    if (member == null || this.currentRenter == null || !this.currentRenter.getMemberNumber().equals(member.getMemberNumber())) {
      return false;
    }
    this.transactionHistory.add(member);
    this.currentRenter = null;
    return true;
  }

  /**
   * Split input line to String array.
   * Return new book if first element equals "serialNumber".
   */
  public static Book readBook(String filename, String serialNumber) {
    if (filename == null || serialNumber == null) {
      return null;
    }
    try {
      Scanner sc = new Scanner(new File(filename));
      sc.nextLine();
      while (sc.hasNextLine()) {
        String[] line = sc.nextLine().split(",");
        if (line[0].equals(serialNumber)) {
          sc.close();
          return new Book(line[1], line[2], line[3], line[0]);
        }
      }
      sc.close();
      return null;
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  /**
   * Split input line to String array.
   * Initialise new book and add to ArrayList with each line.
   * Return ArrayList.
   */
  public static List<Book> readBookCollection(String filename) {
    if (filename == null) {
      return null;
    }
    try {
      List<Book> result = new ArrayList<Book>();
      Scanner sc = new Scanner(new File(filename));
      sc.nextLine();
      while (sc.hasNextLine()) {
        String[] line = sc.nextLine().split(",");
        result.add(new Book(line[1], line[2], line[3], line[0]));
      }
      sc.close();
      return result;
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  public static void saveBookCollection(String filename, Collection<Book> books) {
    if (filename == null || books == null) {
      return;
    }
    try {
      PrintWriter pw = new PrintWriter(filename);
      pw.write("serialNumber,title,author,genre\n");
      for (Book b : books) {
        pw.write(String.format("%s,%s,%s,%s\n", b.getSerialNumber(), b.getTitle(), b.getAuthor(), b.getGenre()));
      }
      pw.flush();
      pw.close();
    } catch (IOException e) {
    }
  }

  /** For each book in "books", add each book to ArrayList if book's genre matches genre, sort and return list. */
  public static List<Book> filterGenre(List<Book> books, String genre) {
    if (books == null || genre == null) {
      return null;
    }
    List<Book> result = new ArrayList<Book>();
    for (Book b : books) {
      if (b.getGenre().equals(genre)) {
        result.add(b);
      }
    }
    Collections.sort(result);
    return result;
  }

  /** For each book in "books", add each book to ArrayList if book's author matches author, sort and return list. */
  public static List<Book> filterAuthor(List<Book> books, String author) {
    if (books == null || author == null) {
      return null;
    }
    List<Book> result = new ArrayList<Book>();
    for (Book b : books) {
      if (b.getAuthor().equals(author)) {
        result.add(b);
      }
    }
    Collections.sort(result);
    return result;
  }

  @Override
  public int compareTo(Book book) {
    return this.serialNumber.compareTo(book.serialNumber);
  }

}