package br.com.kbmg.wsconsultamedica.utils;

import java.security.InvalidParameterException;

public class Validator {

	public static boolean verifyIsNullOrEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	public static Long stringParseLong(String param, String msgErro) {
		try {
			return Long.parseLong(param);
		} catch (Exception e) {
			throw new InvalidParameterException(msgErro);
		}
	}
}
