import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {


            Socket s = new Socket("localhost", 12345);
            InputStream is= s.getInputStream();
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader bfr= new BufferedReader(isr);
            //pour afficher les 4 services ligne par ligne
            System.out.println(bfr.readLine() + "\n" +bfr.readLine() + "\n"+
                    bfr.readLine() + "\n"+ bfr.readLine());
            //fermeture
            bfr.close();
            s.close();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("client déconneté");
        }

    }

}