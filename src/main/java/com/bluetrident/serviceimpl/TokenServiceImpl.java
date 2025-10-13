package com.bluetrident.serviceimpl;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.config.exception.BadRequestException;
import com.bluetrident.entity.TokenPayload;
import com.bluetrident.service.TokenService;
import com.bluetrident.util.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TokenServiceImpl implements TokenService {

	@Override
	public TokenPayload getTokenPayload(HttpHeaders httpHeaders) throws ApplicationException {
		String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
		if (AppUtil.isNullString(token)) {
			throw new BadRequestException("authorization token cannot be null/blank!!");
		}
		String[] jwtToken = token.split(" ");
		String[] chunks = jwtToken[1].split("\\.");

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String payload = new String(decoder.decode(chunks[1]));

		TokenPayload tokenPayload = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			tokenPayload = objectMapper.readValue(payload, TokenPayload.class);
		} catch (JsonMappingException e) {
		} catch (JsonProcessingException e) {
		}

		return tokenPayload;
	
	}

}
