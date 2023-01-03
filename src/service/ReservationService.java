package service;

import entities.Reservation;
import entities.Chambre;
import entities.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;
import connection.Connect;
import dao.IDAO;


public class ReservationService implements IDAO<Reservation> {
	
	private List<Reservation> reservation;

	public ReservationService() {
		reservation =new ArrayList<Reservation>();
		
	}

	@Override
	public boolean create(Reservation o) {
            if(o.getDateDebut().after(o.getDateFin())) {
			System.out.println("Date de debut depasse la date de fin");
			return false;
		}
            boolean chambreLibre = true;
            List<Reservation> reservations = this.findAll();
		for(Reservation r : reservations) {
			if(r.getChambre().getId() == o.getChambre().getId()) {
				if(!(o.getDateDebut().before(r.getDateDebut()) && o.getDateFin().before(r.getDateDebut()) 
						|| o.getDateDebut().after(r.getDateFin()) && o.getDateFin().after(r.getDateFin()))) {
					chambreLibre = false;
					break;
				}
			}
		}
                if(chambreLibre)
                {
                   
		String req="insert into reservation values(null,?,?,?,?)";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setDate(1, new java.sql.Date(o.getDateDebut().getTime()) );
			ps.setDate(2, new java.sql.Date(o.getDateFin().getTime()));
			ps.setInt(3, o.getClient().getId());
			ps.setInt(4, o.getChambre().getId());
			
			int E =ps.executeUpdate();
			if(E==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                 }
		return false;
	}
 
	@Override
	public boolean update(Reservation o) {
		if(o.getDateDebut().after(o.getDateFin())) {
			System.out.println("Erreur: date debut depasse date fin!");
			return false;
		}
                boolean chambreLibre = true;
		List<Reservation> reservations = this.findAll();
		for(Reservation r : reservations) {
                    if(r.getId() != o.getId()){
                        if(r.getChambre().getId() == o.getChambre().getId()) {
				if(!(o.getDateDebut().before(r.getDateDebut()) && o.getDateFin().before(r.getDateDebut()) 
						|| o.getDateDebut().after(r.getDateFin()) && o.getDateFin().after(r.getDateFin()))) {
					chambreLibre = false;
					break;
				}
			}
                }
		}
                if(chambreLibre)
                {
                    
		String req= "update reservation set date_debut=?,date_fin=?,id_chambre=?, id_client=? where id=?";
		
		
		PreparedStatement ps;
		try {
			ps = Connect.getCon().prepareStatement(req);
			ps.setDate(1, new java.sql.Date(o.getDateDebut().getTime()) );
			ps.setDate(2, new java.sql.Date(o.getDateFin().getTime()));
			ps.setInt(3, o.getChambre().getId());
			ps.setInt(4, o.getClient().getId());
			ps.setInt(5, o.getId());
			
			int E =ps.executeUpdate();
			if(E==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                }
		return false;
	}

	@Override
	public boolean delete(Reservation o) {
		String req="delete from reservation where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, o.getId());
			int E =ps.executeUpdate();
			if(E==1)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return false;
	}

	@Override
	public Reservation findById(int id) {
		// TODO Auto-generated method stub
		String req="select * from reservation where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet E =ps.executeQuery();
			CategorieService cs = new CategorieService();
			ChambreService chambreS = new ChambreService();
			ClientService clientS  = new ClientService();
			if(E.next())
			{
				System.out.println(E.getInt(4));
			Chambre ch = chambreS.findById(E.getInt(5));
			Client cli = clientS.findById(E.getInt(4));
			
				return new Reservation(E.getInt(1),E.getDate(2),E.getDate(3),ch,cli);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		String req="select * from Reservation";
		List<Reservation> C = new ArrayList<Reservation>();
		
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ResultSet E =ps.executeQuery();
			ChambreService chambreService = new ChambreService();
                        ClientService clientService = new ClientService();
			while(E.next())
                            
				C.add(new Reservation(E.getInt(1),new java.util.Date(E.getDate(2).getTime()),new java.util.Date(E.getDate(3).getTime()),chambreService.findById(E.getInt(5)),clientService.findById(E.getInt(4))));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Chambre> findChambreBetweenDates(Client client, java.util.Date dateDebut, java.util.Date dateFin) {
        String req;
        req = "select c.id,c.tel,c.id_categorie from chambre c,reservation "
                + "where reservation.id_chambre = c.id and reservation.id_client = ? and reservation.date_debut>=? and reservation.date_fin<=?";
        List<Chambre> cham = new ArrayList<Chambre>();
        try {
            CategorieService cs = new CategorieService();
            PreparedStatement stmt = Connect.getCon().prepareStatement(req);
            stmt.setInt(1, client.getId());
            stmt.setDate(2, new Date(dateDebut.getTime()));
            stmt.setDate(3, new Date(dateFin.getTime()));
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                cham.add(new Chambre(res.getInt(1), res.getString(2), cs.findById(res.getInt(3))));
            }
            return cham;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	
	

}
