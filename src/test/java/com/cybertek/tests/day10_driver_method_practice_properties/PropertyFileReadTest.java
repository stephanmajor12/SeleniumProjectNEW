package com.cybertek.tests.day10_driver_method_practice_properties;

import com.cybertek.utility.ConfigReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReadTest {

    @Test
    public void testInitialRead() throws IOException {

        // Open a connection to the file using FileInputStream object
        FileInputStream in = new FileInputStream("config.properties") ;

        // Create empty Properties object
        Properties myProperty = new Properties() ;
        // Load the FileInputStream into the Properties Object
        myProperty.load(in);
        // close the connection by calling close method of FileInputStream object
        in.close();

        // now actually read from the properties file using it's key
        String helloValue =  myProperty.getProperty("hello");
        System.out.println("helloValue = " + helloValue);

        // read the value of key called "message"
        System.out.println("myProperty.getProperty(\"message\") = "
                + myProperty.getProperty("message"));

        System.out.println("myProperty.getProperty(\"weborder_url\") = "
                + myProperty.getProperty("weborder_url"));


    }


    @Test
    public void testReadWithTryCatch(){


        try {
            // this throw FileNotFoundException
            FileInputStream in = new FileInputStream("config.properties") ;

            Properties properties = new Properties();
            properties.load(in);
            // this throw IOException , it's parent of FileNotFoundException
            in.close();
            System.out.println("properties.getProperty(\"hello\") = "
                    + properties.getProperty("hello"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testReadingUsingUtilityClass(){

        System.out.println("ConfigReader.read(\"hello\") = "
                + ConfigReader.read("hello"));
        System.out.println("ConfigReader.read(\"weborder_username\") = "
                + ConfigReader.read("weborder_username"));


    }


}
