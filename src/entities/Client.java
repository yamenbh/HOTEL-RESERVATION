package entities;

public class Client {
	
	private static int count=0;
	private int id;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	
	public Client(String nom, String prenom, String tel, String email) {
		this.id = ++count;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
	}
	public Client(int id,String nom, String prenom, String tel, String email) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Client.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return  nom + " " + prenom ;
	}
	
	
	
	
	

}
