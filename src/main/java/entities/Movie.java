package entities;

import dtos.MovieDTO;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
        @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movie m"),
        @NamedQuery(name = "Movie.getByTitle", query = "SELECT m FROM Movie m WHERE m.title LIKE :title")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "title")
    private String title;

    public Movie() {
    }

    public Movie(int year, String title) {
        this.year = year;
        this.title = title;
    }

    public Movie(int id, int year, String title) {
        this.id = id;
        this.year = year;
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && year == movie.year && Objects.equals(title, movie.title) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title);
    }
}
