package buildingBlocks.book;

import buildingBlocks.enums.BookAccess;

public class Book {
   private String title;
   private String author;
   private String ISBN;
   private String genre;
   private double rating;
   private BookAccess access;
   private boolean isRead;

   public Book() {
       this.title = "";
       this.author = "";
       this.ISBN = "";
       this.genre = "";
       this.rating = 0.0;
       this.access = BookAccess.ANNOUNCED;
       this.isRead = false;
   }

    public Book(String title, String author, String ISBN, String genre, double rating, BookAccess access, boolean isRead) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.rating = rating;
        this.access = access;
        this.isRead = isRead;
    }

   public double getRating() {
       return rating;
   }
   public void setRating(double rating) {
       this.rating = rating;
   }

   public boolean getIsRead() {
       return isRead;
   }

   public void setIsRead(boolean isRead) {
       this.isRead = isRead;
   }
}
