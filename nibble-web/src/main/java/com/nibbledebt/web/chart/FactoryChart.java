package com.nibbledebt.web.chart;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import javafx.stage.Stage;

public class FactoryChart extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Color trans = new Color(0xFF, 0xFF, 0xFF, 0);

	// @Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	private static PieDataset createDataset(Map<String, BigDecimal> dataSet) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataSet.forEach((k, v) -> {
			dataset.setValue(k, v.doubleValue());
		});
		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	public static JFreeChart createPieChart(Map<String, BigDecimal> dataSet) {
		JFreeChart chart = ChartFactory.createPieChart("", // chart title
				createDataset(dataSet), // data
				true, // include legend
				true, false);
		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(trans);
		chart.setBackgroundPaint(trans);
		plot.setInteriorGap(0.0);
		plot.setLabelGenerator(null);
		return chart;
	}

	private static CategoryDataset createDataset(BigDecimal you, BigDecimal yourCrowd) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		result.addValue(you.doubleValue(), "YOU", "");
		result.addValue(yourCrowd.doubleValue(), "YOUR CROWD", "");
		return result;
	}

	public static JFreeChart createChart(BigDecimal you, BigDecimal yourCrowd) {
		final JFreeChart chart = ChartFactory.createStackedBarChart(null, // chart
																			// title
				null, // domain axis label
				null, // range axis label
				createDataset(you, yourCrowd), // data
				PlotOrientation.VERTICAL, // the plot orientation
				false, // legend
				false, // tooltips
				false // urls
		);
		GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
		renderer.setSeriesPaint(1, new Color(43, 222, 115));
		renderer.setSeriesPaint(0, new Color(23, 81, 148));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.getRangeAxis().setVisible(false);
		plot.setBackgroundPaint(trans);
		chart.setBackgroundPaint(trans);
		
		plot.setOutlineVisible(false);
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setShadowVisible(true);
		plot.setRenderer(renderer);

		return chart;
	}

	public static JFreeChart createRingChart(Map<String, BigDecimal> dataSet) {
		RingPlot plot = new RingPlot(createDataset(dataSet));
		plot.setOutlineVisible(false);
		plot.setSeparatorsVisible(false);
		plot.setSectionOutlinesVisible(false);
		List<Color> colors=Arrays.asList(Color.decode("#F5A623"),Color.decode("#BD10E0"),Color.decode("#E74C3C"),Color.decode("#175194"),Color.decode("#F8E71C"),Color.decode("#7ED321"));
		Iterator<String> iter=dataSet.keySet().iterator();
		colors.forEach(c->{
			if(iter.hasNext())
			plot.setSectionPaint(iter.next(), c);
		});
		//plot.setShadowPaint();
		// plot.setSectionDepth(0.05);
		JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
		// chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
		// new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));
		// final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(trans);
		chart.setBackgroundPaint(trans);
		plot.setLabelGenerator(null);
		plot.setNoDataMessage("No data to display");
		// create a new legend title area and add it to the chart
		LegendTitle legend = new LegendTitle(plot);
		legend.setPosition(RectangleEdge.RIGHT);
		chart.addSubtitle(legend);
		//plot.setInteriorGap(0.0);
		// LegendTitle legend = plot.setlabelg
		// legend.setPosition(RectangleEdge.RIGHT);

		return chart;
	}

	public FactoryChart(final String title) {
		super(title);
		Map<String, BigDecimal> map = new HashMap<>();
		map.put("MBQ", BigDecimal.TEN);
		map.put("JQL", new BigDecimal("14"));
		map.put("CCS", new BigDecimal("3"));
		map.put("MFC", new BigDecimal("30"));
		map.put("AQD", new BigDecimal("43"));
		map.put("MBQ1", BigDecimal.TEN);
		map.put("JQL1", new BigDecimal("14"));
		map.put("CCS1", new BigDecimal("3"));
		map.put("MFC1", new BigDecimal("30"));
		map.put("AQD1", new BigDecimal("43"));
		map.put("MBQ2", BigDecimal.TEN);
		map.put("JQL2", new BigDecimal("14"));
		map.put("CCS2", new BigDecimal("3"));
		map.put("MFC2", new BigDecimal("30"));
		map.put("AQD2", new BigDecimal("43"));
//		 final CategoryDataset dataset = createRingChart();
		final JFreeChart chart = createRingChart(map);
//		final JFreeChart chart = createChart(new BigDecimal(30), new BigDecimal(70));
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(100, 500));
		setContentPane(chartPanel);
	}

	public static void main(final String[] args) {
		final FactoryChart demo = new FactoryChart("Stacked Bar Chart Demo 4");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}
