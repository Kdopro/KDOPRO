package Old;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    public Downloader() {
    }

    public static void main(String[] args) throws IOException {
        String url = "https://cbr.ru/s/newbik";
       // String url = "https://filestore1.comita.ru/reference_kfm/fil.zip";
        String fileName = "C:\\newbik\\20221016ED01OSBR.zip";


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
/*
        JarInputStream zis = null;
        ZipEntry ze;
        while((ze = zis.getNextEntry()) != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;

            String filename = ze.getName();
            File path = null;
            String zipname = null;
            if(ze.isDirectory()) {
                File directPath = new File(path + zipname.substring(0,zipname.length()-4) + "C:\\newbik\\" + filename);
                directPath.mkdirs();
            } else {
                File directPath = new File(path + zipname.substring(0,zipname.length()-4) + "C:\\newbik\\");
                directPath.mkdir();
                FileOutputStream fout1 = new FileOutputStream(path + zipname.substring(0,zipname.length()-4) + "C:\\newbik\\" + filename);

                // reading and writing
                while((count = zis.read(buffer)) != -1)
                {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout1.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }
        }
*/
        }
}




