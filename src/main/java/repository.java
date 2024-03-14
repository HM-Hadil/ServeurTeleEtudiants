import Execptions.EtudiantExisteException;
import Execptions.EtudiantIntrouvableException;

import java.util.Vector;

public interface repository {
    //methode pour inserer un etudiant
    public void ajouterEtudiant(Etudiant etudiant) throws EtudiantExisteException;

    //methode retrouver un etudiant par son nom et prenom
    public String getEtudiant(String nom, String prenom) throws EtudiantIntrouvableException;

    //methode pour  Lister l’ensemble des étudiants
    public Vector<Etudiant> getAllEtudiant();

    //methode pour Lister l’ensemble de services rendus par le serveur
    public String getServices();
}
