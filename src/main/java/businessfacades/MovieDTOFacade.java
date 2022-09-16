package businessfacades;

import datafacades.IDataFacade;
import datafacades.MovieFacade;
import dtos.MovieDTO;
import entities.Movie;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import java.util.List;

public class MovieDTOFacade implements IDataFacade<MovieDTO> {
    private static IDataFacade<MovieDTO> instance;
    private static IDataFacade<Movie> movieFacade;

    //Private Constructor to ensure Singleton
    private MovieDTOFacade() {}

    public static IDataFacade<MovieDTO> getFacade() {
        if (instance == null) {
            movieFacade = MovieFacade.getMovieFacade(EMF_Creator.createEntityManagerFactory());
            instance = new MovieDTOFacade();
        }
        return instance;
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
        Movie m = movieDTO.getEntity();
        m = movieFacade.create(m);
        return new MovieDTO(m);
    }

    @Override
    public MovieDTO getById(int id) throws EntityNotFoundException {
        return new MovieDTO(movieFacade.getById(id));
    }

    @Override
    public List<MovieDTO> getAll() {
        return MovieDTO.toList(movieFacade.getAll());
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) throws EntityNotFoundException {
        Movie m = movieFacade.update(movieDTO.getEntity());
        return new MovieDTO(m);
    }

    @Override
    public MovieDTO delete(int id) throws EntityNotFoundException {
        return new MovieDTO(movieFacade.delete(id));
    }
}
