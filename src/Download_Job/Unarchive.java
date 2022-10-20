package Download_Job;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class  Unarchive {

    public static void main(String[] args) {

        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\!My\\Code\\Java\\NewBik\\20221020ED01OSBR.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t", name);

                // распаковка
                FileOutputStream fout = new FileOutputStream("C:\\!My\\Code\\Java\\NewBik\\import_BIK\\" + name);
                for (int a = zin.read(); a != -1; a = zin.read()) {
                    fout.write(a);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        ////////==============/////////

        try(ZipInputStream zim = new ZipInputStream(new FileInputStream("C:\\!My\\Code\\Java\\NewBik\\fil.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zim.getNextEntry())!=null){

                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t", name);

                // распаковка
                FileOutputStream fout = new FileOutputStream("C:\\!My\\Code\\Java\\NewBik\\import_BIK\\" + name);
                for (int b = zim.read(); b != 0; b = zim.read()) {
                    fout.write(b);
                }
                fout.flush();
                zim.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }



    }
}
