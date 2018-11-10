package com.demo.DemoDeveloper.web.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.DemoDeveloper.model.LogValues;
import com.demo.DemoDeveloper.service.LogValuesService;


@RestController
public class LogValuesController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogValuesService logValuesService;
	
	@RequestMapping(value = "/logValues", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE 														
	)
	public ResponseEntity<List<LogValues>> getAllIssues() {
		logger.info("> getAllLogValues");
		List<LogValues> listLogValues = null;

		try {
			listLogValues = logValuesService.findAll();
			if (listLogValues == null) {
				listLogValues = new ArrayList<LogValues>();
			}

		} catch (Exception e) {
			logger.error("Ocurrio una exception.", e);
			return new ResponseEntity<List<LogValues>>(listLogValues,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getAllLogValues");
		return new ResponseEntity<List<LogValues>>(listLogValues, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/crearLogValues", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogValues> createLogValues(@RequestBody LogValues logValues) {
		logger.info("> createIssue");

		LogValues createdLogValues = null;

		try {
			LogValues log=new LogValues();
			log.setMensaje(logValues.getMensaje());
			log.setTipo(logValues.getTipo());
			createdLogValues = logValuesService.create(log);
		} catch (Exception ex) {
			logger.error("Unexpected exception caught.", ex);
			return new ResponseEntity<LogValues>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< createIssue");
		return new ResponseEntity<LogValues>(createdLogValues, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/grabarArchivoTexto/{mensaje}/{tipo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String grabarArchivoTexto(@PathVariable("mensaje") String mensaje,@PathVariable("tipo") int tipo) {
		
		String resultado="";
		
		try {
			resultado=logValuesService.archivoTexto(mensaje, tipo);
		} catch (IOException e) {
			e.printStackTrace();
			resultado="Ocurrió un error";
			return resultado;
		}
		
		return resultado;
	}
	
	@RequestMapping(value = "/consola/{mensaje}/{tipo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String consola(@PathVariable("mensaje") String mensaje,@PathVariable("tipo") int tipo) {
		
		String resultado="";
		
		try {
			resultado=logValuesService.consola(mensaje, tipo);
		} catch (IOException e) {
			e.printStackTrace();
			resultado="Ocurrió un error";
			return resultado;
		}
		
		return resultado;
	}
	
}
