package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controller.HuespedController;
import controller.ReservaController;
import factory.ValorReserva;
import models.Huesped;
import models.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private boolean isHuesped = false;
	private boolean isReserva = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		cargarTablas();
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usuarioInput = txtBuscar.getText();
				
				if (usuarioInput.matches("[a-zA-Z]+")) {
					// Busqueda por apellido
					var huespedes = huespedController.listar(usuarioInput);
					JOptionPane.showMessageDialog(null, String.format("Se han encontrado %d huesped(es)", huespedes.size()));
					
					if (huespedes != null) {
						limpiarTablaHuesped();
						rellenarTablaHuespedes(huespedes);
					}
				} else if (usuarioInput.matches("\\d+")) {
					// Busqueda por id de reserva
					JOptionPane.showMessageDialog(null, "Buscando por: " + txtBuscar.getText());
					var reservas = reservaController.listar(Integer.parseInt(txtBuscar.getText()));
					JOptionPane.showMessageDialog(null, String.format("Se han encontrado %d reserva(s)", reservas.size()));
					
					if (reservas != null) {
						limpiarTablaReserva();
						rellenarTablaReservas(reservas);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes ingresar solo números o letras");
					limpiarTablaReserva();
					limpiarTablaHuesped();
					cargarTablas();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isHuespedSeleccionado()) {
					modificarHuesped();
					limpiarTablas();
					cargarTablas();
				} else if (isReservaSeleccionada()) {
					modificarReserva();
					limpiarTablas();
					cargarTablas();
				}
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isHuespedSeleccionado()) {
					eliminarHuesped();
					limpiarTablas();
					cargarTablas();
				} else if (isReservaSeleccionada()) {
					eliminarReserva();
					limpiarTablas();
					cargarTablas();
				}
			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		panel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = panel.getSelectedIndex();
                isReserva = selectedIndex == 0;
                isHuesped = selectedIndex == 1;
			}
		});
	}
	
	public boolean isReservaSeleccionada() {
		return isReserva;
	}
	
	public boolean isHuespedSeleccionado() {
		return isHuesped;
	}
	
	private boolean tieneFilaElegida(JTable table) {
		return table.getSelectedRowCount() == 0 || table.getSelectedColumnCount() == 0;
	}
	
	private void eliminarHuesped() {
		if (tieneFilaElegida(tbHuespedes)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un huesped");
            return;
		}
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn())).ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			
			JOptionPane.showMessageDialog(this, huespedController.eliminar(id));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor elegir un huesped"));
	}
	
	private void eliminarReserva() {
		if (tieneFilaElegida(tbReservas)) {
			JOptionPane.showMessageDialog(this, "Por favor elegir una reserva");
			return;
		}
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			
			JOptionPane.showMessageDialog(this, reservaController.eliminar(id));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor elegir una reserva"));
	}
	
	private void modificarReserva() {
		if (tieneFilaElegida(tbReservas)) {
			JOptionPane.showMessageDialog(this, "Elejir una reserva");
			return;
		}
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			java.sql.Date fechaEntrada = convertDate(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
			java.sql.Date fechaSalida = convertDate(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
			long valor = ValorReserva.getValor(fechaSalida.getTime(), fechaEntrada.getTime());
			int filaSeleccionada = tbReservas.getSelectedRow();
			
			modelo.setValueAt(valor, filaSeleccionada, 3);
			
			String metodoPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
			
			Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, metodoPago);
			
			JOptionPane.showMessageDialog(this, reservaController.actualizar(reserva));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije una reserva"));
	}
	
	SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	
	private void modificarHuesped() {
		if (tieneFilaElegida(tbHuespedes)) {
			JOptionPane.showMessageDialog(this, "Elejir un huesped");
			return;
		}
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn())).ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
			String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
			java.sql.Date fechaNacimiento = convertDate(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
			String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
			int telefono = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
			int idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			
			Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
			
			JOptionPane.showMessageDialog(this, huespedController.actualizar(huesped));
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un huesped"));
	}
	
	private java.sql.Date convertDate(String fechaIngresada) {
		java.sql.Date fecha;
		
		try {
			fecha = new java.sql.Date(formatoFecha.parse(fechaIngresada).getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return fecha;
	}
	
	private void limpiarTablaReserva() {
		modelo.getDataVector().clear();
	}
	
	private void limpiarTablaHuesped() {
		modeloHuesped.getDataVector().clear();
	}
	
	private void limpiarTablas() {
		limpiarTablaHuesped();
		limpiarTablaReserva();
	}
	
	private void cargarTablas() {
		var reservas = this.reservaController.listar();
		rellenarTablaReservas(reservas);
		
		var huespedes = this.huespedController.listar();
		rellenarTablaHuespedes(huespedes);
	}
	
	private void rellenarTablaReservas(List<Reserva> reservas) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if (column == 0) {
					c.setEnabled(false);
				}
				
				return c;
			}
		};
		
		if (reservas != null) {
			try {
				for (Reserva reserva : reservas) {
					modelo.addRow(new Object[] {
							reserva.getId(),
							reserva.getFechaEntrada(),
							reserva.getFechaSalida(),
							reserva.getValor(),
							reserva.getFormaPago()
					});
				}
				
				tbReservas.getColumnModel().getColumn(0).setCellRenderer(renderer);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private void rellenarTablaHuespedes(List<Huesped> huespedes) {
		TableColumn column = tbHuespedes.getColumnModel().getColumn(6);
		column.setCellEditor(null);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if (column == 0 || column == 6) {
					c.setEnabled(false);
				}
				
				return c;
			}
		};
		
		if (huespedes != null) {
			try {
				for (Huesped huesped : huespedes) {
					modeloHuesped.addRow(new Object[] {
							huesped.getId(),
							huesped.getNombre(),
							huesped.getApellido(),
							huesped.getFechaNacimiento(),
							huesped.getNacionalidad(),
							huesped.getTelefono(),
							huesped.getIdReserva()
					});
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
