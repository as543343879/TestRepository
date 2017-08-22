package ch17.exam26;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class RootController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String,Integer> barChart;
    @FXML
    private AreaChart<String, Integer> areaChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> data1 = FXCollections.observableArrayList();
        data1.add(new PieChart.Data("AWT", 10));
        data1.add(new PieChart.Data("SWING", 86));
        data1.add(new PieChart.Data("SWT", 30));
        data1.add(new PieChart.Data("JavaFX", 50));
        pieChart.setData(data1);
        //-----------------------------XY차트-------------
        XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
        series1.setName("남자");
        ObservableList<XYChart.Data<String,Integer>> data2 = FXCollections.observableArrayList();
        data2.add(new XYChart.Data<String,Integer>("2015", 10));
        data2.add(new XYChart.Data<String,Integer>("2016", 42));
        data2.add(new XYChart.Data<String,Integer>("2017", 50));
        data2.add(new XYChart.Data<String,Integer>("2018", 80));
        series1.setData(data2);
        barChart.getData().add(series1);
        
        
        
        
        XYChart.Series<String,Integer> series2 = new XYChart.Series<>();
        series2.setName("여자");
        ObservableList<XYChart.Data<String,Integer>> data3 = FXCollections.observableArrayList();
        data3.add(new XYChart.Data<String,Integer>("2015", 60));
        data3.add(new XYChart.Data<String,Integer>("2016", 52));
        data3.add(new XYChart.Data<String,Integer>("2017", 10));
        data3.add(new XYChart.Data<String,Integer>("2018", 90));
        series2.setData(data3);
        barChart.getData().add(series2);
        
        //-------------------------------------------------areachart----------------------
         XYChart.Series<String,Integer> series4 = new XYChart.Series<>();
        series4.setName("작년기온");
        ObservableList<XYChart.Data<String,Integer>> data4 = FXCollections.observableArrayList();
        data4.add(new XYChart.Data<String,Integer>("2015", 60));
        data4.add(new XYChart.Data<String,Integer>("2016", 52));
        data4.add(new XYChart.Data<String,Integer>("2017", 10));
        data4.add(new XYChart.Data<String,Integer>("2018", 70));
        series4.setData(data4);
        areaChart.getData().add(series4);
        
        
        XYChart.Series<String,Integer> series5 = new XYChart.Series<>();
        series5.setName("평균기온");
        ObservableList<XYChart.Data<String,Integer>> data5 = FXCollections.observableArrayList();
        data5.add(new XYChart.Data<String,Integer>("2015", 40));
        data5.add(new XYChart.Data<String,Integer>("2016", 80));
        data5.add(new XYChart.Data<String,Integer>("2017", 10));
        data5.add(new XYChart.Data<String,Integer>("2018", 90));
        series5.setData(data5);
        areaChart.getData().add(series5);
        
        XYChart.Series<String,Integer> series6 = new XYChart.Series<>();
        series6.setName("올해기온");
        ObservableList<XYChart.Data<String,Integer>> data6 = FXCollections.observableArrayList();
        data6.add(new XYChart.Data<String,Integer>("2015", 90));
        data6.add(new XYChart.Data<String,Integer>("2016", 20));
        data6.add(new XYChart.Data<String,Integer>("2017", 50));
        data6.add(new XYChart.Data<String,Integer>("2018", 30));
        series6.setData(data6);
        areaChart.getData().add(series6);
        
        
    }    
    
}
