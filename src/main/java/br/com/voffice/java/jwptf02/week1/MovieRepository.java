package br.com.voffice.java.jwptf02.week1;

import java.util.Collection;

public interface MovieRepository {

	Collection<Movie> findAll();

	Long create(Movie movie);

	Movie findById(Long id);

	void update(Movie movie);

	void remove(Long id);

	boolean contains(Long id);

}
