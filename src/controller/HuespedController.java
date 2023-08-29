package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import models.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDao;

	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public void guardar(Huesped huesped) {
		this.huespedDao.guardar(huesped);
	}
	
	public List<Huesped> listar() {
		return this.huespedDao.listar();
	}
	
	public List<Huesped> listar(String apellido) {
		return this.huespedDao.listar(apellido);
	}
	
	public String actualizar(Huesped huesped) {
		return this.huespedDao.actualizar(huesped);
	}
	
	public String eliminar(int id) {
		return this.huespedDao.eliminar(id);
	}
}
