package models;

public class Usuario {
	
	private Integer id;
	private String usuario;
	private String clave;
	
	public Usuario(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return String.format("Id: %d, Usuario: %s", id, usuario);
	}

}
