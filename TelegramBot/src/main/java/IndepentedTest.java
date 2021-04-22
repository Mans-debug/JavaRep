import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import java.util.stream.Stream;

public class IndepentedTest
{
    public static void downloadFileFromURL(String urlString, File destination) {
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("./source.txt");
        URL url;
        try
        {
            url = new URL("https://knigolub.net/uploads/book/Batluk_Akademiya-vlasti_2_Studentka-v-nakazanie.txt");
//            FileUtils.copyURLToFile(url, file);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        downloadFileFromURL("https://knigolub.net/uploads/book/Batluk_Akademiya-vlasti_2_Studentka-v-nakazanie.txt", file);
        WordGenerator wordGenerator = new WordGenerator(file);
       String str =wordGenerator.createSentence(100);
        System.out.println(str);

    }
}
