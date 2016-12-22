package com.hansson.dybalcalc.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.vaadin.addon.JFreeChartWrapper;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.vaadin.ui.CustomComponent;

@SuppressWarnings("serial")
public class HanssonChart extends CustomComponent {
	private String title;
	private VectorBal[] vectorBal;
	private String[] str;
	
	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


    public VectorBal[] getVectorBal() {
		return vectorBal;
	}

	public void setVectorBal(VectorBal[] vectorBal) {
		this.vectorBal = vectorBal;
	}

	public HanssonChart(String title, VectorBal[] vectorBal, String[] str) {
    	this.vectorBal = vectorBal;
    	this.str = str;
    	this.title = title;

    	setCaption(title);
    	JFreeChart chart = createChart(createDataset(vectorBal,str));
    	chart.setBackgroundPaint(Color.WHITE);

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setSizeFull();
        //Panel input = new Panel("");
        //input.setSizeFull();
        //input.setStyleName("v-panel-caption");
        //input.setContent(wrapper);
        //VerticalLayout v = new VerticalLayout();
        //v.addComponent(wrapper);
        setCompositionRoot(wrapper);
        setSizeFull();
        //v.setWidth("100%");
    } 
    
    public  XYDataset createDataset(VectorBal[] vectorBal, String[] str) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries ser[]=new XYSeries[str.length];
        for(int i=0; i<str.length; i++){
        	ser[i] = new XYSeries(str[i]);
        	ser[i].add(0, 0);
        	ser[i].add((90-vectorBal[i].getpha()+360)%360, 25);
        	result.addSeries(ser[i]);
        }
        
        return result;
    }

    private  JFreeChart createChart(XYDataset dataset) {
        ValueAxis radiusAxis = new NumberAxis();
        radiusAxis.setTickLabelsVisible(false);
        PolarItemRenderer renderer = new DefaultPolarItemRenderer();
        PolarPlot plot = new PolarPlot(dataset, radiusAxis, renderer) {

            @Override
            protected List refreshAngleTicks() {
                List<NumberTick> ticks = new ArrayList<NumberTick>();
                int delta = (int) this.getAngleTickUnit().getSize();
                for (int t = 0; t < 360; t += delta) {
                    int tp = (360 + 90 - t) % 360;
                    NumberTick tick = new NumberTick(
                        Double.valueOf(t), String.valueOf(tp)+"°",
                        TextAnchor.CENTER, TextAnchor.CENTER_RIGHT, 0.0);
                    ticks.add(tick);
                }
                for(int j=0; j<str.length; j++){
                	if(vectorBal[j].getamp()!=0 && vectorBal[j].getpha()!=0){
                        NumberTick tickt = new NumberTick(
                                Double.valueOf((90-vectorBal[j].getpha()+360)%360), 
                                String.valueOf(vectorBal[j].getamp())+"g∠"+
                                String.valueOf(vectorBal[j].getpha())+"°",
                                TextAnchor.CENTER, TextAnchor.CENTER, 
                                vectorBal[j].getpha());
                        
                            ticks.add(tickt);
                	}

                }
   
                return ticks;
            }
        };
        NumberAxis numberaxis = (NumberAxis) plot.getAxis();
        //numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRange(true);
        //numberaxis.setVisible(true);
        numberaxis.setTickUnit(new NumberTickUnit(5));
        
        plot.setBackgroundPaint(Color.WHITE);
        
        plot.addCornerTextItem("以0°为键槽的位置，角度按逆时针方向旋转");
        plot.setBackgroundImageAlignment(PolarPlot.MINIMUM_HEIGHT_TO_DRAW);
        plot.setAngleTickUnit(new NumberTickUnit(45));//设置角度标签间隔
        plot.setAngleGridlinesVisible(true);//设置角度网格线是否可见；
        plot.setAngleLabelsVisible(true);
        //plot.setCounterClockwise(true);
        //plot.setAxisLocation(PolarAxisLocation.EAST_BELOW);
        //plot.setAngleOffset(0);
    	/*GradientPaint gradientpaint3 = new GradientPaint(0.0F, 0.0F,
			     Color.black, 0.0F, 0.0F, Color.black);
		plot.setRadiusGridlinePaint(gradientpaint3);
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.red,
			     0.0F, 0.0F, Color.red); //
	    GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F,
			     Color.blue, 0.0F, 0.0F, Color.blue);

        ren.setSeriesPaint(0, gradientpaint1);
        ren.setSeriesPaint(1, gradientpaint2);
        plot.setRenderer(ren);*/
        
        DefaultPolarItemRenderer ren = new DefaultPolarItemRenderer();
        ren.setSeriesFilled(0, true);
        ren.setSeriesFilled(1, true);
        //plot.setRenderer(ren);
        //plot.setAngleGridlinePaint(new GradientPaint(1.0f, 2.0f, Color.red,
                //3.0f, 4.0f, Color.blue));//设置角度坐标线的颜色；
        Stroke s = new BasicStroke(1.23f);
   
        //plot.setAngleGridlineStroke(s);//设置角度坐标线的粗细；
        plot.setAngleLabelFont(new Font("Serif", Font.PLAIN, 15));//设置角度标签字体
        //plot.setAngleLabelPaint(new GradientPaint(9.0f, 8.0f, Color.blue,
               // 7.0f, 6.0f, Color.red));//设置角度标签颜色；
        //plot.setRadiusGridlinePaint(new GradientPaint(1.0f, 2.0f, Color.white,
               // 3.0f, 4.0f, Color.black));//设置半径线颜色；
        //plot.setRadiusGridlineStroke(s);//设置半径线粗细；
        plot.setRadiusGridlinePaint(Color.gray);
        
        JFreeChart chart = new JFreeChart(
            title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        chart.setBackgroundPaint(Color.white);

        return chart;
    }

}
    
    
