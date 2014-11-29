package dream.aplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

import org.apache.catalina.connector.Request;
import org.hibernate.criterion.LikeExpression;

import dream.dao.IdeaDAO;
import dream.dao.IdeaLikeDAO;
import dream.dao.IdeaSeguidorDAO;
import dream.dao.MensajeDAO;
import dream.dao.UsuarioDAO;
import Entidad.Hashtag;
import Entidad.Idea;
import Entidad.IdeaLike;
import Entidad.IdeaSeguidor;
import Entidad.Mensaje;
import Entidad.Usuario;


@ManagedBean
@ViewScoped
public class BuscarIdeas  {
	@Setter @Getter private String hashtag;
private List<Idea> ideas = new ArrayList<>();
	@Setter @Getter private IdeaDAO ideaDAO = new IdeaDAO();
	@Setter @Getter private IdeaLikeDAO ideaLikeDAO = new IdeaLikeDAO();
	@Setter @Getter private MensajeDAO mensajeDAO = new MensajeDAO();
	@Setter @Getter private int idea_id;
	
	@Setter @Getter private String descripcion;
	@Setter @Getter private Usuario usuario;
	
	
	
	
	public BuscarIdeas(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.consultarId(1);
		hashtag="#mole";
		doActionBuscarHashtag();
		
	}
	
	public void doActionBuscarHashtag(){
		this.setIdeas(ideaDAO.getIdesPorHashtag(hashtag));
	}
	
	public void doActionLikeIdea(){
		IdeaLike ideaLike=null;
		/*si hace YA NO ME GUSTA*/
		 ideaLike = ideaLikeDAO.getPorIdea(idea_id);	 
		 if(ideaLike!=null){
		 ideaLikeDAO.eliminar(ideaLike);
		 }else{
		 /*Si es ME GUSTA*/
		 ideaLike = new IdeaLike();
		 ideaLike.setIdea(ideaDAO.consultarId(idea_id));
		 ideaLike.setUsuario(usuario);
		 ideaLikeDAO.insertar(ideaLike);
		 }
	}
	
	public void doActionEnviarMensaje(){
		Mensaje mensaje = new Mensaje();
		mensaje.setDescripcion(descripcion);
		mensaje.setFechaEnvio(new Date());
		Idea idea = ideaDAO.consultarId(idea_id);
		if(idea!=null)
		mensaje.setUsuarioDestinatario(idea.getListaIdea().getUsuario());
		mensaje.setUsuarioRemitente(usuario);
		mensajeDAO.insertar(mensaje);
		
	}
	
	public void doActionSegirIdea(){
		IdeaSeguidor ideaSeguidor = new IdeaSeguidor();
		Idea idea = ideaDAO.consultarId(idea_id);
		if(idea!=null)
		ideaSeguidor.setIdea(idea);
		ideaSeguidor.setUsuario(usuario);
		IdeaSeguidorDAO ideaSeguidorDAO = new IdeaSeguidorDAO();
		ideaSeguidorDAO.insertar(ideaSeguidor);
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

	

}
