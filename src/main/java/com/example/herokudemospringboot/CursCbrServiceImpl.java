package com.example.herokudemospringboot;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CursCbrServiceImpl implements CursParser {


    @Override
    public ValCurs parseCurs() {

        String URL = "https://cbr.ru/scripts/XML_daily.asp";

        List<Valute> valCurs = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(URL);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    Valute valute = new Valute(
                            elem.getElementsByTagName("NumCode").item(0).getTextContent(),
                            elem.getElementsByTagName("CharCode").item(0).getTextContent(),
                            Integer.parseInt(elem.getElementsByTagName("Nominal").item(0).getTextContent()),
                            elem.getElementsByTagName("Name").item(0).getTextContent(),
                            (BigDecimal) getDecimalFormat().parse(elem.getElementsByTagName("Value").item(0).getTextContent())
                    );

                    valCurs.add(valute);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ValCurs(valCurs);
    }

    private DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "###000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }

}
