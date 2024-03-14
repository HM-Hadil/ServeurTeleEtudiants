import Execptions.EtudiantExisteException;
import Execptions.EtudiantIntrouvableException;

import java.util.Vector;

public class Service implements repository{
    private Vector<Etudiant> etudiants ;

    public Service(){
        etudiants =new Vector<Etudiant>();
    }
    @Override
    public void ajouterEtudiant(Etudiant etudiant) throws EtudiantExisteException {
        int index = etudiants.indexOf(etudiant);
        if (index!=-1){
            throw new EtudiantExisteException();
        }
        etudiants.add(etudiant);

    }

    @Override
    public String getEtudiant(String nom, String prenom) throws EtudiantIntrouvableException {
      int index =etudiants.indexOf(new Etudiant(nom,prenom,null,null,null,null));
        if (index ==-1){
            throw new EtudiantIntrouvableException();
        }
        return etudiants.get(index).toString();
    }

    @Override
    public Vector<Etudiant> getAllEtudiant() {
        return etudiants;
    }

    @Override
    public String getServices() {
       String insert = "ajouter un etudiant";
       String getInfoEtuddiant= "afficher les information d'un etudiant";
       String getAllEtudiant="afficher la liste des etudiants";
       String exit= "sortir";
       return insert+ "\n"+ getInfoEtuddiant+"\n"+getAllEtudiant+"\n"+exit;
    }
}
