package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class DemoController {

	@GetMapping(value = "/getStatus", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getEmployees(@RequestParam String status){
		
		if(status.equalsIgnoreCase("short")) {
			return new ResponseEntity<>("{'status' : 'OK'}", HttpStatus.OK);
		} else if(status.equalsIgnoreCase("Full")) {
			return ResponseEntity.ok().header("content-type", "application/json").body("{'status' : 'Full'}");
		}
		throw new InternalRequestFormatException("Bad REquest");		
		
	}
	
	@ExceptionHandler(InternalRequestFormatException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public ResponseEntity<String> handleAssortmentBadRequestException(InternalRequestFormatException exception) {
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Content-type", 
	      "application/json");
		return ResponseEntity.badRequest()
			      .headers(responseHeaders)
			      .body("Error REsponse bcoz of Bad REquest");
	  }
	
	
}
