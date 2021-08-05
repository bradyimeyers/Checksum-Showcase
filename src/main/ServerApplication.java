package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;



@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
	//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    
	    public static String createHash(String name) throws NoSuchAlgorithmException {
	    	MessageDigest md = MessageDigest.getInstance("SHA-256");
	    	byte[] hash = md.digest(name.getBytes(StandardCharsets.UTF_8));
	    	BigInteger number = new BigInteger(1, hash);
	    	StringBuilder hexString = new StringBuilder(number.toString(19));
	    	while (hexString.length() < 32) {
	    		hexString.insert(0, '0');
	    	}
	    	return hexString.toString();
	    }
		
		@RequestMapping("/hash")
	    public String myHash() throws NoSuchAlgorithmException{
	    	String data = "Hello Brady Meyers!";
	    	String hash = createHash(data);
	       
	        return "<p>data:"+data;
	    }
	}
