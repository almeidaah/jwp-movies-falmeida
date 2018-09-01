package br.com.voffice.java.jwptf02.week1;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMovieRepository implements MovieRepository {

	private static Map<Long, Movie> database = new HashMap<>();

	{
		Movie movie = new Movie();
		movie.setBudget(1000.50);
		movie.setCategory("Top Movies");
		movie.setRating(9);
		movie.setReleasedDate(LocalDate.now());
		movie.setTitle("Meu filme 1");
		create(movie);
	}

	@Override
	public Collection<Movie> findAll() {
		return database.values();
	}

	@Override
	public Long create(Movie movie) {
		long id = System.currentTimeMillis();
		movie.setId(id);
		database.put(id, movie);
		return id;
	}

	@Override
	public Movie findById(Long id) {
		return database.get(id);
	}

	@Override
	public void update(Movie movie) {
		database.put(movie.getId(), movie);
	}

	@Override
	public void remove(Long id) {
		if (database.containsKey(id)) {
			database.remove(id);
		} else {
			throw new IllegalArgumentException("movie with id "+id+" not found");
		}
	}

	@Override
	public boolean contains(Long id) {
		return findById(id) != null;
	}

}
