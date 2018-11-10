package com.demo.DemoDeveloper.web.api;

import com.demo.DemoDeveloper.model.LogValues;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogValuesControllerTest {

    LogValues logValues;

   @Autowired
   private LogValuesController controller;

    @Before
    public void setUp() throws Exception {


        logValues=new LogValues();
        logValues.setMensaje("Mensaje de prueba junit");
        logValues.setTipo(1);

    }

    @Test
    public void getAllIssues() {

        Assert.assertEquals(HttpStatus.OK,controller.getAllIssues().getStatusCode());

    }

    @Test
    public void createLogValues() {


        Assert.assertEquals(HttpStatus.CREATED,controller.createLogValues(logValues).getStatusCode());

    }

    @Test
    public void grabarArchivoTexto() {
        String mensaje="Mensaje de prueba Junit";
        int tipo=1;
        Assert.assertEquals("0",controller.grabarArchivoTexto(mensaje,tipo));

    }

    @Test
    public void consola() {
        String mensaje="Mensaje de prueba Junit";
        int tipo=1;
        Assert.assertEquals("0",controller.consola(mensaje,tipo));
    }
}