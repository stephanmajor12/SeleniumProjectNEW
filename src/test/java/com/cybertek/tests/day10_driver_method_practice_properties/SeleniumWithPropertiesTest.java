package com.cybertek.tests.day10_driver_method_practice_properties;

import com.cybertek.utility.ConfigReader;
import com.cybertek.utility.Driver;
import com.cybertek.utility.TestBase;
import com.cybertek.utility.WebOrderUtil;
import org.junit.jupiter.api.Test;

public class SeleniumWithPropertiesTest extends TestBase {


    @Test
    public void testWebOrderLogin(){

        // use ConfigReader everywhere
        String url = ConfigReader.read("weborder_url") ;
        String username = ConfigReader.read("weborder_username") ;
        String password = ConfigReader.read("weborder_password") ;

        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);


        // navigate by passing value you got from properties file
        Driver.getDriver().get(   url   );
        // if you want to directly use the returned value instead of saving it
        //Driver.getDriver().get( ConfigReader.read("url")   );
       // WebOrderUtil.openWebOrderApp();
        WebOrderUtil.login( username ,password  );

        System.out.println("END");


    }



}
