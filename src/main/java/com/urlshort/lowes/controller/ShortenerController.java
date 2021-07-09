package com.urlshort.lowes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<Shortener> create(@RequestBody Shortener shortener) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Shortener s = null;
		try {
			Map<String, Object> rsMap = shortenerBo.create(shortener);
			if(rsMap.get("message").equals(Messages.SUCCESS)) {
				status = HttpStatus.OK;
				s = (Shortener) rsMap.get("data");
			} else if(rsMap.get("message").equals(Messages.ORIGINAL_URL_INVALID) || rsMap.get("message").equals(Messages.ORIGINAL_URL_NOT_EMPTY)) {
				status = HttpStatus.BAD_REQUEST;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Shortener>(s, status);
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
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(message, status);
	}

}
