package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Huesped;

public class HuespedDAO {
	
	private Connection con;
	private final String sqlInsert = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
	private final String sqlSelectAll = "SELECT * FROM huespedes";
	private final String sqlSelectOne = "SELECT * FROM huespedes WHERE apellido = ?";
	private final String sqlUpdate = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ? WHERE id = ?";
	private final String sqlDelete = "DELETE FROM huespedes WHERE id = ?";
	
	private List<Huesped> huespedes = new ArrayList<>();
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setInt(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());
				huesped.setId(ConnectionFactory.addNewStatement(statement, huesped));
			}
		} catch (SQLException e) {
			System.out.println("Error al guardar el huesped: " + e.getMessage());
		}
	}
	
	public List<Huesped> listar() {
		try {
			huespedes = new ArrayList<>();
			final PreparedStatement statement = con.prepareStatement(sqlSelectAll);
			
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				
				try (resultSet) {
					while (resultSet.next()) {
						final Integer id = resultSet.getInt(1);
						final String nombre = resultSet.getString(2);
						final String apellido = resultSet.getString(3);
						final Date fechaNacimiento = resultSet.getDate(4);
						final String nacionalidad = resultSet.getString(5);
						final Integer telefono = resultSet.getInt(6);
						final Integer idReserva = resultSet.getInt(7);
						
						Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						huespedes.add(huesped);
					}
					
					return huespedes;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al listar los huespedes: " + e.getMessage());
			return null;
		}
	}
	
	public List<Huesped> listar(String apellido) {
		try {
			huespedes = new ArrayList<>();
			final PreparedStatement statement = con.prepareStatement(sqlSelectOne);
			
			try (statement) {
				statement.setString(1, apellido);
				ResultSet resultSet = statement.executeQuery();
				
				try (resultSet) {
					while (resultSet.next()) {
						Huesped huesped = new Huesped(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_nacimiento"),
								resultSet.getString("nacionalidad"),
								resultSet.getInt("telefono"),
								resultSet.getInt("id_reserva")
								);
						huespedes.add(huesped);
					}
					
					return huespedes;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al listar al huesped: " + e.getMessage());
		}
		return null;
	}
	
	public String actualizar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlUpdate);
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, huesped.getFechaNacimiento());
			statement.setString(4, huesped.getNacionalidad());
			statement.setInt(5, huesped.getTelefono());
			statement.setInt(6, huesped.getId());
			
			try (statement) {
				final Integer modificado = statement.executeUpdate();
				
				if (modificado > 0) {
					System.out.println("Datos del huesped modificados: " + huesped);
					return "Se actualizaron los datos del huesped";
				}
			}
		} catch (Exception e) {
			System.out.println("Error al actualizar el huesped: " + e.getMessage());
		}
		
		return "No se actualizo";
	}
	
	public String eliminar(int id) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlDelete);
			statement.setInt(1, id);
			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 1) {
				return "Huesped eliminado";
			} else {
				return "No se encontro el huesped con el id " + id;
			}
		} catch (SQLException e) {
			return "Error al eliminar la reserva: " + e.getMessage();
		}
	}

}
