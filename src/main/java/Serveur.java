import Execptions.EtudiantExisteException;
import Execptions.EtudiantIntrouvableException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Serveur {
    private static List<Client> clients = new ArrayList<>();
    private static Services services = new Services();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client conncté...");

                // Créer un nouveau gestionnaire de client pour gérer la connexion
                Client client = new Client(clientSocket);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Client extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        Client(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Envoyer les services disponibles au client
                writer.println("Nos services:");
                writer.println("1. Ajouter un etudiant");
                writer.println("2. Rechercher un etudiant");
                writer.println("3. Afficher la liste des etudiants");
                writer.println("4. Quitter");

                // Lire les requêtes du client
                String line;
                while ((line = reader.readLine()) != null) {
                    int option = Integer.parseInt(line);
                    switch (option) {
                        case 1:
                            writer.println("Entrez les informations de l'etudiant: nom, prenom, telephone, mail, URL, date de naissance (separes par des virgules)");
                            String[] studentInfo = reader.readLine().split(",");
                            try {
                                services.ajouterEtudiant(new Etudiant(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3], studentInfo[4], studentInfo[5]));
                                writer.println("Etudiant ajoute avec succes.");
                            } catch (EtudiantExisteException e) {
                                writer.println("Erreur: " + e.getMessage());
                            }
                            break;
                        case 2:
                            writer.println("Entrez le nom et le prenom de l'etudiant a rechercher (separes par un espace)");
                            String[] searchInfo = reader.readLine().split(" ");
                            try {
                                String etudiantInfo = services.getEtudiant(searchInfo[0], searchInfo[1]);
                                writer.println(etudiantInfo);
                            } catch (EtudiantIntrouvableException e) {
                                writer.println("Etudiant introuvable: " + e.getMessage());
                            }
                            break;
                        case 3:
                            Vector<Etudiant> etudiantsList = services.getAllEtudiant();
                            StringBuilder studentsInfo = new StringBuilder();
                            for (Etudiant etd : etudiantsList) {
                                studentsInfo.append(etd.toString()).append("\n");
                            }
                            writer.println(studentsInfo.toString());
                            break;
                        case 4:
                            writer.println("Déconnexion...");
                            clientSocket.close();
                            return;
                        default:
                            writer.println("Option invalide.");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
