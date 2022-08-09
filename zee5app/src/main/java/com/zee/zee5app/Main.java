package com.zee.zee5app;

import java.beans.beancontext.BeanContextServices;
import java.util.Scanner;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.repos.MovieRepositoryImpl;
import com.zee.zee5app.repos.UserRepoImpl;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.MovieServiceImpl;
import com.zee.zee5app.service.UserServiceImpl;

public class Main {
	
    public static void main(String[] args) {
		
    	
        String userId;
        String result;
        User user = new User();
    	
        UserServiceImpl service = UserServiceImpl.getInstance();
        MovieService movieService = MovieServiceImpl.getInstance();
        
        int choice;
        int countId=1,occupancy=0,movieOccupancy = 0;
        
        while(true) {
        	
        System.out.println("1. User");
        System.out.println("2. Movie");
        System.out.println("Enter your choice");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        
        switch(choice)
        {
        case 1:
        	while(true) {
                System.out.println("1. Get all users");
                System.out.println("2. Get user by id");
                System.out.println("3. Insert user");
                System.out.println("4. Delete user by id");
                System.out.println("5. Update user by id");
                System.out.println("6. Exit");
                System.out.println("Enter your choice!");
                User user1 = new User();
                
                choice = sc.nextInt();
                
                switch(choice)
                {
                // get all users
                case 1:
                	User[] list = service.getAllUsers();
                	if(occupancy == 0)
                	{
                		System.out.println("no records found!");
                	}
                	else
                	{
                		for (User user2 : list) {
        					if(user2 != null)
        					{
        						System.out.println("FirstName " + user2.getFirstName());
        						System.out.println("LastName " + user2.getLastName());
        						System.out.println("Email " + user2.getEmail());
        						System.out.println("===========================");
        					}
        					
        				}
                	}
                	break;
                case 2:
                	// get user by id
                	System.out.println("Enter userId");
                	userId = sc.next();
                	user = service.getUserById(userId);
                	if(user == null)
                	{
                		System.out.println("user not found");
                	}
                	else {
                	System.out.println("FirstName " + user.getFirstName());
                	System.out.println("LastName " + user.getLastName());
                	System.out.println("Email " + user.getEmail());
                	}
                	break;
                case 3:
                	// insert new user
                	System.out.println("enter first name");
                	String fn = sc.next();
                	user1.setFirstName(fn);
                	System.out.println("enter last name");
                	String ln = sc.next();
                	user1.setLastName(ln);
                	System.out.println("enter email id");
                	String id = sc.next();
                	user1.setEmail(id);
                	user1.setActive(true);
                	user1.setDob(null);
                	user1.setDoj(null);
                	user1.setUserId(String.valueOf(countId));
                	countId++;
                	occupancy++;
                    user= service.insertUser(user1);
                	System.out.println(user);
                	break;
                case 4:
                	// delete user by id
                	System.out.println("enter userid to delete");
                	userId = sc.next();
                	System.out.println(service.deleteUserById(userId));
                	occupancy--;
                	break;
                case 5:
                	// update user by userid
                	System.out.println("enter userid to update");
                	String id1 = sc.next();
                	System.out.println("enter first name");
                	String fn1 = sc.next();
                	user.setFirstName(fn1);
                	System.out.println("enter last name");
                	String ln1 = sc.next();
                	user.setLastName(ln1);
                	System.out.println("enter email id");
                	String email = sc.next();
                	user.setEmail(email);
                	user.setActive(true);
                	user.setDob(null);
                	user.setDoj(null);
                	
                	System.out.println(service.updateUserById(id1, user));
                    break;
                    
                case 6:
                	break;
                	
                }
                if(choice == 6)
                	break;
                }
        	break;
        	
        case 2:
        	while(true)
        	{
        		System.out.println("1. Get all movies");
                System.out.println("2. Get movie by id");
                System.out.println("3. Insert movie");
                System.out.println("4. Delete movie by id");
                System.out.println("5. Update movie by id");
                System.out.println("6. Get all movies by genre");
                System.out.println("7. Get all movies by name");
                System.out.println("8. Exit");
                System.out.println("Enter your choice!");
                choice = sc.nextInt();
                switch(choice)
                {
                case 1:
                	// get all movies
                	Movie[] list = movieService.getAllMovies();
                	if(movieOccupancy == 0)
                	{
                		System.out.println("no records found!");
                	}
                	else
                	{
                		for (Movie movie2 : list) {
        					if(movie2 != null)
        					{
        						System.out.println("FirstName " + user2.getFirstName());
        						System.out.println("LastName " + user2.getLastName());
        						System.out.println("Email " + user2.getEmail());
        						System.out.println("===========================");
        					}
        					
        				}
                	}
                	break;	
                }
        	}
        }
        
        
    	
//    	System.out.println(user);
//    	System.out.println(user.toString());
//    	System.out.println(user.getClass().getName());
//    	System.out.println(user.hashCode());
//    	System.out.println(Integer.toHexString(user.hashCode()));
		
		// System: class
		// out: static ref to class
		// println: its method from PrintStream class
    	// user: reference to class User
	}

}
}
