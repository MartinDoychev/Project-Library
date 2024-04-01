package buildingBlocks.book;

import buildingBlocks.enums.BookAccess;

public class Book {
   private String title;
   private String author;
   private String ISBN;
   private String genre;
   private double rating;
   private BookAccess access;

   public Book() {
       this.title = "";
       this.author = "";
       this.ISBN = "";
       this.genre = "";
       this.rating = 0.0;
       this.access = BookAccess.ANNOUNCED;
   }

    public Book(String title, String author, String ISBN, String genre, double rating, BookAccess access) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.rating = rating;
        this.access = access;
    }

   public double getRating() {
       return rating;
   }
   public void setRating(double rating) {
       this.rating = rating;
   }
}
