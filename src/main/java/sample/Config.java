package sample;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    InputStream inputStream = null;

    public void getConfig(SQLConnect sql) throws IOException {
        try{

            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getResourceAsStream(propFileName);
            if( inputStream != null )
                prop.load(inputStream);
            else
                throw new FileNotFoundException("Property file " + propFileName + " not found in the classpath!");

            sql.setServerName(prop.getProperty("server"));
            sql.setPortNumber(prop.getProperty("port"));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
    }
}
