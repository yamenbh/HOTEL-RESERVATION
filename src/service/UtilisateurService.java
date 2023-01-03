package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.Connect;
import dao.IDAO;
import entities.Utilisateur;

public class UtilisateurService implements IDAO<Utilisateur> {
	
	private List<Utilisateur> utilisateur;
	

	public UtilisateurService() {
		utilisateur = new ArrayList <Utilisateur>();
	}

	@Override
	public boolean create(Utilisateur o) {
		String req="insert into authentification values(null,?,?)";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getUsername());
			ps.setString(2, o.getPassword());
		
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
	public boolean update(Utilisateur o) {
		String req= "update authentification set username=?,password=? where id=?";
		
		
		PreparedStatement ps;
		try {
			ps = Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getUsername());
			ps.setString(2, o.getPassword());
			ps.setInt(4, o.getId());
			int E =ps.executeUpdate();
			if(E==1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
//		for(Client cli : client) {
//			if(cli.getId()== o.getId()) {
//				 	Scanner sc= new Scanner(System.in);
//				 	System.out.println("enter name : ");
//				 	cli.setNom(sc.nextLine());
//				 	System.out.println("enter prename : ");
//				 	cli.setPrenom(sc.nextLine());
//				 	System.out.println("enter Telephone : ");
//				 	cli.setTel(sc.nextLine());
//				 	System.out.println("enter EMAIL : ");
//				 	cli.setEmail(sc.nextLine());
//				 	sc.close();
//				return true;
//			}
//		}
//		return false;
	}

	@Override
	public boolean delete(Utilisateur o) {
		String req="delete from authentification where id=?";
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
	public Utilisateur findById(int id) {
		String req="select * from client where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet E =ps.executeQuery();
			if(E.next())
				return new Utilisateur(E.getInt(1),E.getString(2),E.getString(3));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
//		for(Client cli : client) {
//			
//			if(cli.getId()== id) {
//				return cli;
//			}
//		}
//		return null;
	}
	

	@Override
	public List<Utilisateur> findAll() {
		String req="select * from authentification";
		List<Utilisateur> C = new ArrayList<Utilisateur>();
		
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ResultSet E =ps.executeQuery();
			while(E.next())
				C.add(new Utilisateur(E.getInt(1),E.getString(2),E.getString(3)));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

}
