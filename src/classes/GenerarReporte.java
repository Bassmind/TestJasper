package classes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class GenerarReporte {
	public GenerarReporte(){
		try{
			System.out.println("FINALIZA EJECUCION");
			
			//guardarInfo();
			generarReporte();
		}
		catch(Exception e){
			System.out.println("Fallo en la generacion del report: " + e.getMessage());
		}
	}
	
	public void guardarInfo(){
		
	}
	
	public void generarReporte() throws JRException{
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				"C:\\InvoiceTest.jasper", null,
				Conexion.conectar());
		JRPdfExporter exp = new JRPdfExporter();
		exp.setExporterInput(new SimpleExporterInput(jasperPrint));
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteClientes.pdf"));
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exp.setConfiguration(conf);
		exp.exportReport();
 
		System.out.println("PROGRAMA TERMINADO");
		
		/*// se muestra en una ventana aparte para su descarga
		JasperPrint jasperPrintWindow = JasperFillManager.fillReport(
				"C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\ReporteAlumnos.jasper", null,
				Conexion.conectar());
		JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
		jasperViewer.setVisible(true);*/
	}
}
