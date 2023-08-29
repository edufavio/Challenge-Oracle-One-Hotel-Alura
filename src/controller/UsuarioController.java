package controller;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import models.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDao;
	
	public UsuarioController() {
		this.usuarioDao = new UsuarioDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public boolean iniciarSesion(Usuario usuario) {
		return usuarioDao.iniciarSesion(usuario);
	}

}
