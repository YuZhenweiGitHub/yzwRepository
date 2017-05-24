package com.company.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

/**
 * Created by yzw on 2017/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml", "classpath:spring/spring-servlet.xml" })
public class TestControllerTest {

    @Autowired
    private TestController testController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void returnString() throws Exception {
        //System.out.print(testController.returnString());

        Arrays.asList( "a", "b", "d" ).forEach( (String str ) -> System.out.println( str ) );
        
    }

}