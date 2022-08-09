package com.zee.zee5app.repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.utils.DBUtils;

public class MovieRepositoryImpl implements MovieRepository {
	
	//private List<Movie> movies = new ArrayList<>();
	//private Set<Movie> movies = new HashSet<Movie>();
	
	private MovieRepositoryImpl() {
        // TODO Auto-generated constructor stub
    }
    
    private static MovieRepositoryImpl movieRepo;
    
    public static MovieRepositoryImpl getInstance() {
        // userRepo object
        
        if(movieRepo == null) {
            movieRepo = new MovieRepositoryImpl();
            
        }
        
        return movieRepo;
    }
    private DBUtils dbUtils= DBUtils.getInstance();
    @Override
	public Movie insertMovie(Movie movie) {
		// TODO Auto-generated method stub
    	Connection connection=null;
		PreparedStatement preparedStatement=null;
		String insertStatement ="insert into movies_table "
				+ "(movieid,actors,moviename,director,genre,production,languages,movielength,trailer) "
				+ "value(?,?,?,?,?,?,?,?,?)";
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement=connection.prepareStatement(insertStatement);
			 preparedStatement.setString(1,movie.getMovieId());
			 String[] actors_name=movie.getActors();
			 
			 String actor = String.join(",", actors_name);
			 //String actor  =String.join(",", actors_name);
//			 for (String string : actors_name) {
//				 actor.join(",", string);
//				
//			}
			 //String[] langauge_name=movie.getLanguages();
			 
			 String langauge= String.join(",", movie.getLanguages());
			 
			 preparedStatement.setString(2,actor);
			 preparedStatement.setString(3,movie.getMovieName());
			 preparedStatement.setString(4,movie.getDirector());
			 //preparedStatement.setDate(5, new Date(user.getDoj().toEpochDay()));
			 preparedStatement.setString(5, movie.getGenre().name());
			 preparedStatement.setString(6, movie.getProduction());
			 preparedStatement.setString(7, langauge);
			 preparedStatement.setFloat(8, movie.getMovieLength());
			 preparedStatement.setString(9, movie.getTrailer1());
			 
			 int result=preparedStatement.executeUpdate();
			 System.out.println(result);
			 if(result>0) return movie;
			 else return null;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		 finally {
			dbUtils.closeConnection(connection);
		}
		//we should get the result based on that we will return the 
		//result
		return null;
	}
	@Override
	public Optional<Movie> updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<List<Movie>> getAllMovies() throws InvalidIdException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String query ="select * from movies_table";
		List<Movie> movies=new ArrayList<>();
		ResultSet resultSet=null;
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement =connection.prepareStatement(query);
			 
			 resultSet= preparedStatement.executeQuery();
			 while(resultSet.next())
				try {
					{
						 /*//record exists
						 //inside the resultset object
						 //movie object from resultset data*/
						 
						 Movie movie =new Movie();
						 movie.setMovieId(resultSet.getString("movieid"));
						 String actors[] = resultSet.getString("actors").split(",");
						 movie.setActors(actors);
						 movie.setMovieName(resultSet.getString("moviename"));
						 movie.setDirector(resultSet.getString("director"));
						 movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
						 movie.setProduction(resultSet.getString("production"));
						 String languages[] = resultSet.getString("languages").split(",");
						 movie.setLanguages(languages);
						 movie.setMovieLength(resultSet.getFloat("movielength"));
						 movie.setTrailer1(resultSet.getString("trailer"));
						 movies.add(movie);
					 }
				}
			 catch (InvalidNameException | InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 return Optional.of(movies);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		 finally {
			 dbUtils.closeConnection(connection);
			
		}
		return Optional.empty();
	}
	@Override
	public Optional<Movie[]> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public Optional<List<Movie>> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String query ="select * from movies_table";
		List<Movie> movies=new ArrayList<>();
		ResultSet resultSet=null;
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement =connection.prepareStatement(query);
			 
			 resultSet= preparedStatement.executeQuery();
			 while(resultSet.next())
				if(resultSet.getString("moviename").equals(movieName)) {
				
					
					Movie movie =new Movie();
					movie.setMovieId(resultSet.getString("movieid"));
					String actors[] = resultSet.getString("actors").split(",");
					movie.setActors(actors);
					movie.setMovieName(resultSet.getString("moviename"));
					movie.setDirector(resultSet.getString("director"));
					movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
					movie.setProduction(resultSet.getString("production"));
					String languages[] = resultSet.getString("languages").split(",");
					movie.setLanguages(languages);
					movie.setMovieLength(resultSet.getFloat("movielength"));
					movie.setTrailer1(resultSet.getString("trailer"));
					movies.add(movie);
				}
						 /*//record exists
						 //inside the resultset object
						 //movie object from resultset data*/
					 
				
			
