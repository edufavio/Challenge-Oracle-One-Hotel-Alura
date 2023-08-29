package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionFactory {

	private DataSource dataSource;
	
	public ConnectionFactory() {
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_pe?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("Pa22W0rd*457");

        this.dataSource = pooledDataSource;
    }
	
	public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (Exception e) {
        	System.out.println("Error ocurrido: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
	
	public static <T> Integer addNewStatement(PreparedStatement statement, T object) throws SQLException {
		var estado = statement.executeUpdate();
		
		if (estado > 0) {
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				
				System.out.println("Id generado: " + id);
				System.out.println("Guardado con exito: " + object.toString());
				
				return id;
			}
			
			return -1;
		} else {
			System.out.println("No se ha guardado: " + object.toString());
		}
		
		return null;
	}
}
