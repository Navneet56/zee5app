package com.zee.zee5app.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.repos.MovieRepository;
import com.zee.zee5app.repos.MovieRepositoryImpl;
import com.zee.zee5app.utils.DBUtils;

public class MovieServiceImpl implements MovieService {

	private MovieServiceImpl()
	{
		
	}
	
	private static MovieService movieService;
	
	public static MovieService getInstance()
	{
		if(movieService == null)
		{
			movieService = new MovieServiceImpl();
		}
		
		return movieService;
	}
	
	private MovieRepository movieRepository = MovieRepositoryImpl.getInstance();
	@Override
	public Movie insertMovie(Movie movie) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// trailer file exists or not
		File file=new File(movie.getTrailer1());
		BufferedInputStream bufferedInputStream= null;
		BufferedOutputStream bufferedOutputStream=null;
		System.out.println(file.getName());
		if(movie.getTrailer1()==null || movie.getTrailer1()==""||
				!file.exists())
		{
			throw new FileNotFoundException();
		}
		else
		{
			 bufferedInputStream= 
					new BufferedInputStream(new FileInputStream(file));
			 bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("d:\\zee5app\\Trailer\\"+file.getName()),2048);
			 try {
				bufferedOutputStream.write(bufferedInputStream.readAllBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file exists");
		}
		
		// shift that file to zee5app/trailer folder
		// provide the location to trailer field
		//then take the path and store it in db .-->handle by repo
		return movieRepository.insertMovie(movie);
	}

	@Override
	public Optional<Movie> updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.updateMovie(movieId, movie);
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		
		return movieRepository.deleteMovieByMovieId(movieId);
	}

	@Override
	public Optional<List<Movie>> getAllMovies() throws InvalidIdException {
		// TODO Auto-generated method stub
		return movieRepository.getAllMovies();
	}

	@Override
	public Optional<Movie[]> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByGenre(genre);
	}

	@Override
	public Optional<List<Movie>> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByName(movieName);
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) throws InvalidNameException, InvalidLengthException {
		// TODO Auto-generated method stub
		return movieRepository.getMovieByMovieId(movieId);
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub

		return movieRepository.findByOrderByMovieNameDsc();
	}

}
