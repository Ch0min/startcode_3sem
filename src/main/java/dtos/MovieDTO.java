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
import java.util.stream.Collectors;


public class MovieDTO {
    private int id;
    private int year;
    private String title;

    public MovieDTO(Movie m) {
        if (m.getId() != 0)
            this.id = m.getId();
        this.year = m.getYear();
        this.title = m.getTitle();
    }

    public static List<MovieDTO> toList(List<Movie> movies) {
        return movies.stream().map(MovieDTO::new).collect(Collectors.toList());
    }

    public Movie getEntity(){
        Movie m = new Movie(this.year, this.title);
        if(id != 0)
            m.setId(this.id);
        return m;
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
        return "MovieDTO{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return id == movieDTO.id && year == movieDTO.year && Objects.equals(title, movieDTO.title) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title);
    }

}
