package com.demo.DemoDeveloper.service;

import java.io.IOException;
import java.util.List;

import com.demo.DemoDeveloper.model.LogValues;

public interface LogValuesService {
	
	List<LogValues> findAll();
	
	LogValues create(LogValues logvalues);
	
	String archivoTexto(String mensaje,int tipo) throws IOException;
	
	String consola(String mensaje,int tipo) throws IOException;
}
