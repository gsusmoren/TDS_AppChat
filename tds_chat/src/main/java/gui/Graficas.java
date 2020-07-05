package gui;

import java.io.IOException;
import java.util.Arrays;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import controlador.ControladorAppChat;
import modelo.ContactoIndividual;

//Clase para la obteneción de las grafiacas del usuario
public class Graficas {

	public void exportarGraficas() {
		crearHistograma();
		

	}

	private void crearHistograma() {
		Integer[] nMensajesMes = ControladorAppChat.getUnicaInstancia().getMensajesPorMeses();

		CategoryChart grafica = new CategoryChartBuilder().width(600).height(400)
				.title("Número de Mensajes escritos cada mes").xAxisTitle("Meses").yAxisTitle("Mensajes").build();
		
		
		grafica.getStyler().setLegendPosition(LegendPosition.InsideNW);
	    grafica.getStyler().setHasAnnotations(true);

	
		
		grafica.addSeries("Nmensajes", Arrays.asList(new String[] { "Enero", "Febrero", "Marzo",
				"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciembre"}), Arrays.asList(nMensajesMes));
		
		
		try {
			BitmapEncoder.saveBitmap(grafica, "./HistrogramasMensajes", BitmapFormat.PNG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void crearPieChart() {
		
	}
	
	
	
	

}
