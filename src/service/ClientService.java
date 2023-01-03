package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.Connect;
import dao.IDAO;
import entities.Client;

public class ClientService implements IDAO<Client> {
	
	private List<Client> client;
	

	public ClientService() {
		client = new ArrayList <Client>();
	}

	@Override
	public boolean create(Client o) {
		String req="insert into client values(null,?,?,?,?)";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getTel());
			ps.setString(4, o.getEmail());
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
	public boolean update(Client o) {
		String req= "update client set nom=?,prenom=?,tel=?,email=? where id=?";
		
		
		PreparedStatement ps;
		try {
			ps = Connect.getCon().prepareStatement(req);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getTel());
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
	public boolean delete(Client o) {
		String req="delete from client where id=?";
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
	public Client findById(int id) {
		String req="select * from client where id=?";
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet E =ps.executeQuery();
			if(E.next())
				return new Client(E.getInt(1),E.getString(2),E.getString(3),E.getString(4),E.getString(5));

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
	public List<Client> findAll() {
		String req="select * from client";
		List<Client> C = new ArrayList<Client>();
		
		try {
			PreparedStatement ps= Connect.getCon().prepareStatement(req);
			ResultSet E =ps.executeQuery();
			while(E.next())
				C.add(new Client(E.getInt(1),E.getString(2),E.getString(3),E.getString(4),E.getString(5)));
			return C;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

}
