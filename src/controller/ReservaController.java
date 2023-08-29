package controller;

import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import models.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDao;
	
	public ReservaController() {
		this.reservaDao = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public Reserva guardar(Reserva reserva) {
		return this.reservaDao.guardar(reserva);
	}
	
	public List<Reserva> listar() {
		return this.reservaDao.listar();
	}
	
	public List<Reserva> listar(int id) {
		return this.reservaDao.listar(id);
	}
	
	public String actualizar(Reserva reserva) {
		return this.reservaDao.actualizar(reserva);
	}
	
	public String eliminar(int id) {
		return this.reservaDao.eliminar(id);
	}

}
