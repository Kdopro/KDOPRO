package Download_Job;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.*;

public class PostBankBikUp {
    // this is the entry point for what I want the instase of the class to do
    public static void main(String args[]) throws Exception {

        String url = args[0];
        final String username = args[1];
        final String password1 = args[2];
        String directory = args[3];

        checkArguments(args);

        PostBankBikUp max = new PostBankBikUp();
        max.process(url, username, password1, directory);

    }

    private static void checkArguments(String[] args) {

    }

    public void process(String url, String username1, String password1, String directory) throws Exception {

        final char[] password = password1.toCharArray();
        final String username = username1;
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication p = new PasswordAuthentication(username, password);
                return p;
            }
        });


        BufferedInputStream in = null;
        BufferedInputStream in2 = null;
        FileOutputStream fout = null;
        // proxy
        String proxyip = "http://fortigate02.bankexp.local";
        int proxyport = 8080;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyip, proxyport));
        // URL connection to file
        URL file = new URL(url);
        URLConnection connection = file.openConnection(proxy);
        ((HttpURLConnection) connection).getResponseCode();
        int reponsecode = ((HttpURLConnection) connection).getResponseCode();
        System.out.println("response code " + reponsecode);


        if (reponsecode == HttpURLConnection.HTTP_FORBIDDEN) {
            System.out.println("Invalid username or psw");
            return;
        }
        if (reponsecode != HttpURLConnection.HTTP_OK) {
            System.out.println("Unable to find response");
            return;
        }


        //Save the file into the chosen folder
        in = new BufferedInputStream(connection.getInputStream());

        //Create instance of DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //Get the DocumentBuilder
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        //Using existing XML Document
        Document doc = docBuilder.parse(in);

        //create the root element
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("li");


        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getTextContent().contains("/")) {


                //  System.out.println(url + childNode.getTextContent());
                process(url + childNode.getTextContent(), username, password1, directory);

            }

            if (childNode.getTextContent().contains(".") && !childNode.getTextContent().contains("..")) {


                String textcon = url + childNode.getTextContent();
                System.out.println("aaa " + textcon);

                if (url.endsWith("/")) {
                    System.out.println("ends with a /");
                }

                textcon = textcon.replace(" ", "%20");
                URL file2 = new URL(textcon);

                String[] urlparts = textcon.split("/");
                int urllength = urlparts.length;
                String lastarray = urlparts[urllength - 2];
                System.out.println("last array " + lastarray);


                URLConnection connection2 = file2.openConnection(proxy);
                in2 = new BufferedInputStream(connection2.getInputStream());
                String test2 = childNode.getTextContent();
                System.out.println("eeee " + childNode.getTextContent());

                String filename = (directory + test2);
                File f = new File(filename);
                if (f.isDirectory())
                    continue;


                //InputStream inputStream= new FileInputStream("InputStreamToFile.java");
                OutputStream out = new FileOutputStream(f);
                byte buf[] = new byte[1024];
                int len;
                while ((len = in2.read(buf)) > 0)
                    out.write(buf, 0, len);
                out.close();
                in2.close();


            }
        }
    }
}