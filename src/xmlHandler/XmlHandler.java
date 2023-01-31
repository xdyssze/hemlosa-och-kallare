package xmlHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import game.Game;
/**
 * A small application that parses xmlFiles in a way that makes it more easy for a person to understand,
 * if they dont posses an extensive knowledge of xml. Aka makes it less cancer
 * 
 */
public class XmlHandler {
    Document doc;
    public Element main;
    String[] param;
    Node[] PrimaryNode;
    Node[] nHand;
	public XmlHandler(String fileName, boolean Writer) {
		if(!Writer) {
		    param = new String[4];
		    try {
		        File f = new File(System.getProperty("user.dir") + "\\resources\\" + fileName + ".xml");
		        DocumentBuilderFactory dF = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = dF.newDocumentBuilder();
		        this.doc = builder.parse(f);
		        this.main = (Element) doc.getElementsByTagName(fileName).item(0);	
		        param[0] = String.valueOf(this.main.getChildNodes().getLength());
		        nHand = new Node[NodeLength()];
		        for(int i = 0; i < NodeLength(); i++) {
			        nHand[i] = this.main.getChildNodes().item(i);
		        }
		    } catch(Exception e) {
		    	StackTraceElement[] f = Thread.currentThread().getStackTrace();;
		    	e.setStackTrace(f);
		    	
		    	
		    	ErrorWriter(e.getMessage() + " on line " + f[2].getLineNumber());
		    }			
		}
		
	}	
	
	/**
	 * Counts the ammount of children in a node
	 * @return nodes childnode length
	*/
	public int ChildNodeLength(Node n) {
		return(((NodeList) n).getLength());
	}
	/**
	 * Counts the ammount of nodes with a specific name in a nodelist
	 * if no node is specified then it chooses the main xml file
	 * @return ammount of childnodes
	 */
	public int NodeCounter(Node f, String name) {
		int i1 = 0;
		if(f == null) {
			f = (Node) this.main.getChildNodes();
		}
		for(int i = 0; i < NodeLength(); i++) {
			if(((NodeList) f).item(i).getNodeName().equals(name)) {			
				i1++;
			}
		}
		return(i1);
	}
	
	/**
	 * @return total childnode length of org file
	 */
	public int NodeLength() {
		return(Integer.parseInt(param[0]));
	}
	/**
	 * Searches for specific node that is one of the node objects and returns the first one
	 * @return node with specified name
	 */
	public Node NRList(String name) {
		for(int i = 0; i < NodeLength(); i++) {
			if(this.nHand[i].getNodeName().equals(name)) {
				return(this.nHand[i]);
			}
		}
		
		return(null);
		
	}
	/**
	 * Returns node with specific name in a new node array with a specific place, noted by Item
	 * @return node;
	 */
	public Node NR(String name, int Item) {
		int i1 = 0;
		Node[] tempNode = new Node[NodeLength()];
		for(int i = 0; i < NodeLength(); i++) {
			if(this.nHand[i].getNodeName().equals(name)) {
				tempNode[i1] = this.nHand[i];
				i1++;
			}
		}
		if(tempNode[Item] == null) {
			ErrorWriter("Wrong Item Selected");
			return(null);
		} else {
			return(tempNode[Item]);
		}
		
	}
	public Node getNodeByName(Node n, String name) {
		for(int i = 0; i < ChildNodeLength(n); i++) {
			if(n.getChildNodes().item(i).getNodeName().equals(name)) {
				return(n.getChildNodes().item(i));
			}
		}
		ErrorWriter("No Node with name " + name + " was found");
		return(null);
	}
	/**
	 * Get the node text content of a specific node with specific name
	 * @return Node string
	 */
	public String getNodeText(String name, Node n) {
		for(int i = 0; i < ChildNodeLength(n); i++) {
			Node sex = n.getChildNodes().item(i);
			
			if(sex.getNodeName().equals(name)) {
				//Game.lg.Logbuilder("XmlHandler: Node with name " + sex.getNodeName() + " has text content " + sex.getTextContent());
				//Game.lg.Logwriter();
				return(sex.getTextContent());
			}
		}
        ErrorWriter("No TextNode With the name " + name + " was found");
        return(null);
	}
	/**
	 * Error writer that writes an error log according to the error, in a log file.
	 */
	public void ErrorWriter(String e) {
		StackTraceElement[] STE = Thread.currentThread().getStackTrace();
		StackTraceElement f = STE[2] ;
		Game.lg.Logbuilder("XmlHandler: Error in Function " + f.getMethodName() +  ", " + e );
		Game.lg.Logwriter();
		
	}
}
