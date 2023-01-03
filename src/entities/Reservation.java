package entities;


import java.util.*;
import java.util.Date;

public class Reservation {
	private static int count=0;
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private Chambre chambre;
	private Client client;
	
	
	public Reservation(int id,Date dateDebut, Date dateFin, Chambre chambre, Client client) {
		
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.chambre = chambre;
		this.client = client;
	}

	public Reservation(Date dateDebut, Date dateFin, Chambre chambre, Client client) {
		
		this.id = ++count;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.chambre = chambre;
		this.client = client;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date localDate) {
		this.dateDebut = localDate;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}



	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", chambre=" + chambre
				+ ", client=" + client + "]";
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		Reservation.count = count;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Chambre getChambre() {
		return chambre;
	}


	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
	
	

}
