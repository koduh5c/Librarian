import java.util.*;

public class Member {
  
  private String name;
  private String memberNumber;
  private List<Book> currentRental;
  private List<Book> rentalHistory;

  public Member(String name, String memberNumber) {
    this.name = name;
    this.memberNumber = memberNumber;
    this.currentRental = new ArrayList<Book>();
    this.rentalHistory = new ArrayList<Book>();
  }

  public String getName() {
    return this.name;
  }

  public String getMemberNumber() {
    return this.memberNumber;
  }

  public boolean rent(Book book) {
    if (book == null || book.isRented()) {
      return false;
    }
    this.currentRental.add(book);
    book.rent(this);
    return true;
  }

  public boolean relinquish(Book book) {
    if (book == null || !book.isRented() || !book.relinquish(this)) {
      return false;
    }
    this.rentalHistory.add(book);
    this.currentRental.remove(book);
    book.relinquish(this);
    return true;
  }

  public void relinquishAll() {
    for (Book b : this.currentRental) {
      this.rentalHistory.add(b);
      b.relinquish(this);
    }
    this.currentRental.clear();
  }

  public List<Book> history() {
    return this.rentalHistory;
  }

  public List<Book> renting() {
    return this.currentRental;
  }

  /**
   * Initialise a "commonCounter" HashMap to check for common books.
   * For each book in each member's "rentalHistory", initialise a HashSet to filter duplicate books and add to HashSet.
   * For each book in HashSet, add to "commonCounter" then increment value if key exists, else add the book to "commonCounter".
   * Initialise a "result" ArrayList, for each key in "commonCounter", if value matches length of members array, add to "result".
   * Sort "result" then return list.
   */
  public static List<Book> commonBooks(Member[] members) {
    if (members == null) {
      return null;
    }
    Map<Book, Integer> commonCounter = new HashMap<Book, Integer>();
    for (Member m : members) {
      if (m == null) {
        return null;
      }
      Set<Book> hset = new HashSet<Book>();
      for (Book b : m.history()) {
        hset.add(b);
      }
      for (Book b : hset) {
        try {
          commonCounter.put(b, commonCounter.get(b) + 1);
        } catch (NullPointerException e) {
          commonCounter.put(b, 1);
        }
      }
    }
    List<Book> result = new ArrayList<Book>();
    for (Book b : commonCounter.keySet()) {
      if (commonCounter.get(b) == members.length) {
        result.add(b);
      }
    }
    Collections.sort(result);
    return result;
  }

}
