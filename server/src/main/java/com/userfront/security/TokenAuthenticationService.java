package com.userfront.security;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.userfront.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.UserAuthentication;
import com.userfront.util.DecryptPassword;



public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";


    private TokenHandler tokenHandler = new TokenHandler();   

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }
    
    public UserAuthentication getAuthenticationForLogin(HttpServletRequest request, HttpServletResponse response, UserDao userDao){
    	UserAuthentication userAuthentication;
    	
    	String OriginalPassword = null;
    	String userName = request.getParameter("userName");
    	String passwordReceived = request.getParameter("password");
    	System.out.println("req username "+userName);
    	System.out.println("req password "+passwordReceived);
    	User user = userDao.findByUsername(userName);
    	
    	String key = user.getSalt();
    	String password = user.getPassword();
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
    	
    	
    	
    	if(userName.equalsIgnoreCase(user.getUsername()) && OriginalPassword.equals(passwordReceived)){
    		
    		userAuthentication = new UserAuthentication(user);
    		userAuthentication.setAuthenticated(true);
    		
    	}else{
    		userAuthentication = new UserAuthentication(null);
    		userAuthentication.setAuthenticated(false);
    	}
    	
    	return userAuthentication;
    	
    }

    public Authentication getAuthentication(HttpServletRequest request,UserDao userDao) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token,userDao);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}