package clientes_MVC;


import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Vista extends JFrame {
	JDialog modal;
	JScrollPane scroll;
	JTable tabla;
	DefaultTableModel registro;
	JLabel etqRFC, etqNombre, etqEdad, etqIdCiudad, etqTitulo;
	JTextField txtRFC, txtNombre, txtEdad, txtIdCiudad;
	JButton btnRecuperar, btnBorrar, btnConsultar, btnGrabar, btnModificar;
	ImageIcon imgConsultar, imgGrabar, imgModificar, imgBorrar, imgRecuperar;
	

	public Vista() {
		super("*** Mantenimiento al Catálogo de Clientes ***");
		hazInterfaz();
	}
	
	public void hazInterfaz() {
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		imgConsultar = new ImageIcon("consultar.png");
		imgGrabar = new ImageIcon("grabar.png");
		imgModificar = new ImageIcon("modificar.png");
		imgBorrar = new ImageIcon("borrar.png");
		imgRecuperar = new ImageIcon("recuperar.png");
		
		//Ajusté el tamaño de los íconos de los botones
		Image img1 = imgConsultar.getImage();
		Image img2 = imgGrabar.getImage();
		Image img3 = imgModificar.getImage();
		Image img4 = imgBorrar.getImage();
		Image img5 = imgRecuperar.getImage();
		Image newImg1 = img1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Image newImg2 = img2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Image newImg3 = img3.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Image newImg4 = img4.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Image newImg5 = img5.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgConsultar = new ImageIcon(newImg1);
		imgGrabar = new ImageIcon(newImg2);
		imgModificar = new ImageIcon(newImg3);
		imgBorrar = new ImageIcon(newImg4);
		imgRecuperar = new ImageIcon(newImg5);
		
		//setLayout(new GridLayout(7,2));
		setLayout(null);
		etqTitulo = new JLabel("Mantenimiento al Catálogo de Clientes");
		etqRFC = new JLabel("RFC");
		etqNombre = new JLabel("Nombre");
		etqEdad = new JLabel("Edad");
		etqIdCiudad = new JLabel("ID Ciudad");
		etqTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		etqRFC.setFont(new Font("Arial", Font.BOLD, 14));
		etqNombre.setFont(new Font("Arial", Font.BOLD, 14));
		etqEdad.setFont(new Font("Arial", Font.BOLD, 14));
		etqIdCiudad.setFont(new Font("Arial", Font.BOLD, 14));
		etqTitulo.setBounds(120, 10, 400, 30);
		etqRFC.setBounds(160, 60, 100, 20);
		etqNombre.setBounds(160, 100, 100, 20);
		etqEdad.setBounds(160, 140, 100, 20);
		etqIdCiudad.setBounds(160, 185, 100, 20);
		txtRFC = new JTextField();
		txtNombre = new JTextField();
		txtEdad = new JTextField();
		txtIdCiudad = new JTextField();
		txtRFC.setBounds(260, 60, 200, 25);
		txtNombre.setBounds(260, 100, 200, 25);
		txtEdad.setBounds(260, 140, 200, 25);
		txtIdCiudad.setBounds(260, 180, 200, 25);
		btnRecuperar = new JButton("Recuperar", imgRecuperar);
		btnBorrar = new JButton("Borrar", imgBorrar);
		btnConsultar = new JButton("Consultar", imgConsultar);
		btnGrabar = new JButton("Grabar", imgGrabar);
		btnModificar = new JButton("Modificar", imgModificar);
		btnRecuperar.setBounds(150, 230, 130, 30);
		btnBorrar.setBounds(150, 270, 130, 30);
		btnConsultar.setBounds(150, 310, 130, 30);
		btnGrabar.setBounds(350, 250, 130, 30);
		btnModificar.setBounds(350, 290, 130, 30);
		
		add(etqTitulo);
		add(etqRFC);
		add(etqNombre);
		add(etqEdad);
		add(etqIdCiudad);
		add(txtRFC);
		add(txtNombre);
		add(txtEdad);
		add(txtIdCiudad);
		add(btnRecuperar);
		add(btnBorrar);
		add(btnConsultar);
		add(btnGrabar);
		add(btnModificar);
	}
	
	public void tablaRegistros(ArrayList<Cliente> arrClientes) {
		modal = new JDialog();
		modal.setSize(600,500);
		modal.setLocationRelativeTo(null);
		modal.setResizable(false);
		
		//Evita la escritura en la tabla de registros
		registro = new DefaultTableModel() {
			public boolean isCellEditable(int filas, int col){
				return false;
			}
		};
		registro.addColumn("RFC");
		registro.addColumn("Nombre");
		registro.addColumn("Edad");
		registro.addColumn("ID Ciudad");
		tabla = new JTable(registro);
		scroll = new JScrollPane(tabla);
		tabla.setBounds(50, 10, 700, 50);
		scroll.setBounds(10, 10, 750, 650);
		modal.add(scroll, BorderLayout.CENTER);
		modal.setModal(true);
		
		llenarTabla(arrClientes);		
		modal.setVisible(true);
	}
	
	
	//Poner como parámetro una variable del Controlador para que lleguen los datos
	public void llenarTabla(ArrayList<Cliente> arrClientes) {
		try {
			for(int i=0; i<arrClientes.size(); i++) {
				registro.addRow(new Object[] {
						arrClientes.get(i).getRFC(), 
						arrClientes.get(i).getNombre(), 
						arrClientes.get(i).getEdad()+"", 
						arrClientes.get(i).getIdCiudad()+""});
			}
		} catch (Exception e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void setControlador(ClientesControlador c) {
		btnRecuperar.addActionListener(c);
		btnBorrar.addActionListener(c);
		btnConsultar.addActionListener(c);
		btnGrabar.addActionListener(c);
		btnModificar.addActionListener(c);
		
		txtRFC.addKeyListener(c);
		txtNombre.addKeyListener(c);
		txtEdad.addKeyListener(c);
		txtIdCiudad.addKeyListener(c);
	}
	
	
	public void muestrate() {
		setVisible(true);
	}
	
	public String getRfc(){
		try{
			return txtRFC.getText();
		}catch(Exception e){
			return "";
		}
	}
	public String getNombre(){
		try{
			return txtNombre.getText();
		}catch(Exception e){
			return "";
		}
	}
	public int getEdad(){
		try{
			return Integer.parseInt(txtEdad.getText());
		}catch(Exception e){
			return 0;
		}
	}
	public int getIdCiudad(){
		try{
			return Integer.parseInt(txtIdCiudad.getText());
		}catch(Exception e){
			return 0;
		}
	}
	public void setRfc(String rfc){
		txtRFC.setText(rfc);
	}
	public void setNombre(String nombre){
		txtNombre.setText(nombre);
	}
	public void setEdad(int edad){
		txtEdad.setText(edad+"");
	}
	public void setIdCiudad(int idCiudad){
		txtIdCiudad.setText(idCiudad+"");
	}
	
	public void limpiarCampos() {
		txtRFC.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		txtIdCiudad.setText("");
	}

	public void exito(String msg){
		JOptionPane.showMessageDialog(null,msg,"Exito",JOptionPane.INFORMATION_MESSAGE);
	}
	public void error(String msg){
		JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
	}
}
