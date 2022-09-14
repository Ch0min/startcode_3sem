package facades;

import dtos.MovieDTO;
import dtos.PersonDTO;
import entities.Movie;
import entities.Person;
import errorhandling.MovieNotFoundException;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movieEntity = new Movie(movieDTO.getYear(), movieDTO.getTitle());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movieEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(movieEntity);
    }

    public MovieDTO updateMovie(MovieDTO movieDTO) {
        EntityManager em = getEntityManager();
        Movie fromDB = em.find(Movie.class, movieDTO.getId());
        if (fromDB == null)
            throw new EntityNotFoundException("No such Movie with id: " + movieDTO.getId());

        Movie movieEntity = new Movie(movieDTO.getId(), movieDTO.getYear(), movieDTO.getTitle());
        try {
            em.getTransaction().begin();
            em.merge(movieEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(movieEntity);
    }

    public MovieDTO getMovie(long id) throws MovieNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null)
            throw new MovieNotFoundException("The Movie entity with ID: "+id+" Was not found");
        return new MovieDTO(movie);
    }

    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movies = query.getResultList();
        return MovieDTO.getDTOS(movies);
    }

    public Movie deleteMovie(long id) throws MovieNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null) {
            throw new MovieNotFoundException("No such Movie exist with the id " + id);
        }
        try {
            em.getTransaction().begin();
            em.remove(movie);
            em.getTransaction().commit();
            return movie;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade mf = getMovieFacade(emf);
        mf.getAllMovies().forEach(dto -> System.out.println(dto));
//        pf.update(new PersonDTO(6, "Fido", 3));
    }

}
