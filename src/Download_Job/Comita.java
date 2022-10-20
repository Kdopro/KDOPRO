package Download_Job;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class Comita {


        public static void main(String[] args) throws FileNotFoundException {


            String url = "https://filestore1.comita.ru/reference_kfm/fil.zip";
            String fileName = "C:\\!My\\Code\\Java\\NewBik\\fil.zip";




            FileOutputStream fout = null;
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new URL(url).openStream());
                fout = new FileOutputStream(fileName);
                byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                    fout.flush();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            final Logger Logger;
        }





}
