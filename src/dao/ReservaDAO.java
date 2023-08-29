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
import models.Reserva;

public class ReservaDAO {
	
	private Connection con;
	private final String sqlInsert = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago) VALUES (?, ?, ?, ?)";
	private final String sqlSelectAll = "SELECT * FROM reservas";
	private final String sqlSelectOne = "SELECT * FROM reservas WHERE id = ?";
	private final String sqlUpdate = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_de_pago = ? WHERE id = ?";
	private final String sqlDelete = "DELETE FROM reservas WHERE id = ?";
	
	private List<Reserva> reservas = new ArrayList<>();

	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public Reserva guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			try (statement) {
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setLong(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				reserva.setId(ConnectionFactory.addNewStatement(statement, reserva));
				
				return reserva;
			}
		} catch (Exception e) {
			System.out.println("Error al guardar la reserva: " + e.getMessage());
		}
		
		return null;
	}
	
	public List<Reserva> listar() {
		try {
			reservas = new ArrayList<>();
			final PreparedStatement statement = con.prepareStatement(sqlSelectAll);
			
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				
				try (resultSet) {
					while (resultSet.next()) {
						Integer id = resultSet.getInt(1);
						Date fechaEntrada = resultSet.getDate(2);
						Date fechaSalida = resultSet.getDate(3);
						
						if (fechaEntrada != null && fechaSalida != null) {
							Integer valor = resultSet.getInt(4);
							String formaPago = resultSet.getString(5);
							
							Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
							reservas.add(reserva);
						}
					}
					return reservas;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al listar las reservas: " + e.getMessage());
			return null;
		}
	}
	
	public List<Reserva> listar(Integer id) {
		try {
			reservas = new ArrayList<>();
			final PreparedStatement statement = con.prepareStatement(sqlSelectOne);
			
			try (statement) {
				statement.setInt(1, id);
				final ResultSet resultSet = statement.executeQuery();
				
				try (resultSet) {
					while (resultSet.next()) {
						Reserva reserva = new Reserva(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada"),
								resultSet.getDate("fecha_salida"),
								resultSet.getInt("valor"),
								resultSet.getString("forma_de_pago")
								);
						
						reservas.add(reserva);
					}
					return reservas;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al listar la reserva: " + e.getMessage());
		}
		return null;
	}
	
	public String actualizar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlUpdate);
			statement.setDate(1, reserva.getFechaEntrada());
			statement.setDate(2, reserva.getFechaSalida());
			statement.setLong(3, reserva.getValor());
			statement.setString(4, reserva.getFormaPago());
			statement.setInt(5, reserva.getId());
			
			try (statement) {
				final Integer modificados = statement.executeUpdate();
				
				if (modificados > 0) {
					System.out.println("Reserva actualizada: " + reserva);
					return "Actualizacion de reserva exitosa";
				}
			}
		} catch (Exception e) {
			System.out.println("Error al actualizar los datos de reserva " + e.getMessage());
		}
		return "No se pudo actualizar la reserva";
	}
	
	public String eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement(sqlDelete);
			statement.setInt(1, id);
			Integer modificados = statement.executeUpdate();
			
			if (modificados == 1) {
				return "Reserva eliminada";
			} else return "No se encontro reserva con el id " + id;
		} catch (SQLException e) {
			return "Error al eliminar la reserva: " + e.getMessage();
		}
	}

}
