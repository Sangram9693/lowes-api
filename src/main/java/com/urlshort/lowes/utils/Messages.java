package com.urlshort.lowes.utils;

public interface Messages {
	String FAILED = "FAILED";
	String SUCCESS = "SUCCESS";
	String NOT_FOUND = "Not Found";
	String ORIGINAL_URL_NOT_EMPTY = "Original URL shouldn't be empty";
	String ORIGINAL_URL_INVALID = "Invalid Original URL";
	String REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";
}
