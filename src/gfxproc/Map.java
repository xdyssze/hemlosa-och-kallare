package gfxproc;



import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.*;



public class Map {
	String m, mp, mn;
	int ms;
	public Map(byte id) {
		Document doc;
		try {		
			File xml = new File(System.getProperty("user.dir") + "\\resources\\maps\\map" + id + ".xml");	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xml);
			m = doc.getElementsByTagName("mapstring").item(0).getTextContent();
			mp = doc.getElementsByTagName("mappathmap").item(0).getTextContent();
			mn = doc.getElementsByTagName("mapname").item(0).getTextContent();
			ms = Integer.valueOf(doc.getElementsByTagName("mapsize").item(0).getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
