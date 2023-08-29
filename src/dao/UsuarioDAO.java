package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;

public class UsuarioDAO {
	
	private final Connection con;
	private String sqlSelect = "SELECT usuario, clave FROM usuarios WHERE (usuario = ?) AND (clave = ?)";
	private boolean isLogin = false;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public boolean iniciarSesion(Usuario usuario) {
		try {
			var statement = con.prepareStatement(sqlSelect);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getClave());
			
			final ResultSet resultSet = statement.executeQuery();
			
			try (resultSet) {
				while (resultSet.next()) {
					String usuarioDB = resultSet.getString("usuario");
					String claveDB = resultSet.getString("clave");
					
					if (usuarioDB.equals(usuario.getUsuario()) && claveDB.equals(usuario.getClave())) {
						System.out.println("Usuario encontrado: " + usuarioDB + "\n" + claveDB);
						isLogin = true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return isLogin;
	}
}
