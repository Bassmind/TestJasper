package classes;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.engine.data.JsonDataSource;

import org.apache.pdfbox.printing.PDFPageable;

public class GenerarReporte {
	public GenerarReporte(){
		
	}
	
	public GenerarReporte(Map<String,String> map){
		try{
			generarReporte(map);
			imprimirPrimerPagina();
			imprimirSegundaPagina();
			
			System.out.println("FINALIZA EJECUCION");
		}
		catch(Exception e){
			System.out.println("Fallo en la generacion del report: " + e.getMessage());
		}
		finally{
			System.exit(0);
		}
	}
	
	/**
	 * Generación del reporte en PDF basado en el .jasper
	 * Reporte se guarda en carpeta del proyecto
	 * 
	 * @throws JRException
	 */
	public void generarReporte(Map<String,String> map) throws JRException{
		if(map.get("Phone").equals("")){
			System.out.println("s"+map.get("Phone"));
		}
		else{
			System.out.println("HOla");
		}
		String patientString = "{\"Phone\":\"" + ((map.get("Phone").equals(""))?"":map.get("Phone")) 
        	+ "\",\"ContactName\":\"" + ((map.get("ContactName").equals(""))?"":map.get("ContactName")) 
        	+ "\",\"Address\":\"" + ((map.get("Address").equals(""))?"":map.get("Address")) 
        	+ "\",\"Age\":\"" + ((map.get("Age").equals(""))?0:map.get("Age")) 
        	+ "\",\"Birthdate\":\"" + ((map.get("Birthdate").equals(""))?"":map.get("Birthdate")) 
        	+ "\",\"Treatment\":\"" + ((map.get("Treatment").equals(""))?"":map.get("Treatment")) + "\"}";
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource("./Blank_Letter.jasper");
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
        }
        		
		System.out.println(patientString);
        //Cargar el Reporte
		JasperReport report = (JasperReport) JRLoader.loadObject(new File(resource.getFile()));
        
		//Llenar el reporte con datos del JSON construido
		ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(patientString.getBytes());
		JsonDataSource ds = new JsonDataSource(jsonDataStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report,null,ds);
		
		JasperExportManager.exportReportToPdfFile(jasperPrint,"jasperpdfexample.pdf");
		System.out.println("PDF GENERADO");
	}
	
	/**
	 * Imprime el documento generado (Primer página)
	 */
	public void imprimirPrimerPagina(){
		try{
			JOptionPane.showMessageDialog(null, "Ahora se imprimirá la primer página del reporte");
			PDDocument document = PDDocument.load(new File("jasperpdfexample.pdf"));
			PrinterJob job = PrinterJob.getPrinterJob();
			
			if (job.printDialog() == true) {
				job.setPageable(new PDFPageable(document));
				job.print();
			}
		}
		catch (PrinterException | IOException ex) {
			System.out.println("Error durante impresión: " + ex.getMessage());
		}
	}
	
	/**
	 * Imprime la segunda página del PDF
	 */
	public void imprimirSegundaPagina(){
		try{
			JOptionPane.showMessageDialog(null, "Ahora se imprimirá la segunda página del reporte."
			 	+ " Por favor inserte la hoja en la posición deseada.");
			
			PDDocument document = PDDocument.load(new File("page2.pdf"));
			PrinterJob job = PrinterJob.getPrinterJob();
			
			if (job.printDialog() == true) {
				job.setPageable(new PDFPageable(document));
				job.print();
			}
		}
		catch (PrinterException | IOException ex) {
			System.out.println("Error durante impresión: " + ex.getMessage());
		}
	}
}
