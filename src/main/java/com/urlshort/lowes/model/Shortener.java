package com.urlshort.lowes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Shortener {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="short_url_code", unique = true)
	@Length(max=10)
	private String shortUrlCode;
	
	@Column(name="original_url")
	@NotNull(message = "originalUrl has to be present")
	@NotEmpty(message = "originalUrl has to be present")
	private String originalUrl;
	
	@Column
	private int count;

	public Shortener() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shortener(Long id, @NotNull @Length(max = 10) String shortUrlCode, @NotNull String originalUrl, int count) {
		super();
		this.id = id;
		this.shortUrlCode = shortUrlCode;
		this.originalUrl = originalUrl;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrlCode() {
		return shortUrlCode;
	}

	public void setShortUrlCode(String shortUrlCode) {
		this.shortUrlCode = shortUrlCode;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Shortener [id=" + id + ", shortUrlCode=" + shortUrlCode + ", originalUrl=" + originalUrl + ", count="
				+ count + "]";
	}

}
