package service;

import entities.Chambre;
import entities.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;
import dao.IDAO;


public class ChambreService implements IDAO<Chambre> {
	
	private List<Chambre> chambres;
	
	

	public ChambreService() {
		chambres = new ArrayList<Chambre>();
		
	}

	@Override
	public boolean create(Chambre o) {
		String req="insert into chambre values(null,?,?)";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getTel());
			ps.setInt(2, o.getCat().getId());
			
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
	public boolean update(Chambre o) {
				
		
		String req= "update chambre set tel=?,id_categorie=? where id=?";
		
		
		PreparedStatement ps;
		try {
			ps = Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getTel());
			ps.setInt(2, o.getCat().getId());
			ps.setInt(3, o.getId());
			
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
	public boolean delete(Chambre o) {
		
		String req="delete from chambre where id=?";
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
	public Chambre findById(int id) {
		
		String req="select * from chambre where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet E =ps.executeQuery();
			CategorieService cs = new CategorieService();
			if(E.next())
				return new Chambre(E.getInt(1),E.getString(2),cs.findById(E.getInt(3)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Chambre> findAll() {
		String req="select * from chambre";
		List<Chambre> C = new ArrayList<Chambre>();
		
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ResultSet E =ps.executeQuery();
			CategorieService cs = new CategorieService();
			while(E.next())
				C.add(new Chambre(E.getInt(1),E.getString(2),cs.findById(E.getInt(3))));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Chambre> findChambreByCategorie(Categorie c){
		String req="select * from chambre where id_categorie= ?";
		List<Chambre> C = new ArrayList<Chambre>();
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, c.getId());
			ResultSet E =ps.executeQuery();
			CategorieService cs = new CategorieService();
			while(E.next())
				C.add(new Chambre(E.getInt(1),E.getString(2),cs.findById(E.getInt(3))));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	

}
