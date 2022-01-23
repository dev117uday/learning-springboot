package com.amigoscode.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = "SELECT * FROM movie;";
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
                    insert into movie (name, release_date) values (?, ?);
                """;
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        return jdbcTemplate.update("delete from movie where id = ?", id);

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql = "SELECT * FROM movie where id = ?;";
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), id);
        return movies.stream().findFirst();
    }

}
