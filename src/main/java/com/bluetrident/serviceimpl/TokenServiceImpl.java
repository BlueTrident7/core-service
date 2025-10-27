package com.bluetrident.serviceimpl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.config.exception.BadRequestException;
import com.bluetrident.config.exception.InvalidAccessException;
import com.bluetrident.entity.TokenPayload;
import com.bluetrident.service.TokenService;
import com.bluetrident.util.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TokenServiceImpl implements TokenService {

	@Override
	public TokenPayload getTokenPayload(HttpHeaders httpHeaders) throws ApplicationException {
		String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

	    if (AppUtil.isNullString(token)) {
	        throw new BadRequestException("authorization token cannot be null/blank!!");
	    }

	    // Remove "Bearer " prefix if present
	    if (token.startsWith("Bearer ")) {
	        token = token.substring(7);
	    }

	    try {
	        // Split token into header, payload, signature
	        String[] parts = token.split("\\.");

	        if (parts.length < 2) {
	            throw new BadRequestException("Invalid JWT token format!");
	        }

	        // Decode payload (2nd part)
	        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);

	        // Convert to TokenPayload object
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(payloadJson, TokenPayload.class);

	    } catch (Exception e) {
	        throw new InvalidAccessException("Error decoding JWT token: " + e.getMessage());
	    }
	}
}
