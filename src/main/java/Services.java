import Execptions.EtudiantExisteException;
import Execptions.EtudiantIntrouvableException;

import java.util.Vector;

public class Services implements repository{
    private Vector<Etudiant> etudiants ;

    public Services(){
        etudiants =new Vector<Etudiant>();
    }

    @Override
    public void ajouterEtudiant(Etudiant etudiant) throws EtudiantExisteException {
        if (etudiants.contains(etudiant)) {
            throw new EtudiantExisteException();
        }
        etudiants.add(etudiant);
    }

    @Override
    public String getEtudiant(String nom, String prenom) throws EtudiantIntrouvableException {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNom().equals(nom) && etudiant.getPrenom().equals(prenom)) {
                return etudiant.toString(); // Retourne les propriétés de l'étudiant
            }
        }
        throw new EtudiantIntrouvableException();
    }


    @Override
    public Vector<Etudiant> getAllEtudiant() {
        return etudiants;
    }

    @Override
    public String getServices() {
        String insert = "ajouter un étudiant";
        String getInfoEtudiant = "afficher les informations d'un étudiant";
        String getAllEtudiant = "afficher la liste des étudiants";
        String exit = "sortir";
        return insert + "\n" + getInfoEtudiant + "\n" + getAllEtudiant + "\n" + exit;
    }
}
