package Download_Job;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path Bik = downloadFile("https://cbr.ru/s/newbik", Paths.get("C:\\!My\\Code\\Java\\NewBik\\import_BIK\\"));

        for (String line : Files.readAllLines(Bik)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method

        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        int temp  = urlString.lastIndexOf("/");
        String fName = urlString.substring(temp+1, urlString.length() );
        Path path = Paths.get(downloadDirectory+"/" + fName);
        Path tempFile = Files.createTempFile("temp-",".tmp");
        //System.out.println(fName);
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        Files.move(tempFile, path, StandardCopyOption.REPLACE_EXISTING);
        return path;
    }
}