package com.bluetrident.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.entity.TokenPayload;

@Service
public interface TokenService {
	
	/**
	 * this is to get the payload fron jwt token
	 * 
	 * @param httpHeaders
	 * @return
	 */
	TokenPayload getTokenPayload(HttpHeaders httpHeaders) throws ApplicationException;

}
