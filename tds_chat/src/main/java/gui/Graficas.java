package gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import controlador.ControladorAppChat;
import modelo.Grupo;

/**
 * Clase para la obteneción de las graficas procendentes de la inteacción del
 * Usuario con sus contactos
 * 
 * @author Jesus
 *
 */
public class Graficas {

	public void exportarGraficas() {
		crearHistograma();
		crearPieChart();

	}

	private void crearHistograma() {
		Integer[] nMensajesMes = ControladorAppChat.getUnicaInstancia().getMensajesPorMeses();

		CategoryChart grafica = new CategoryChartBuilder().width(900).height(400)
				.title("Número de Mensajes escritos cada mes").xAxisTitle("Meses").yAxisTitle("Mensajes").build();

		grafica.getStyler().setLegendPosition(LegendPosition.InsideNW);
		grafica.getStyler().setHasAnnotations(true);

		grafica.addSeries(
				"Nmensajes", Arrays.asList(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
						"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }),
				Arrays.asList(nMensajesMes));

		try {
			BitmapEncoder.saveBitmap(grafica, "./HistrogramasMensajes", BitmapFormat.PNG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Esta gráfica muestra en porcentaje cuanto ha interactuado el usuario dentro
	 * de un grupo en comparación con los demás, es decir, cuantos mensajes ha
	 * enviado respecto a los demás contactos.
	 * 
	 * El pie muestra en proporcion visual los grupos con más mensajes del usuario
	 */
	private void crearPieChart() {
		PieChart pie = new PieChartBuilder().width(600).height(400).title("Grupos Más Activos").build();
		Color[] sliceColors = new Color[] { new Color(238, 211, 160), new Color(160, 238, 162),
				new Color(160, 233, 238), new Color(160, 187, 238), new Color(222, 160, 238),
				new Color(238, 160, 172) };

		pie.getStyler().setSeriesColors(sliceColors);
		pie.getStyler().setLegendVisible(true);

		HashMap<Grupo, Double> gps = ControladorAppChat.getUnicaInstancia().getGruposMasActivos();

		for (Grupo g : gps.keySet()) {

			double percentMsg = gps.get(g) * 100;
			pie.addSeries(g.getNombre() + " " + String.valueOf(percentMsg) + "%", percentMsg);
		}

		try {
			BitmapEncoder.saveBitmap(pie, "./PieGrupos", BitmapFormat.PNG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
