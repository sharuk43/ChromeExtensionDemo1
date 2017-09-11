package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import service.DBService;

@Controller
@RequestMapping("/dbservice")
public class DBController {
	
	
	@Autowired
	private DBService dbService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/insertRow", method = RequestMethod.POST)
	public @ResponseBody void insertRow (@RequestBody beans.InputRequestBean input){

		try {
			dbService.insertRow(input.getMobileNumbers());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
