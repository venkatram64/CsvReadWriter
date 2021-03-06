package com.shamanthaka.rl.model;

public class ProductXml {
    private int id;
    private String xmlRow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXmlRow() {
        return xmlRow;
    }

    public void setXmlRow(String xmlRow) {
        this.xmlRow = xmlRow;
    }

    @Override
    public String toString() {
        return "ProductXml{" +
                "id=" + id +
                ", xmlRow='" + xmlRow + '\'' +
                '}';
    }
}
