/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author msi
 */

import java.util.*;

public class Utilisateur {
	private static int count=0;
	private int id;
	private String username;
	private String password;
	
	
	
	public Utilisateur(int id,String username, String password) {
		
		this.id = id;
		this.username = username;
                this.password = password;
		
	}

	public Utilisateur(String username, String password) {
		
		this.id = ++count;
		this.username = username;
                this.password = password;
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Reservation [id=" + id + ", username=" + username + ", password=" + password
				+ ", ]";
	}


	public static int getCount() {
		return count;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	
	
	
	
	
	
	
	

}
