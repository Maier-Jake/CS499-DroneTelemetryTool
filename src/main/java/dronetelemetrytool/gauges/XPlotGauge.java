package dronetelemetrytool.gauges;

import dronetelemetrytool.DTT_Tools;
import dronetelemetrytool.fieldparsing.Field;
import dronetelemetrytool.fieldparsing.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.List;


public class XPlotGauge extends Gauge {

    private NumberField field;

    private XYChart.Series<Number, Number> series;
    public NumberAxis primaryAxis;
    public NumberAxis subAxis;
    private ScatterChart<Number,Number> scatterChart;
    public GaugeOrient orient;

    public XPlotGauge(GaugeOrient orient, double lowerBound, double upperBound, double tickUnit)
    {
        super();
        this.gaugeType = GaugeType.XPLOT;
//        ArrayList<XYChart.Data> list = new ArrayList<>();
//        ArrayList<Double> doubles = new ArrayList<>(field.getNumberData());
//        for (Double d : doubles) {
//            list.add(new XYChart.Data(d, 1));
//        }
//        ObservableList<XYChart.Data> observableList = FXCollections.observableList(list);
        series = new XYChart.Series();

        tile.setTitle("Line Plot Gauge");

        tile.setAnimated(true);
        tile.setRunning(true);
        tile.setActive(true);

        tile.setMaxValue(upperBound);
        tile.setMinValue(lowerBound);

        primaryAxis = new NumberAxis(lowerBound, upperBound, tickUnit);
        subAxis = new NumberAxis(0, 2, 0);

        switch (orient)
        {
            case VERTICAL:
                scatterChart = new ScatterChart<Number, Number>(subAxis, primaryAxis);
                this.orient = GaugeOrient.VERTICAL;
                break;
            default:
                scatterChart = new ScatterChart<Number, Number>(primaryAxis,subAxis);
                this.orient = GaugeOrient.HORIZONTAL;
                break;
        }

        scatterChart.setVerticalZeroLineVisible(false);
        scatterChart.setVerticalGridLinesVisible(false);
        scatterChart.setHorizontalZeroLineVisible(false);
        scatterChart.setHorizontalGridLinesVisible(false);

        primaryAxis.setLabel("X Axis Label");
        subAxis.setTickLabelsVisible(false);

        scatterChart.getData().addAll(series);

        tile.setGraphic(scatterChart);

    }
    @Override
    public void update() {
        Double newVal_o = field.getNext();

        if (newVal_o == null) {
            return;
        }

        double newVal = newVal_o.doubleValue();
        double newValInRange = DTT_Tools.map(newVal, 0, tile.getRange(), tile.getMinValue(), tile.getMaxValue());
        switch (orient)
        {
            case VERTICAL:
                series.getData().add(new XYChart.Data(1, newValInRange));
                break;
            default:
                series.getData().add(new XYChart.Data(newValInRange, 1));
                break;
        }
        if(series.getData().size() > 5)
        {
            series.getData().remove(0);
        }
    }

    public void setLabel(String label)
    {
        primaryAxis.setLabel(label);
    }

    @Override
    public Field getField() { return field; }

    public void setField(NumberField field) {
        this.field = field;
    }
}
