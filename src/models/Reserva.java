package models;

import java.sql.Date;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private long valor;
	private String formaPago;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, long valor, String formaPago) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, long valor, String formaPago) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public String toString() {
		return "Reserva [id =" + id + ", fecha_entrada = " + fechaEntrada + ", fecha_salida = " + fechaSalida + ", valor = "
				+ valor + ", forma_de_pago = " + formaPago + "]";
	}
	
	
}
