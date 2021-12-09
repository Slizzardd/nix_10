package ua.com.alevel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Author extends BaseEntity {
    private String name;
    private String surname;
    private String dateOfBirth;
    private Set<Integer> books;

    public Author() {
        super();
        this.books = new HashSet<>();
    }

    @Override
    public String toString() {
        String bookId;
        if(books.isEmpty()) bookId = "Unknown";
        else bookId = books.toString();
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", book id=" + bookId +
                '}';
    }
}
