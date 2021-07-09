package com.urlshort.lowes.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshort.lowes.dao.IShortenerDAO;
import com.urlshort.lowes.model.Shortener;
import com.urlshort.lowes.utils.Messages;
import com.urlshort.lowes.utils.Utils;

@Service
public class ShortenerBo {

	@Autowired
	private IShortenerDAO shortenerDAO;

	public Map<String, Object> create(Shortener shortener) {
		Map<String, Object> rsMap = new HashMap<>();
		String message = Messages.FAILED;

		try {
			if(shortener.getOriginalUrl() == null || shortener.getOriginalUrl().trim().length() == 0) {
				message = Messages.ORIGINAL_URL_NOT_EMPTY;
				rsMap.put("message", message);
				return rsMap;
			} else if(!Pattern.matches(Messages.REGEX, shortener.getOriginalUrl())) {
				message = Messages.ORIGINAL_URL_INVALID;
				rsMap.put("message", message);
				return rsMap;
			}
			
			String code = Utils.generateShortText();
			shortener.setShortUrlCode(code);
			shortener.setCount(0);
			Shortener s = shortenerDAO.save(shortener);
			if (s != null && s.getId() != null) {
				message = Messages.SUCCESS;
				rsMap.put("message", message);
				rsMap.put("data", s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public List<Shortener> getAll() {
		return (List<Shortener>) shortenerDAO.findAll();
	}
	
	public Shortener getByCode(String code) {
		List<Shortener> li = shortenerDAO.findByShortUrlCode(code);
		return li.size() > 0 ? li.get(0) : new Shortener();
	}
	
	public String updateCount(String code) {
		String message = Messages.FAILED;
		try {
			Shortener shortener = getByCode(code);
			if(shortener != null && shortener.getId() != null) {
				shortener.setCount(shortener.getCount() + 1);
				shortenerDAO.save(shortener);
				message = shortener.getOriginalUrl();
			} else {
				message = Messages.NOT_FOUND;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
}
