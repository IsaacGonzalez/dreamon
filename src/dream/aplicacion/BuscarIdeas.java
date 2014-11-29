package dream.aplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private String hashtag;
	private List<Idea> ideas = new ArrayList<>();
	private IdeaDAO ideaDAO = new IdeaDAO();
	private IdeaLikeDAO ideaLikeDAO = new IdeaLikeDAO();
	private MensajeDAO mensajeDAO = new MensajeDAO();
	private int idea_id;
	
	private String descripcion;
	private Usuario usuario;
	
	public BuscarIdeas(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.consultarId(1);
	}
	
	public void doActionBuscarHashtag(){
		this.ideas = ideaDAO.getIdesPorHashtag(hashtag);
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
	
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}
	
	
	

}
