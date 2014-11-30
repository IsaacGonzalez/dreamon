package dream.aplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

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
@RequestScoped
public class BuscarIdeas  {

	private String hashtag;
	private String hashtag2;
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
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String param = requestMap.get("j_idt16");
		hashtag2="#"+param;
		doActionBuscarHashtag();
		
	}
	
	public void doActionBuscarHashtag(){
		this.ideas = ideaDAO.getIdesPorHashtag(hashtag2);
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

	public IdeaDAO getIdeaDAO() {
		return ideaDAO;
	}

	public void setIdeaDAO(IdeaDAO ideaDAO) {
		this.ideaDAO = ideaDAO;
	}

	public IdeaLikeDAO getIdeaLikeDAO() {
		return ideaLikeDAO;
	}

	public void setIdeaLikeDAO(IdeaLikeDAO ideaLikeDAO) {
		this.ideaLikeDAO = ideaLikeDAO;
	}

	public MensajeDAO getMensajeDAO() {
		return mensajeDAO;
	}

	public void setMensajeDAO(MensajeDAO mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}

	public int getIdeaId() {
		return idea_id;
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
