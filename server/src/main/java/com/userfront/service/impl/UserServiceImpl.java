package com.userfront.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.dao.MovieDao;
import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.Movie;
import com.userfront.domain.Role;
import com.userfront.domain.User;
import com.userfront.domain.UserMovie;
import com.userfront.domain.UserRole;
import com.userfront.exception.BadRequestException;
import com.userfront.model.Genre;
import com.userfront.model.RatingsAndComments;
import com.userfront.model.UserMovieRating;
import com.userfront.service.UserService;
import com.userfront.util.DecryptPassword;
import com.userfront.util.EncryptPassword;
import com.userfront.util.GenerateRandomKey;



@Service
@Transactional
public class UserServiceImpl implements UserService  {


	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MovieDao movieDao;
	
	@Override
	public User createUser(User user) {
		
		User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
        	throw new BadRequestException("Employee with this email already exists");
        } else {
            
        	Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByRole("ROLE_USER")));
            
            user.getRoles().addAll(userRoles);
            
            KeyGenerator keyGen;
            String key;
			try {
				keyGen = KeyGenerator.getInstance(GenerateRandomKey.AES);
				keyGen.init(128);
		        SecretKey sk = keyGen.generateKey();
		        key = GenerateRandomKey.byteArrayToHexString(sk.getEncoded());
		        System.out.println("key:" + key);
		        user.setSalt(key);
		        
		        byte[] bytekey = EncryptPassword.hexStringToByteArray(key);
		        SecretKeySpec sks = new SecretKeySpec(bytekey, EncryptPassword.AES);
		        Cipher cipher;
			
				cipher = Cipher.getInstance(EncryptPassword.AES);
				cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
			    byte[] encrypted = cipher.doFinal(user.getPassword().getBytes());
			    String encryptedpwd = EncryptPassword.byteArrayToHexString(encrypted);
			    
			    System.out.println(encryptedpwd);
		        user.setPassword(encryptedpwd);
		            
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
           
			
			 
            
            localUser=  userDao.createUser(user);
            
            
	}
        return localUser;
	}

	@Override
	public String getImdbPage(Long id) {
		
		return movieDao.getImdbPage(id);
	}

	@Override
	public UserMovie rateMovie(UserMovie userMovie) {
		
		return movieDao.rateMovie(userMovie);
	}

	@Override
	public List<com.userfront.model.Movie> getTopRatedMovies() {
		
		return movieDao.getTopRatedMovies();
	}

	@Override
	public List<Movie> getTopRatedMoviesAndSeries() {
		
		return movieDao.getTopRatedMoviesAndSeries();
	}

	@Override
	public List<Movie> getTopRatedSeries() {
		
		return movieDao.getTopRatedSeries();
	}

	@Override
	public List<Genre> filterByGenre(String id) {
		
		List<Genre> movies= movieDao.filterByGenre(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<RatingsAndComments> getMovieRatingsAndComments(Long id) {
		
		return movieDao.getMovieRatingsAndComments(id);
	}

	@Override
	public List<Movie> filterByType(String id) {
		
		List<Movie> movies= movieDao.filterByType(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<Movie> filterByYear(String id) {
		
		List<Movie> movies= movieDao.filterByYear(id);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
	   // return null;		
		else
		return movies;
		
	}

	@Override
	public List<com.userfront.model.Genre> filterByTypeYearGenre(String type, String year, String genre) {
		List<com.userfront.model.Genre> movies = movieDao.filterByTypeYearGenre(type,year,genre);
		if(movies==null || movies.isEmpty())
		throw new BadRequestException("no movies found");
		else
		return movies;
	}

	@Override
	public List<Movie> sortMoviesBy(String string) {
		return movieDao.sortMoviesBy(string);
		
	}

	@Override
	public List<Movie> sortMoviesBy() {
		 return movieDao.sortMoviesBy();
		
	}

	@Override
	public Double getMovieAverageRating(Long id) {
		
		Double rating= movieDao.getMovieAverageRating(id);
		if(rating == null)
		{
			throw new BadRequestException("no ratings yet");	
		}
		return rating;
	}

	@Override
	public User validateUser(User user) {
		
		User localUser = userDao.findByUsername(user.getUsername());
		String OriginalPassword = null;
		if (localUser == null) {
        	throw new BadRequestException("Employee with this email doesn't exist");
        }else
        {
		
		String key = localUser.getSalt();
		
		String password = localUser.getPassword();
		
		byte[] bytekey = DecryptPassword.hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, DecryptPassword.AES);
        Cipher cipher;
		try {
			cipher = Cipher.getInstance(DecryptPassword.AES);
			cipher.init(Cipher.DECRYPT_MODE, sks);
	        byte[] decrypted = cipher.doFinal(DecryptPassword.hexStringToByteArray(password));
	        OriginalPassword = new String(decrypted);
	        System.out.println(OriginalPassword);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        }
        
      if(OriginalPassword.equals(user.getPassword()))
      {  
    	  user.setUserId(localUser.getUserId());
    	  user.setFirstName(localUser.getFirstName());
    	  user.setPassword("");
    	  return user;
      }
      else
    	  return null;
		
	}

	@Override
	public List<com.userfront.model.Movie> filterByGenre2(String id) {
		// TODO Auto-generated method stub
		return movieDao.filterByGenre2(id);
	}

	@Override
	public UserMovieRating rateTheMovie(UserMovieRating userMovieRating) {
		
		return movieDao.rateTheMovie(userMovieRating);
	}

	
}
