package fr.dm.sqllocal;

/*
 * JavaBean Ville
 */
public class Ville {

    // --- Attributs
    private int idVille;
    private String cp;
    private String nomVille;
    private String idPays;

    // --- Methodes

    // --- Constructeurs
    public Ville() {
        super();
    }

    public Ville(String cp, String nomVille, String idPays) {
        super();
        this.cp = cp;
        this.nomVille = nomVille;
        this.idPays = idPays;
    }

    public Ville(int idVille, String cp, String nomVille, String idPays) {
        super();
        this.idVille = idVille;
        this.cp = cp;
        this.nomVille = nomVille;
        this.idPays = idPays;
    }

    // --- Accesseurs et modificateurs
    public int getIdVille(int i) {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    @Override
    public String toString() {
        return idVille + "-" + cp + "-" + nomVille + "-" + idPays;
    }

} // / class Ville
