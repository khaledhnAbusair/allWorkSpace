import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Test {

	public static void main(String[] args) {
		XMLReader reader = new XMLReader();
		reader.addHandler("node", new NodeHandler() {

			@Override
			public void process(StructuredNode node) {
				try {

					System.out.println(node.queryString("name"));
					System.out.println(node.queryValue("price").asDouble(0d));
					if (!node.isEmpty("resources/resource[@type='test']")) {
						System.out.println(node.queryString("resources/resource[@type='test']"));
					}
				} catch (XPathExpressionException e) {
				}
			}
		});
		try {
			reader.parse(new FileInputStream(Paths.get("/home/khaled/task/monitor/sample.xml").toFile()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
