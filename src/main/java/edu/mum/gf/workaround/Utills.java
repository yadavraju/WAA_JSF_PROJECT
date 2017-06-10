package edu.mum.gf.workaround;

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

}
