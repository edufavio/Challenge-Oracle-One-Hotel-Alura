package test;

import java.sql.Connection;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		
		Connection con = new ConnectionFactory().recuperaConexion();
		
		System.out.println("Cerrando la conexion");
		
		con.close();
	}
}
