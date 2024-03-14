import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//. La partie cliente permet d’afficher un menu qui permet de lister les services du serveur. Ce menu
//doit faciliter l’utilisation de l’application.

public class Serveur {
    public static void main(String[] args){
        Services service = new Services();

        try{
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("en attend de connexion");

            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            //envoyer la liste des services rendu par le serveur
            pw.println(service.getServices());
            pw.flush();
            pw.close();
            s.close();

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("deconnect ");
        }
    }
}
