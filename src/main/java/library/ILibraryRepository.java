package library;

import book.Book;

public interface ILibraryRepository {
    void sortLibraryByAuthor();
    void sortLibraryByGenre();
    void sortLibraryByName();
    int getAvailableLibraryID();
    int addToLibrary(Book book, Library library, boolean isRead);
}
