package classes;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class IngresoDatos extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor para generar el JFrame
	 */
	public IngresoDatos(){
		super("Ingreso de datos");
		setSize(600, 380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		crearInterfazIngresarDatos();
	}
	
	/**
	 * Creación de los elementos que iran en el JFrame, y se añaden eventos
	 */
	private void crearInterfazIngresarDatos(){
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		int propiedadAlto = this.getHeight()/30;
		
		JLabel mensajePrincipal = new JLabel("Ingresa la información del cliente que se imprimirá.");
		mensajePrincipal.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/8));
		mensajePrincipal.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel etiquetaNombre = new JLabel("Nombre: ");
		JTextField textFieldNombre = new JTextField(20);
		JLabel etiquetaInvisible1 = new JLabel("");
		etiquetaInvisible1.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaNombre.getWidth()-textFieldNombre.getWidth(),propiedadAlto));
		
		JLabel etiquetaDireccion = new JLabel("Direccion: ");
		JTextField textFieldDireccion = new JTextField(20);
		JLabel etiquetaInvisible2 = new JLabel("");
		etiquetaInvisible2.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaDireccion.getWidth()-textFieldDireccion.getWidth(),propiedadAlto));
		
		JLabel etiquetaFechaNacimiento = new JLabel("Fecha de Nacimiento: ");
		JTextField textFieldFechaNacimiento = new JTextField(20);
		JLabel etiquetaInvisible3 = new JLabel("");
		etiquetaInvisible3.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaFechaNacimiento.getWidth()-textFieldFechaNacimiento.getWidth(),propiedadAlto));
		
		JLabel etiquetaEdad = new JLabel("Edad: ");
		JTextField textFieldEdad = new JTextField(20);
		JLabel etiquetaInvisible4 = new JLabel("");
		etiquetaInvisible4.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaEdad.getWidth()-textFieldEdad.getWidth(),propiedadAlto));
		
		JLabel etiquetaTelefono = new JLabel("Telefono: ");
		JTextField textFieldTelefono = new JTextField(20);
		JLabel etiquetaInvisible5 = new JLabel("");
		etiquetaInvisible5.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaTelefono.getWidth()-textFieldTelefono.getWidth(),propiedadAlto));
		
		JLabel etiquetaTratamiento = new JLabel("Tratamiento: ");
		JTextField textFieldTratamiento = new JTextField(20);
		JLabel etiquetaInvisible6 = new JLabel("");
		etiquetaInvisible6.setPreferredSize(new Dimension(
				this.getWidth()-etiquetaTratamiento.getWidth()-textFieldTratamiento.getWidth(),propiedadAlto));
		
		JButton botonAceptar = new JButton("Continuar");
		botonAceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Map<String,String> map = new HashMap<>();
				map.put("ContactName", textFieldNombre.getText());
				map.put("Address", textFieldDireccion.getText());
				map.put("Birthdate", textFieldFechaNacimiento.getText());
				map.put("Age", textFieldEdad.getText());
				map.put("Phone", textFieldTelefono.getText());
				map.put("treatment", textFieldTratamiento.getText());
				confirmarDatos(map);
			}
		});
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
		
		cp.add(mensajePrincipal);
		cp.add(etiquetaNombre);
		cp.add(textFieldNombre);
		cp.add(etiquetaInvisible1);
		
		cp.add(etiquetaDireccion);
		cp.add(textFieldDireccion);
		cp.add(etiquetaInvisible2);
		
		cp.add(etiquetaFechaNacimiento);
		cp.add(textFieldFechaNacimiento);
		cp.add(etiquetaInvisible3);
		
		cp.add(etiquetaEdad);
		cp.add(textFieldEdad);
		cp.add(etiquetaInvisible4);
		
		cp.add(etiquetaTelefono);
		cp.add(textFieldTelefono);
		cp.add(etiquetaInvisible5);
		
		cp.add(etiquetaTratamiento);
		cp.add(textFieldTratamiento);
		cp.add(etiquetaInvisible6);
		
		cp.add(botonAceptar);
		cp.add(botonCancelar);
	}
	
	/**
	 * JOptionPane para confirmar que la información esté correcta.
	 * 	Continuar: se procede a la impresión
	 * 	Regresar: se regresa al JFrame para editar información
	 * 
	 * @param map: contiene información ingresada en el JFrame
	 */
	public void confirmarDatos(Map<String,String> map){
		String datos = "\nNombre: " + map.get("ContactName")+"\n"
				+ "Direccion: " + map.get("Address")+"\n"
				+ "Fecha de Nacimiento: " + map.get("Birthdate")+"\n"
				+ "Edad: " + map.get("Age")+"\n"
				+ "Telefono: " + map.get("Phone")+"\n"
				+ "Tratamiento: " + map.get("treatment")+"\n";
		
		if(JOptionPane.showConfirmDialog(this, "¿Está la información correcta?"
				+ datos, "CONFIRMACION",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			System.out.println("YESS");
			this.setVisible(false);
			new GenerarReporte(map);
		}
		else{
			System.out.println("NOO");
			
		}
	}
}
