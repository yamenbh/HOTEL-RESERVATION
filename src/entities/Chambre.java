package entities;

public class Chambre {
	private static int count=0;
	private int id;
	private String tel;
	private Categorie cat;
	
	
	public Chambre(int id,String tel, Categorie cat) {
		this.id = id;
		this.tel = tel;
		this.cat=cat;
	}
	public Chambre(String tel, Categorie cat) {
		this.id = ++count;
		this.tel = tel;
		this.cat=cat;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Chambre.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return getCat().toString();
	}
	
	
	
	

}
