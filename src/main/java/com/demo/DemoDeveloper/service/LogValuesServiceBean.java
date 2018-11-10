package com.demo.DemoDeveloper.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.DemoDeveloper.model.LogValues;
import com.demo.DemoDeveloper.repository.LogValuesRepository;

@Service
public class LogValuesServiceBean implements LogValuesService {

	private Logger logger = Logger.getLogger("MyLog");
	
	private final String NOMBRE_ARCHIVO="logFile.txt";
	
	@Autowired
	private LogValuesRepository logValuesRepository;
	
	@Override
	public List<LogValues> findAll() {

		List<LogValues> listLogValues = logValuesRepository.findAll();
		
		return listLogValues;
	}

	@Override
	public LogValues create(LogValues logvalues) {

		LogValues persistedLogvalues = logValuesRepository.save(logvalues);

		return persistedLogvalues;
	}

	@Override
	public String archivoTexto(String mensaje,int tipo) throws IOException {
		
		String resultado;
		
		try {
			File logFile = new File(NOMBRE_ARCHIVO);
	        if (!logFile.exists()) {
	        	logFile.createNewFile();
	        }
	        
	        FileHandler fh = new FileHandler(NOMBRE_ARCHIVO);
	        logger.addHandler(fh);
	        if(tipo==1)
				logger.log(Level.INFO, mensaje);
	        else if(tipo==2)
				logger.log(Level.WARNING, mensaje);
	        else
				logger.log(Level.SEVERE, mensaje);

	        resultado = "0";

		}catch(Exception e) {
			resultado="Hubo un error";
			return resultado;
		}
		
		return resultado;
	}

	@Override
	public String consola(String mensaje,int tipo) throws IOException {
		String resultado;
		
		try {
			ConsoleHandler ch = new ConsoleHandler();
			logger.addHandler(ch);
			if(tipo==1)
				logger.log(Level.INFO, mensaje);
			else if(tipo==2)
				logger.log(Level.WARNING, mensaje);
			else
				logger.log(Level.SEVERE, mensaje);
			
			resultado="0";
		}catch(Exception e) {
			resultado="Hubo un error";
			return resultado;
		}
		
		return resultado;
	}

}
