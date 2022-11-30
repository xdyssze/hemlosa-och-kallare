package xmlHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import logger.Logcreator;
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
	
	
	public int ChildNodeLength(Node n) {
		return(((NodeList) n).getLength());
	}
	
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
	
	
	public int NodeLength() {
		return(Integer.parseInt(param[0]));
	}
	public Node NRList(String name) {
		for(int i = 0; i < NodeLength(); i++) {
			if(this.nHand[i].getNodeName() == name) {
				return(this.nHand[i]);
			}
		}
		
		return(null);
		
	}
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
	public String getNodeText(String name, Node n) {
		for(int i = 0; i < ChildNodeLength(n); i++) {
			Node sex = n.getChildNodes().item(i);
			if(sex.getNodeName() == name) {
				return(sex.getTextContent());
			}
		}
        ErrorWriter("No TextNode With that name found");
        return(null);
	}
	
	public void ErrorWriter(String e) {
		StackTraceElement[] STE = Thread.currentThread().getStackTrace();
		StackTraceElement f = STE[2] ;
		Logcreator.Logbuilder("XmlHandler: Error in Function " + f.getMethodName() +  ", " + e );
		Logcreator.Logwriter();
		
	}
}
