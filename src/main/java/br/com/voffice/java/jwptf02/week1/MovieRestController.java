package br.com.voffice.java.jwptf02.week1;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

	@Autowired
	private MovieRepository movieRepository;

	/**
	 * curl -v localhost:8080/api/movies
	 */
	@GetMapping
	public Collection<Movie> findMovies(boolean forceError) {
		if (forceError)
			throw new IllegalArgumentException("error simulated by using parameter forceError");
		return movieRepository.findAll();
	}

	/**
	 * curl -v -X POST localhost:8080/api/movies -d '{"title":"Movie title"}' -H
	 * 'Content-Type: application/json'
	 *
	 * @throws IOException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addMovie(@RequestBody Movie movie) {
		return movieRepository.create(movie);
	}

	/**
	 * curl -v localhost:8080/api/movies/1
	 */
	@GetMapping("/{id}")
	public Movie findMovie(@PathVariable Long id) {
		return movieRepository.findById(id);
	}

	/**
	 * curl -v --request DELETE localhost:8080/api/movies/:id
	 */
	@DeleteMapping("/{id}")
	public void removeMovie(@PathVariable Long id) {
		if (movieRepository.contains(id)) {
			movieRepository.remove(id);
		} else {
			throw new NotFoundException(id);
		}
	}

	/**
	 * curl -v --request PUT --data '{"title": "Blade Runner 2049"}' --header
	 * 'Content-Type: application/json' localhost:8080/movies/1
	 */
	@PutMapping("/{id}")
	public void editMovie(@PathVariable Long id, @RequestBody Movie movie) {
		if (movieRepository.contains(id)) {
			movie.setId(id);
			movieRepository.update(movie);
		} else {
			throw new NotFoundException(id);
		}
	}

	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class NotFoundException extends IllegalArgumentException {
		NotFoundException(Long id) {
			super(String.format("404 Movie %s not found", id));
		}
	}

}
