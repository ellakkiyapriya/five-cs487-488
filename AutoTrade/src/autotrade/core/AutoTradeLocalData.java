/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core;

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
    private static final String TAG_ROOT = "root";
    private Date current_date;
    private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
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
        } catch (Exception ex) {
            init();
        }
    }

    private void init() {
        current_date = new Date(Calendar.getInstance().getTimeInMillis());
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
        System.out.println(dateFormat.format(current_date));
//        current_dateElement.setNodeValue(dateFormat.format(current_date));
        current_dateElement.setAttribute("value", dateFormat.format(current_date));


        //add to root node
        mapEle.appendChild(current_dateElement);

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

}