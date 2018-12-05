package user;

public class Utilisateur {

	private Droit droit;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private float solde;
	
	public Utilisateur() {
		this.droit = Droit.visiteur;
	}
	
	public Utilisateur(Droit d, String nom, String prenom, String login, String mdp, float solde) {
		this.droit = d;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.setPassword(mdp);
		this.solde = solde;
	}
	
    ////////////// payement ////////////////////////
	
	public void ajouterSolde(int s) {
		this.solde += s;
	}
	
	public void payer(int s) {
		if(this.solde >= s) {
			this.solde -= s;
		}else {
			// exception payement a ajouter
		}
	}

	///////////////////getter & setter/////////////////////////
	
	public Droit getDroit() {
		return droit;
	}

	public void setDroit(Droit droit) {
		this.droit = droit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentification() {
		return login;
	}

	public void setIdentification(String identification) {
		this.login = identification;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
