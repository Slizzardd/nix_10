package ua.com.alevel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends BaseEntity {
    private String name;
    private Integer pages;
    private Set<Integer> authors;

    public Book() {
        super();
        this.authors = new HashSet<>();
    }

    @Override
    public String toString() {
        String authorsId;
        if (authors.isEmpty()) authorsId = "Unknown";
        else authorsId = authors.toString();
        return "Book{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                ", authors id=" + authorsId +
                '}';
    }
}
