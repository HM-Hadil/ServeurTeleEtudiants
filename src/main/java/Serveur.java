import Execptions.EtudiantExisteException;
import Execptions.EtudiantIntrouvableException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//. La partie cliente permet d’afficher un menu qui permet de lister les services du serveur. Ce menu
//doit faciliter l’utilisation de l’application.

public class Serveur {
    public static void main(String[] args){
        Services service = new Services();

        try {
            ServerSocket ss = new ServerSocket(12345);
            System.out.println("En attente de connexion...");

            while (true) {
                Socket s = ss.accept();
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os);

                //envoyer la liste des services rendu par le serveur
                pw.println(service.getServices());

                // Lire l'option choisie par le client
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                int option = Integer.parseInt(br.readLine());

                // Traitement en fonction de l'option choisie
                switch (option) {
                    case 1:
                        // Ajouter un étudiant
                        String nom = br.readLine();
                        System.out.println(nom);
                        String prenom = br.readLine();
                        String mail = br.readLine();
                        String telephone = br.readLine();
                        String url = br.readLine();
                        String date = br.readLine();
                        // Lecture d'informations de l'étudiant...
                        Etudiant etudiant = new Etudiant(nom, prenom,telephone,mail,url,date);
                        try {
                            service.ajouterEtudiant(etudiant);
                            pw.println("Etudiant ajouté avec succès.");
                        } catch (EtudiantExisteException e) {
                            pw.println("Erreur: " + e.getMessage());
                        }
                        break;
                    // Rechercher un étudiant
                    case 2:
                        String searchNom = br.readLine();
                        String searchPrenom = br.readLine();
                        try {
                            String etudiantInfo = service.getEtudiant(searchNom, searchPrenom);
                            pw.println(etudiantInfo);
                        } catch (EtudiantIntrouvableException e) {
                            pw.println("Étudiant introuvable: " + e.getMessage());
                        }
                        pw.flush();
                        break;

                    default:
                        pw.println("Option invalide.");
                        break;
                }
                pw.flush();
                pw.close();
                s.close();

        }

    }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Serveur déconnecté.");
        }
    }}
