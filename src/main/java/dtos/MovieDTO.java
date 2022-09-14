/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movie;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tha
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String title;

    public MovieDTO() {
    }

    public MovieDTO(int year, String title) {
        this.year = year;
        this.title = title;
    }

    public MovieDTO(long id, int year, String title) {
        this.id = id;
        this.year = year;
        this.title = title;
    }

    public MovieDTO(Movie m) {
        if (m.getId() != null)
            this.id = m.getId();
        this.year = m.getYear();
        this.title = m.getTitle();

    }

    public static List<MovieDTO> getDTOS(List<Movie> movies) {
        List<MovieDTO> movieDTOS = new ArrayList();
        movies.forEach(movie -> movieDTOS.add(new MovieDTO(movie)));
        return movieDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "MovieDTO{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDTO)) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return getId().equals(movieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
