package com.zee.zee5app.repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.service.UserServiceImpl;
import com.zee.zee5app.utils.DBUtils;

public class UserRepositoryImpl implements UserRepo {
	private UserRepositoryImpl()
	{
		
	}
	
	private static UserRepositoryImpl userRepo;
	
	public static UserRepositoryImpl getInstance()
	{
		if(userRepo == null)
		{
			userRepo = new UserRepositoryImpl();
		}
		
		return userRepo;
	}
	private DBUtils dbUtils= DBUtils.getInstance();

	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		//connection object
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String insertStatement ="insert into user_table "
				+ "(userid,firstname,lastname,email,doj,dob,active) "
				+ "value(?,?,?,?,?,?,?)";
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement=connection.prepareStatement(insertStatement);
			 preparedStatement.setString(1,dbUtils.userIdGenerator(user.getFirstName(), user.getLastName()));
			 preparedStatement.setString(2,user.getFirstName());
			 preparedStatement.setString(3,user.getLastName());
			 preparedStatement.setString(4,user.getEmail());
			 //preparedStatement.setDate(5, new Date(user.getDoj().toEpochDay()));
			 preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			 preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			 preparedStatement.setBoolean(7, user.isActive());
			 
			 int result=preparedStatement.executeUpdate();
			 System.out.println(result);
			 if(result>0) return user;
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
	public User updateUserById(String userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserById(String userId) throws  NoDataFoundException {
		
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String deleteStatement= "delete from user_table where userid=?";
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			preparedStatement =connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, userId);
			 int result= preparedStatement.executeUpdate();
			 if(result>0) return "success";
			 else throw new NoDataFoundException("No data found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally
		 {
			 dbUtils.closeConnection(connection);
		 }
		 
		return null;
	
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		List<User> users=new ArrayList<>();
		String query ="select * from user_table";
		ResultSet resultSet=null;
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement =connection.prepareStatement(query);
			 
			 resultSet= preparedStatement.executeQuery();
			 while(resultSet.next())
			 {
				 //record exists
				 //inside the resultset object
				 //User object from resultset data
				 User user =new User();
				 user.setUserId(resultSet.getString("userId"));
				 user.setFirstName(resultSet.getString("firstname"));
				 user.setLastName(resultSet.getString("lastname"));
				 user.setEmail(resultSet.getString("email"));
				 user.setDoj(resultSet.getDate("doj").toLocalDate());
				 user.setDob(resultSet.getDate("dob").toLocalDate());
				 user.setActive(resultSet.getBoolean("active"));
				 users.add(user);
				 
			 }
			 return Optional.of(users);
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 finally {
			 dbUtils.closeConnection(connection);
			
		}
		
		return Optional.empty();
	
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> getUserById(String userId) {
		// TODO Auto-generated method stub
		// connection object
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String query ="select * from user_table where userid=? ";
		ResultSet resultSet=null;
		//statement object(prepared)
		 connection= dbUtils.getConnection();
		 try {
			 preparedStatement =connection.prepareStatement(query);
			 preparedStatement.setString(1, userId);
			 resultSet= preparedStatement.executeQuery();
			 if(resultSet.next())
			 {
				 //record exists
				 //inside the resultset object
				 //User object from resultset data
				 User user =new User();
				 user.setFirstName(resultSet.getString("firstname"));
				 user.setLastName(resultSet.getString("lastname"));
				 user.setEmail(resultSet.getString("email"));
				 user.setDoj(resultSet.getDate("doj").toLocalDate());
				 user.setDob(resultSet.getDate("dob").toLocalDate());
				 user.setActive(resultSet.getBoolean("active"));
				 return Optional.of(user);
			 }
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return Optional.empty();
	}

}
