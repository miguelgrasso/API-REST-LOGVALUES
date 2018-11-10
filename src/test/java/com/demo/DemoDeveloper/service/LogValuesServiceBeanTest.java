package com.demo.DemoDeveloper.service;

import com.demo.DemoDeveloper.model.LogValues;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LogValuesServiceBeanTest {

    LogValues logValues;

    @Autowired
    private LogValuesServiceBean service;

    @Before
    public void setUp() throws Exception {
        logValues=new LogValues();
        logValues.setMensaje("Mensaje de prueba junit");
        logValues.setTipo(1);

    }

    @Test
    public void findAll() {
        Assert.assertNotEquals(null,service.findAll());
    }

    @Test
    public void create() {
        Assert.assertNotEquals(null,service.create(logValues).getId());
    }

    @Test
    public void archivoTexto() throws IOException {
        String mensaje="Mensaje de prueba Junit";
        int tipo=1;
        Assert.assertEquals("0",service.archivoTexto(mensaje,tipo));
    }

    @Test
    public void consola() throws IOException {
        String mensaje="Mensaje de prueba Junit";
        int tipo=1;
        Assert.assertEquals("0",service.consola(mensaje,tipo));
    }
}