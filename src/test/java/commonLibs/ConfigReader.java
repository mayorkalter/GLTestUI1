package commonLibs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public String getProp(String nameFileProperties, String nameProp) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/test/resources/" + nameFileProperties + ".properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("File not exist");
            e.printStackTrace();
        }
        return properties.getProperty(nameProp);
    }

    public String photo(String name){
        ClassLoader classLoader=getClass().getClassLoader();
        File file=new File(classLoader.getResource(name).getFile());
        String path=file.getAbsolutePath();
        return path;
    }
}
