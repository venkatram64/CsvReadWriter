package com.shamanthaka.rl.util;

import com.shamanthaka.rl.model.ProductXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XMLElement {

    private Document createDocument() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    private Connection getConnection() throws Exception{
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/my_prod_db","root","root");
        }catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return connection;
    }

    public List<ProductXml> convertRowIntoXmlData() {
        Connection conn = null;
        Statement st = null;
        ResultSet resultSet = null;
        List<ProductXml> productXmlList = new ArrayList<>();
        try{
            conn = getConnection();
            st = conn.createStatement();
            resultSet = st.executeQuery("select * from products");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while(resultSet.next()){
                ProductXml productXml = new ProductXml();
                Document doc = createDocument();
                Element rootElement = (Element) doc.createElement("Products");
                doc.appendChild(rootElement);
                for(int i = 1; i <= metaData.getColumnCount(); i++){
                    String colName = metaData.getColumnName(i);

                    Object colValue = resultSet.getObject(i);
                    if("id".equals(colName)){
                        productXml.setId(Integer.parseInt(colValue.toString()));
                    }
                    Element node = (Element) doc.createElement(colName);
                    node.appendChild(doc.createTextNode(colValue.toString().trim()));
                    rootElement.appendChild(node);
                }
                String xml = convertXMLStr(doc);
                productXml.setXmlRow(xml);
                productXmlList.add(productXml);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productXmlList;
    }

    private String convertXMLStr(Document doc) throws Exception{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter sw = new StringWriter();
        t.transform(source, new StreamResult(sw));
        String myXml = sw.getBuffer().toString();
        return myXml;
    }
}
