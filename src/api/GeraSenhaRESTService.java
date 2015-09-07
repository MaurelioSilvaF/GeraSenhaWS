package api;

import java.io.InputStream;
import java.text.DecimalFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class GeraSenhaRESTService {

	@GET
	@Path("/verificar")
	@Produces(MediaType.TEXT_HTML)
	public Response verificarServico(InputStream incomingData) {
		String result = "GeraSenha iniciado com sucesso...";
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/senhasAtuais")
	@Produces(MediaType.TEXT_HTML)
	public Response senhasAtuais(InputStream incomingData) {
		String result;
		result = "\n" + Senhas.retorneSenhas(false);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/zerarSenhas")
	@Produces(MediaType.TEXT_HTML)
	public Response zerarSenhas(InputStream incomingData) {
		String result = Senhas.zerarSenhas();
		return Response.status(200).entity(result).build();
	} 
	
	@GET
	@Path("/geraSenhaP")
	@Produces(MediaType.TEXT_HTML)
	public Response gerarSenhaP(InputStream incomingData) {
		String result = Senhas.geraSenha(3);
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/geraSenhaN")
	@Produces(MediaType.TEXT_HTML)
	public Response gerarSenhaN(InputStream incomingData) {    
		String result = Senhas.geraSenha(4);
		return Response.status(200).entity(result).build(); 
	}

	@GET
	@Path("/proximaSenha")
	@Produces(MediaType.TEXT_HTML)
	public Response proximaSenha(InputStream incomingData) {
		// retornar próxima senha

		Boolean bMostrou = false;
		String result = "<h1>";
		DecimalFormat df = new DecimalFormat("00000"); 

		if (Senhas.retorneUltimaSenha(3)  > Senhas.retorneUltimaSenha(1)) {
			Senhas.geraSenha(1);
			
			result = result + "P" +   df.format(Senhas.retorneUltimaSenha(1));
			bMostrou = true;
		}
		if ((bMostrou == false) && (Senhas.retorneUltimaSenha(4) > Senhas.retorneUltimaSenha(2))) {
			Senhas.geraSenha(2);
			result = result + 'N' + df.format(Senhas.retorneUltimaSenha(2));
			bMostrou = true;
		}
		if (bMostrou == false) {
			result =  result + "NDD";
		}
		result =  result + "</h1>";

		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/senhaAtual")
	@Produces(MediaType.TEXT_HTML)
	public Response pegaSenhaAtual(InputStream incomingData) {
		String result;
		result = "<h1>" + Senhas.retorneSenhas(true) + "</h1>";
		return Response.status(200).entity(result).build();
	}
	
}
