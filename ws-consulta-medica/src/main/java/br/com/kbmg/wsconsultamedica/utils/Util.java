package br.com.kbmg.wsconsultamedica.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kbmg.wsconsultamedica.response.ErrorResponse;
import br.com.kbmg.wsconsultamedica.response.ObjectResponse;

public class Util {
	private static ModelMapper me = new ModelMapper();

	public static void printObjectConsole(Object obj) {
		try {
			ObjectMapper m = new ObjectMapper();
			System.out.println("--------------- Dados atuais de " + obj.getClass() + " ---------------");
			System.err.println(m.writeValueAsString(obj));
		} catch (Exception e) {
			System.err.println("Não foi possível mostrar objeto. Erro: " + e.getMessage());
		}
	}

	public static Object convertObject(Object obj, Class<?> type) {
		return me.map(obj, type);
	}

	public static void map(Object base, Object destination) {
		me.map(base, destination);
	}

	public static Object convertObjectAndPrintConsole(Object obj, Class<?> type) {
		Object map = me.map(obj, type);
		printObjectConsole(map);
		return map;
	}

	public static List<?> convertList(List<?> list, Class<?> typeConvert) {
		return list.stream().map(e -> Util.convertObject(e, typeConvert)).collect(Collectors.toList());
	}

	public static ResponseEntity<ObjectResponse> responseBad(BindingResult result) {
		ObjectResponse response = new ObjectResponse();

		if (result.hasErrors()) {
			response.setErrorDescription(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Dados inválidos."));

			result.getAllErrors().forEach(error -> response.getErrorDescription().getErrors()
					.add(error.getObjectName() + ": " + error.getDefaultMessage()));

		}
		
		return ResponseEntity.badRequest().body(response);
	}

	public static ResponseEntity<ObjectResponse> createResponseOk(Object obj) {
		return ResponseEntity.ok().body(new ObjectResponse(obj));
	}

}
