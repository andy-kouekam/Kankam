package com.kankam.projet.kankam;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HelloController {
	
	
	
	 @GetMapping(value = "/")
	 public void HelloController() throws ParseException, java.text.ParseException  {
		 System.out.println("andy");
		 GetListOfNewReleases.getListOfNewReleases_Sync();
	      
	    }
	
}
