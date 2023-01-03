package service;

import entities.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.Connect;
import dao.IDAO;


public class CategorieService implements IDAO<Categorie>{
	
	private List<Categorie> categorie;

	
	public CategorieService() {
		categorie = new ArrayList<Categorie>();
	}


	@Override
	public boolean create(Categorie o) {
		String req="insert into categorie values(null,?,?)";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getCode());
			ps.setString(2, o.getLibelle());
			
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
	public boolean update(Categorie o) {

		
		String req= "update categorie set code=?,libelle=? where id=?";
		
		
		PreparedStatement ps;
		try {
			ps = Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getCode());
			ps.setString(2, o.getLibelle());
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
	public boolean delete(Categorie o) {
		String req="delete from categorie where id=?";
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
	public Categorie findById(int id) {

		String req="select * from categorie where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet E =ps.executeQuery();
			if(E.next())
				return new Categorie(E.getInt(1),E.getString(2),E.getString(3));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Categorie> findAll() {
		String req="select * from categorie";
		List<Categorie> C = new ArrayList<Categorie>();
		
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ResultSet E =ps.executeQuery();
			while(E.next())
				C.add(new Categorie(E.getInt(1),E.getString(2),E.getString(3)));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
        
	
	
	
	

}