			 return Optional.of(movies);
			
		} catch(SQLException | InvalidLengthException | InvalidNameException | InvalidIdException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		 finally {
			 dbUtils.closeConnection(connection);
			
		}
		return Optional.empty();
	}
	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) throws InvalidNameException, InvalidLengthException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String query ="select * from movies_table where movieid=? ";
		ResultSet resultSet=null;
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement =connection.prepareStatement(query);
			 preparedStatement.setString(1, movieId);
			 resultSet= preparedStatement.executeQuery();
			 if(resultSet.next())
				try {
					{
						 /*//record exists
						 //inside the resultset object
						 //movie object from resultset data*/
						 
						 Movie movie =new Movie();
						 String actors[] = resultSet.getString("actors").split(",");
						 movie.setActors(actors);
						 movie.setMovieName(resultSet.getString("moviename"));
						 movie.setDirector(resultSet.getString("director"));
						 movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
						 movie.setProduction(resultSet.getString("production"));
						 String languages[] = resultSet.getString("languages").split(",");
						 movie.setLanguages(languages);
						 movie.setMovieLength(resultSet.getFloat("movielength"));
						 movie.setTrailer1(resultSet.getString("trailer"));
						 return Optional.of(movie);
					 }
				} catch (InvalidNameException | InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return Optional.empty();
	}
	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Optional<Movie> updateMovie(String movieId, Movie movie) {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieByMovieId(movieId);
//		if(!optional.isPresent())
//		{
//			return Optional.empty();
//		}
//		else
//		{
//			movies.remove(optional.get());
//			movies.add(movie);
//			return Optional.of(movie);
//		}
//		
//	}
//
//	@Override
//	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieByMovieId(movieId);
//		
//		Movie m = optional.get();
//		
//		if(optional.isPresent())
//		{
//			movies.remove(m);
//			return "sucecess";
//		}
//		else
//		{
//			throw new NoDataFoundException("No data found");
//		}
//	}
//
//	@Override
//	public Optional<List<Movie>> getAllMovies() {
//		// TODO Auto-generated method stub
//		if(this.movies.isEmpty())
//		{
//			return Optional.empty();
//		}
//		
//		Movie[] movie = new Movie[this.movies.size()];
//		this.movies.toArray(movie);
//		
//		return Optional.of(new ArrayList<>(this.movies));
//	}
//
//	@Override
//	public Optional<Movie[]> getAllMoviesByGenre(String genre) {
//		// TODO Auto-generated method stub
//		List<Movie> list = new ArrayList<>();
//		for (Movie movie : movies) {
//			if(genre.equals(movie.getGenre().name()))
//			{
//				list.add(movie);
//			}
//					
//		}
//		if(list.isEmpty())
//		{
//			return Optional.empty();
//		}
//		Movie[] result = new Movie[list.size()];
//		list.toArray(result);
//		
//		return Optional.of(result);
//	}
//
//	@Override
//	public Optional<Movie[]> getAllMoviesByName(String movieName) {
//		// TODO Auto-generated method stub
//		List<Movie> list = new ArrayList<>();
//		for (Movie movie : movies) {
//			if(movieName.equals(movie.getMovieName()))
//			{
//				list.add(movie);
//			}
//					
//		}
//		if(list.isEmpty())
//		{
//			return Optional.empty();
//		}
//		Movie[] result = new Movie[list.size()];
//		list.toArray(result);
//		
//		return Optional.of(result);
//	}
//
//	@Override
//	public Optional<Movie> getMovieByMovieId(String movieId) {
//		// TODO Auto-generated method stub
//		for (Movie movie : movies) {
//			if(movie!=null && movieId.equals(movie.getMovieId()))
//			{
//				return Optional.of(movie);
//			}
//		}
//		return Optional.empty();
//	}
//
//	@Override
//	public List<Movie> findByOrderByMovieNameDsc() {
//		// TODO Auto-generated method stub
//		Comparator<Movie> comparator = (e1,e2)->{
//			return e2.getMovieName().compareTo(e1.getMovieName());
//		};
//		
//		List<Movie> m = new ArrayList<>(movies);
//		
//		Collections.sort(m,comparator);
//		return m;
//	}



}
