package com.urlshort.lowes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.urlshort.lowes.bo.ShortenerBo;
import com.urlshort.lowes.model.Shortener;
import com.urlshort.lowes.utils.Messages;

@RestController
@RequestMapping("/api/urlshortener")
public class ShortenerController {
	
	@Autowired
	private ShortenerBo shortenerBo;
	
	@RequestMapping(path="/create", method=RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody Shortener shortener) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = Messages.FAILED;
		try {
			message = shortenerBo.create(shortener);
			if(message.equals(Messages.SUCCESS)) {
				status = HttpStatus.OK;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(message, status);
	}
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public ResponseEntity<List<Shortener>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Shortener> resp = new ArrayList<>();
		try {
			resp = shortenerBo.getAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Shortener>>(resp, status);
	}
	
	@RequestMapping(path="/{code}", method=RequestMethod.GET)
	public ResponseEntity<Shortener> getByShortCode(@PathVariable("code") String code) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Shortener shortener = new Shortener();
		try {
			shortener = shortenerBo.getByCode(code);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Shortener>(shortener, status);
	}
	
	@RequestMapping(path="/{code}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateCount(@PathVariable("code") String code) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = Messages.FAILED;
		try {
			message = shortenerBo.updateCount(code);
			if(message.equals(Messages.SUCCESS) || message.equals(Messages.NOT_FOUND)) {
				status = HttpStatus.OK;
			}
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(message, status);
	}

}
