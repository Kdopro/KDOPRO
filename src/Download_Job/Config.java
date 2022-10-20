package Download_Job;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static void main(String[] args) {

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src\\downloader\\config.xml");
            property.load(fis);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            System.out.println("HOST: " + host
                    + ", LOGIN: " + login
                    + ", PASSWORD: " + password);

        } catch (IOException e) {
            System.err.println("ВНИМАНИЕ: Конфигурационный файл отсуствует!");
            System.err.println("Приложение будет закрыто");
        }

    }

}