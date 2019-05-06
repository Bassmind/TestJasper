package classes;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TestMain extends Application{
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println("INICIA EJECUCION");
		
		window = primaryStage;
		window.setResizable(false);
		window.setTitle("Generar Reporte");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10));
		
		Text welcome = new Text("Ingresa la información del cliente que se imprimirá.");
		welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT,15));
		grid.add(welcome, 0, 0, 2, 1);
		
		Label labelName = new Label("Nombre:");
		grid.add(labelName, 0, 1);
		
		TextField tfName = new TextField();
		tfName.setPromptText("nombre");
		grid.add(tfName, 1, 1);
		
		Label labelDireccion = new Label("Direccion:");
		grid.add(labelDireccion, 0, 2);
		
		TextField tfDireccion = new TextField();
		tfDireccion.setPromptText("direccion");
		grid.add(tfDireccion, 1, 2);
		
		Label labelBirthdate = new Label("Fecha de Nacimiento:");
		grid.add(labelBirthdate, 0, 3);
		
		TextField tfBirthdate = new TextField();
		tfBirthdate.setPromptText("fecha de nacimiento");
		grid.add(tfBirthdate, 1, 3);
		
		Label labelEdad = new Label("Edad:");
		grid.add(labelEdad, 0, 4);
		
		TextField tfEdad = new TextField();
		tfEdad.setPromptText("edad");
		grid.add(tfEdad, 1, 4);
		
		Label labelTelefono = new Label("Telefono:");
		grid.add(labelTelefono, 0, 5);
		
		TextField tfTelefono = new TextField();
		tfTelefono.setPromptText("telefono");
		grid.add(tfTelefono, 1, 5);
		
		Label labelTratamiento = new Label("Tratamiento:");
		grid.add(labelTratamiento, 0, 6);
		
		TextField tfTratamiento = new TextField();
		tfTratamiento.setPromptText("tratamiento");
		grid.add(tfTratamiento, 1, 6);
		
		Button buttonContinuar = new Button("Continuar");
		grid.add(buttonContinuar, 0, 7);
		buttonContinuar.setOnAction(e->{
			Map<String,String> map = new HashMap<>();
			map.put("ContactName", tfName.getText());
			map.put("Address", tfDireccion.getText());
			map.put("Birthdate", tfBirthdate.getText());
			map.put("Age", tfEdad.getText());
			map.put("Phone", tfTelefono.getText());
			map.put("Treatment", tfTratamiento.getText());
			
			confirmarDatos(map);
		});
		
		Button buttonCancelar = new Button("Cancelar");
		grid.add(buttonCancelar, 1, 7);
		buttonCancelar.setOnAction(e->{
			System.exit(0);
		});
		
		Scene scene = new Scene(grid, 400,400);
		window.setScene(scene);
		window.show();
	}
	
	public void confirmarDatos(Map<String,String> map){
		String datos = "\nNombre: " + map.get("ContactName")+"\n"
				+ "Direccion: " + map.get("Address")+"\n"
				+ "Fecha de Nacimiento: " + map.get("Birthdate")+"\n"
				+ "Edad: " + map.get("Age")+"\n"
				+ "Telefono: " + map.get("Phone")+"\n"
				+ "Tratamiento: " + map.get("Treatment")+"\n";
		
		if(JOptionPane.showConfirmDialog(null, "¿Está la información correcta?"
				+ datos, "CONFIRMACION",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			System.out.println("YESS");
			window.hide();
			new GenerarReporte(map);
		}
		else{
			System.out.println("NOO");	
		}
	}
}