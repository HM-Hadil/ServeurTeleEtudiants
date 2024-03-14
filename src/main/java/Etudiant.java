import java.util.Date;
import java.util.Objects;

public class Etudiant {
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private String url;
    private String date_naissance;

    public Etudiant(String nom, String prenom, String telephone, String mail, String url, String date_naissance) {

        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.url = url;
        this.date_naissance = date_naissance;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getMail() {
        return mail;
    }
    public String getUrl() {
        return url;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    //insertion redondante d’un étudiant dont le nom et le prénom existent déjà surl’annuaire
    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;
        Etudiant etudiant = (Etudiant) ob;
        return nom.equals(etudiant.nom) &&
                prenom.equals(etudiant.prenom);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }
    @Override
    public String toString() {
        return "Nom: " + nom +
                ", Prénom: " + prenom +
                ", Téléphone: " + telephone +
                ", Mail: " + mail +
                ", URL: " + url +
                ", Date de naissance: " + date_naissance;
    }
}
