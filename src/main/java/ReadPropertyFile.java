package main.java;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ReadPropertyFile {

    private static ConcurrentHashMap<String, String> mymap;

    /**
     * getListeParametres
     *
     * @return mymap
     * @throws IOException
     */
    public static synchronized ConcurrentHashMap<String, String> getListeParametres() throws IOException {

        if (null == mymap) {

            final InputStream inputStream = new ClassPathResource("config.properties")
                    .getInputStream();
            final Properties properties = new Properties();

            properties.load(inputStream);
            inputStream.close();

            mymap = new ConcurrentHashMap<String, String>();

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                mymap.put((String) entry.getKey(), (String) entry.getValue());
            }

        }
        return mymap;
    }
}
