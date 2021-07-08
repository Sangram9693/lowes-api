package com.urlshort.lowes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.urlshort.lowes.model.Shortener;

public interface IShortenerDAO extends CrudRepository<Shortener, Long>{
	public List<Shortener> findByShortUrlCode(String code);
}
