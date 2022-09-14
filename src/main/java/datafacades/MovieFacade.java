package datafacades;

import entities.Movie;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieFacade implements IDataFacade<Movie>{

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

    @Override
    public Movie create(Movie movie) {
        EntityManager em = getEntityManager();
        Movie movieEntity = new Movie(movie.getYear(), movie.getTitle());
        try {
            em.getTransaction().begin();
            em.persist(movieEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return movieEntity;
    }

    @Override
    public Movie update(Movie movie) {
        EntityManager em = getEntityManager();
        if (movie.getId() == 0)
            throw new javax.persistence.EntityNotFoundException("No such Movie with id: " + movie.getId());
            em.getTransaction().begin();
            Movie m = em.merge(movie);
            em.getTransaction().commit();
        return m;
    }

    public Movie getById(int id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null)
            throw new EntityNotFoundException("The Movie entity with ID: "+id+" Was not found");
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movies = query.getResultList();
        return movies;
    }

    public Movie delete(int id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, id);
        if (movie == null) {
            throw new EntityNotFoundException("No such Movie exist with the id " + id);
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
        mf.getAll().forEach(dto -> System.out.println(dto));
//        pf.update(new PersonDTO(6, "Fido", 3));
    }

}
