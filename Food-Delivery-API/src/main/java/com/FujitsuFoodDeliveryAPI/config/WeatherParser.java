package com.FujitsuFoodDeliveryAPI.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import org.xml.sax.InputSource;

@Component
@EnableScheduling
public class WeatherParser {

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void parseWeatherData() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            Document doc = db.parse(new InputSource(new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1)));
            NodeList nodeList = doc.getElementsByTagName("station");

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String wmoCode = element.getElementsByTagName("wmocode").item(0).getTextContent();
                    if ("26038".equals(wmoCode) || "26242".equals(wmoCode) || "41803".equals(wmoCode)) {
                        //parseAndInsertWeatherData(element);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
