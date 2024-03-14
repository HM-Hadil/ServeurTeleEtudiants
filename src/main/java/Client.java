import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Socket s = new Socket("localhost", 12345);
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);

            // Affichage du services rendu par le serveur
            String line;
            while ((line = bfr.readLine()) != null) {
                System.out.println(line);
            }

            // Demander à l'utilisateur de choisir une option
            System.out.print("Choisissez une option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            if (option == 1) {
                System.out.print("Entrez le nom de l'étudiant: ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le prénom de l'étudiant: ");
                String prenom = scanner.nextLine();
                System.out.print("Entrez mail de l'étudiant: ");
                String mail = scanner.nextLine();
                System.out.print("Entrez le telephone de l'étudiant: ");
                String telephone = scanner.nextLine();
                System.out.print("Entrez l'url de l'étudiant: ");
                String url = scanner.nextLine();
                System.out.print("Entrez la date de naissance de l'étudiant: ");
                String date = scanner.nextLine();

                // Envoi des informations au serveur pour l'ajout
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.println(nom);
                pw.println(prenom);
                pw.println(mail);
                pw.println(telephone);
                pw.println(url);
                pw.println(date);
                pw.flush();
            }
            if (option == 2) {
                System.out.print("Entrez le nom de l'étudiant: ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le prénom de l'étudiant: ");
                String prenom = scanner.nextLine();
                // Envoi des informations au serveur pour la recherche
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.println(nom);
                pw.println(prenom);
                pw.flush();
                // Lecture de la réponse du serveur
                String response = bfr.readLine();
                System.out.println(response);
            }


            // Fermeture des ressources
            bfr.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("client déconneté");
        }

    }

}