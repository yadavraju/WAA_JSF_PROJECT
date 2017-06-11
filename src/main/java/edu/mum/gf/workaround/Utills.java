package edu.mum.gf.workaround;

import java.util.Random;

import javax.json.Json;
import javax.json.JsonObject;

public class Utills {
	
	public static JsonObject sucessMesseesge(){

		 JsonObject value = Json.createObjectBuilder()
		     .add("status", "Sucess")
		     .add("message", "SucessFully deleted")
		     .build();
		 
		 return value;
	}
	
	public static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
