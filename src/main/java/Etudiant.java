import java.util.Date;

public class Etudiant {
    private String nom;
    private String prenom;
    private Long telephone;
    private String mail;
    private String url;
    private Date date_naissance;

    public Etudiant(String nom, String prenom, Long telephone, String mail, String url, Date date_naissance) {

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
    public Long getTelephone() {
        return telephone;
    }
    public String getMail() {
        return mail;
    }
    public String getUrl() {
        return url;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    // exceptions pour gérer une
    //insertion redondante d’un étudiant dont le nom et le prénom existent déjà surl’annuaire

    public boolean EtudiantExiste(Object ob){
        Etudiant etudiant=(Etudiant) ob;
        return nom.equals(etudiant.getNom()) && prenom.equals(etudiant.getPrenom());
    }
    public  String AfficheEtudiant(){
        return "nom: "+nom+" prenom: "+prenom+" telephone: "+telephone+" mail: "+mail+" url: "+url+
                "date de naissance: "+date_naissance;

    }
}
