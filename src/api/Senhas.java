package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Senhas {

	private static String sArquivo = "C:\\Users\\Maurelio\\workspace\\GeraSenhaWS\\src\\api\\senhas.xml";


	public static void salveConteudo(String conteudo) throws IOException {
        FileWriter file = new FileWriter(sArquivo);
        try {
            file.write(conteudo);
            System.out.println("Arquivo gerado com sucesso");
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
            file.flush();
            file.close();
        }
		
	}
	
	public static void criarArquivoXML() {

		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            
            Element mainRootElement = doc.createElement("senhas");
            Element senhaPChamada = doc.createElement("senhaPChamada");
            Element senhaNChamada = doc.createElement("senhaNChamada");
            Element senhaP = doc.createElement("senhaP");
            Element senhaN = doc.createElement("senhaN");
            Element senhaAtual = doc.createElement("senhaAtual");
            
            senhaPChamada.setTextContent("0");
            senhaNChamada.setTextContent("0");
            senhaP.setTextContent("0");
            senhaN.setTextContent("0");
            senhaAtual.setTextContent("NDD");
            
            mainRootElement.appendChild(senhaPChamada);
            mainRootElement.appendChild(senhaNChamada);
            mainRootElement.appendChild(senhaP);
            mainRootElement.appendChild(senhaN);
            mainRootElement.appendChild(senhaAtual);
            
            doc.appendChild(mainRootElement);

            salveConteudo(converter(doc));
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	private static String converter(Document document) throws TransformerException {
		Transformer transformer =
				 TransformerFactory.newInstance().newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();

		System.out.append(xmlString);

		return xmlString;
	} 
	
	public static String zerarSenhas() {
		criarArquivoXML();
		return "<b><n>Senhas zeradas com sucesso!</n></b>";
	}


	public static String retorneSenhas(boolean somenteAtual) {
		String sRetorno = "";

		try {
			File fXMLFile = new File(sArquivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXMLFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("senhas");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				//if (!somenteAtual) {
				sRetorno = "<b>";
				//}
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (!somenteAtual) {
						sRetorno = sRetorno + " <p> " + "Senha Chamada Preferencial : " + eElement.getElementsByTagName("senhaPChamada").item(0).getTextContent() + "</p>";
						sRetorno = sRetorno + " <p> " + "Senha Chamada Normal : " + eElement.getElementsByTagName("senhaNChamada").item(0).getTextContent() + "</p>";
						sRetorno = sRetorno + " <p> " + "Senha Atual Preferencial : " + eElement.getElementsByTagName("senhaP").item(0).getTextContent()+ "</p>";
						sRetorno = sRetorno + " <p> " + "Senha Atual Normal : " + eElement.getElementsByTagName("senhaN").item(0).getTextContent()+ "</p>";
						sRetorno = sRetorno + " <p> " + "Ultima senha chamada : " + eElement.getElementsByTagName("senhaAtual").item(0).getTextContent()+ "</p>";
					} else
					{
						sRetorno = eElement.getElementsByTagName("senhaAtual").item(0).getTextContent();
					}
					sRetorno = sRetorno + "</b>"; 
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sRetorno;
	}
	
	public static String geraSenha(int nTipo) {

		String sResultado = "";
		int nuSenhaAtual = 0;
		String sVariavel = "";

		try {
			File fXMLFile = new File(sArquivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXMLFile);

			NodeList nList = doc.getElementsByTagName("senhas");

			DecimalFormat df = new DecimalFormat("00000");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
					Element eElement = (Element) nNode;
					if (nTipo == 1) {
						sResultado = "P";
						sVariavel = "senhaPChamada";
					} else if (nTipo == 2) {
						sResultado = "N";
						sVariavel = "senhaNChamada";
					} else if (nTipo == 3) {
						sResultado = "P";
						sVariavel = "senhaP";
					} else {
						sResultado = "N";
						sVariavel = "senhaN";
					}
					nuSenhaAtual = Integer.parseInt(eElement.getElementsByTagName(sVariavel).item(0).getTextContent());
					nuSenhaAtual++;
					eElement.getElementsByTagName(sVariavel).item(0).setTextContent(Integer.toString(nuSenhaAtual));
					sResultado = sResultado + df.format(nuSenhaAtual);
					if (nTipo == 1) {
						eElement.getElementsByTagName("senhaAtual").item(0).setTextContent('P' + df.format(nuSenhaAtual));	
					} else if (nTipo == 2) {
						eElement.getElementsByTagName("senhaAtual").item(0).setTextContent('N' + df.format(nuSenhaAtual));
					}
				}
			}
			salveConteudo(converter(doc));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Senha: " + sResultado;
	}

	public static int retorneUltimaSenha(int nTipo) {
		int nuSenhaAtual = 0;
		String sVariavel = "";
		try {
			File fXMLFile = new File(sArquivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fXMLFile);

			NodeList nList = doc.getElementsByTagName("senhas");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (nTipo == 1) {
						sVariavel = "senhaPChamada";
					} else if (nTipo == 2) {
						sVariavel = "senhaNChamada";
					} else if (nTipo == 3) {
						sVariavel = "senhaP";
					} else {
						sVariavel = "senhaN";
					}
					nuSenhaAtual = Integer.parseInt(eElement.getElementsByTagName(sVariavel).item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nuSenhaAtual;

	}

}
