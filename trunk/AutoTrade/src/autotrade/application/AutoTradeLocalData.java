/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.application;

import autotrade.core.virtualsystem.AutoTrade;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

/**
 *
 * @author Dinh
 */
public class AutoTradeLocalData {
    private static final String TAG_CURRENT_DATE = "current_date";
    private static final String TAG_CENTER_DATE = "center_date";
    private static final String TAG_NUMBER_OF_DAY = "number_of_date";
    private static final String TAG_SYMBOL = "symbol";
    private static final String TAG_ROOT = "root";
    private Date current_date;
    private Date center_date;
    private int number_of_day;
    private String symbol;
    
    private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private Calendar calender = Calendar.getInstance();
    private Document document;
    private static AutoTradeLocalData singleton;
    private File dataFile = new File("data.xml");

    public AutoTradeLocalData() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = (Document) db.parse(dataFile);

            Node node = doc.getElementsByTagName(TAG_CURRENT_DATE).item(0);
            current_date = dateFormat.parse(node.getAttributes().getNamedItem("value").getNodeValue());

            node = doc.getElementsByTagName(TAG_CENTER_DATE).item(0);
            center_date = dateFormat.parse(node.getAttributes().getNamedItem("value").getNodeValue());

            node = doc.getElementsByTagName(TAG_NUMBER_OF_DAY).item(0);
            number_of_day = Integer.parseInt(node.getAttributes().getNamedItem("value").getNodeValue());

            node = doc.getElementsByTagName(TAG_SYMBOL).item(0);
            symbol = node.getAttributes().getNamedItem("value").getNodeValue();

        } catch (Exception ex) {
            init();
        }
    }

    private void init() {
        current_date = new Date(AutoTrade.getLatestTimeInDatabase());
        number_of_day = 300;

        calender.setTime(current_date);
        calender.add(Calendar.DAY_OF_MONTH, -number_of_day/2);
        center_date = calender.getTime();
        symbol = "";
    }

    private void createNewDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();
            createDomTree();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error when create document builder");
        }
    }

    private void createDomTree() {
        Element mapEle = document.createElement(TAG_ROOT);

        Element current_dateElement = document.createElement(TAG_CURRENT_DATE);
        current_dateElement.setAttribute("value", dateFormat.format(current_date));

        Element number_of_dayElement = document.createElement(TAG_NUMBER_OF_DAY);
        number_of_dayElement.setAttribute("value", number_of_day + "");

        Element center_dateElement = document.createElement(TAG_CENTER_DATE);
        center_dateElement.setAttribute("value", dateFormat.format(center_date));

        Element symbolElement = document.createElement(TAG_SYMBOL);
        symbolElement.setAttribute("value", symbol);

        //add to root node
        mapEle.appendChild(current_dateElement);
        mapEle.appendChild(number_of_dayElement);
        mapEle.appendChild(center_dateElement);
        mapEle.appendChild(symbolElement);

        //add root node to document
        document.appendChild(mapEle);
    }

        /**
     * write new configuration to file, finish saving
     */
    public void saveAutoTradeLocalData() {
        try {
            createNewDocument();
            //print
            OutputFormat format = new OutputFormat(document);
            format.setIndenting(true);
            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);
            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(dataFile), format);

            serializer.serialize(document);

        } catch (IOException ie) {
            System.out.println("Error when create document builder");
        }
    }


    public static AutoTradeLocalData load() {

        if (singleton == null) {
            singleton = new AutoTradeLocalData();
        }

        return singleton;
    }

    public Date getCurrentDate() {
        return current_date;
    }

    public void setCurrentDate(Date currentDate) {
        this.current_date = currentDate;
    }

        public Date getCenter_date() {
        return center_date;
    }

    public void setCenter_date(Date center_date) {
        this.center_date = center_date;
    }

    public int getNumber_of_day() {
        return number_of_day;
    }

    public void setNumber_of_day(int number_of_day) {
        this.number_of_day = number_of_day;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}