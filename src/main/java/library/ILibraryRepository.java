package library;

public interface ILibraryRepository {
    void sortLibraryByAuthor();
    void sortLibraryByGenre();
    void sortLibraryByName();
    int getAvailableLibraryID();
}
