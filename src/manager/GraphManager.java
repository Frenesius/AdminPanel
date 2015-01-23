package manager;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import price.Price;

public class GraphManager {
	
	public String generateGraphByEan(String ean){
		DefaultCategoryDataset line = new DefaultCategoryDataset();
		MySqlManager mysqlMan = new MySqlManager();
		ArrayList<Price> price = mysqlMan.getPricesByEan(ean);
		for(Price p : price){
			line.addValue(Double.parseDouble(p.getPriceex().replace(",", ".").replace("-", ""))/4, p.getPriceex(), p.getTimestamp());
		}
		JFreeChart chart = ChartFactory.createLineChart("Price's", "Time", "Price", line, PlotOrientation.VERTICAL,
				true,true,false);
		String path = "";
		try {
			Date date = new Date();
			String filename = new Timestamp(date.getTime()).toString().replace(" ", "");
			System.out.println(filename);
			File lineChart = new File("/home/j/Charts/"+filename+".png");
			ChartUtilities.saveChartAsPNG(lineChart, chart, 800, 600);
			path = lineChart.getAbsolutePath();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	
	
	
}
