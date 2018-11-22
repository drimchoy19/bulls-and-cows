import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileOperator {

	private File file;

	public void createFile() {

		this.file = new File("Games.xml");

		try {

			if (file.exists() == false) {
				System.out.println("We had to make new file !");
				this.file.createNewFile();

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();

				Element rootElement = doc.createElement("game");
				doc.appendChild(rootElement);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("DayPlanner.xml"));

				transformer.transform(source, result);

				System.out.println("File saved!");
			} else {

				System.out.println("File is already created !");

			}
		} catch (IOException e) {
			System.out.println("Exception occured cant create file");
			e.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Error 123");
			ex.printStackTrace();
		}

	}

	public void writeFile(Game g) {

		try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("games");
				doc.appendChild(rootElement);

				Element game = doc.createElement("game");
				rootElement.appendChild(game);

				Element name = doc.createElement("playerName");
				name.appendChild(doc.createTextNode(g.getPlayerName()));
				game.appendChild(name);

				Element type = doc.createElement("startTime");
				type.appendChild(doc.createTextNode(g.getStartTime().getTime().toString()));
				game.appendChild(type);

				Element marker = doc.createElement("gameNums");
				marker.appendChild(doc.createTextNode(g.getGameNums()));
				game.appendChild(marker);

				Element attempts = doc.createElement("attempts");
				attempts.appendChild(
						doc.createTextNode(String.valueOf(g.getAttempts())));
				game.appendChild(attempts);

				Element triedNums = doc.createElement("triedNums");
				triedNums
						.appendChild(doc.createTextNode(g.choicesToString()));
				game.appendChild(triedNums);

				Element endTime = doc.createElement("endTime");
				endTime.appendChild(
						doc.createTextNode(g.getEndTime().getTime().toString()));
				game.appendChild(endTime);


			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(("Games.xml"));
			// result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (

		ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
