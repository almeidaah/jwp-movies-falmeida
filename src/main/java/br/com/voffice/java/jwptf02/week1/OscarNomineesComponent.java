package br.com.voffice.java.jwptf02.week1;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OscarNomineesComponent {

	private static final String CATEGORY_BEST_PICTURE = "Best Picture";
	private static final Movie callme = new Movie(null, "Call Me By Your Name", LocalDate.of(2017, 1, 1), 0d, "https://drraa3ej68s2c.cloudfront.net/wp-content/uploads/2018/01/24093817/198d42addf5e5e45e6b9d4228ec47239fc247db5df56e44eb8bdbc1fd42b1d97-1024x1024.jpg", 74, CATEGORY_BEST_PICTURE, false);
	private static final Movie darkest = new Movie(null, "Darkest Hour", LocalDate.of(2017, 9, 1), 114_845_157d, "https://image.tmdb.org/t/p/w1280/qtG4Gf3GE7UEdN6ixJXEKwZUSA4.jpg", 72, CATEGORY_BEST_PICTURE, false);
	private static final Movie dunkirk = new Movie(null, "Dunkirk", LocalDate.of(2017, 7, 19), 100_000_000d, "https://image.tmdb.org/t/p/w1280/ebSnODDg9lbsMIaWg2uAbjn7TO5.jpg", 74, CATEGORY_BEST_PICTURE, false);
	private static final Movie getOut = new Movie(null, "Get Out", LocalDate.of(2017, 2,24), 5_000_000d, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjUxMDQwNjcyNl5BMl5BanBnXkFtZTgwNzcwMzc0MTI@._V1_UX182_CR0,0,182,268_AL_.jpg", 74, CATEGORY_BEST_PICTURE, false);
	private static final Movie ladyBird = new Movie(null, "Lady Bird", LocalDate.of(2017, 12, 1), 10_000_000d, "https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg", 76, CATEGORY_BEST_PICTURE, false);
	private static final Movie phantom = new Movie(null, "Phantom Thread", LocalDate.of(2017, 12, 11), 35_000_000d, "https://image.tmdb.org/t/p/w1280/yZ8j8xKk2xQ1d88hB8YG6LZfRQj.jpg", 73, CATEGORY_BEST_PICTURE, false);
	private static final Movie post = new Movie(null, "The Post", LocalDate.of(2017, 12, 14), 50_000_000d, "https://image.tmdb.org/t/p/w1280/qyRwj5VvuTRdJ76o2grP93grNxt.jpg", 70, CATEGORY_BEST_PICTURE, false);
	private static final Movie shapeOfWater = new Movie(null, "Shape of Water", LocalDate.of(2017, 12, 22), 19_400_000d, "https://drraa3ej68s2c.cloudfront.net/wp-content/uploads/2017/12/12163133/87954c22e6e3783117f13feadf7e9681f463b7011a91c7af2ebd1a962d20aa53-195x195.jpg", 74, CATEGORY_BEST_PICTURE, true);
	private static final Movie threeBillboards = new Movie(null, "Three Billboards Outside Ebbing, Missouri", LocalDate.of(2017,12,01), 15_000_000d, "https://images-na.ssl-images-amazon.com/images/M/MV5BZTZjYzU2NTktNTdmNi00OTM0LTg5MDgtNGFjOGMzNjY0MDk5XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg", 82, CATEGORY_BEST_PICTURE,false);
	private static final List<Movie> nominees = Arrays.asList(callme,darkest,dunkirk,getOut,ladyBird,phantom,post,shapeOfWater,threeBillboards);
	@Autowired
	private MovieRepository movieRepository;

	public Collection<Movie> create() {
		nominees.stream().forEach(movieRepository::create);
		return movieRepository.findAll();
	}

}
