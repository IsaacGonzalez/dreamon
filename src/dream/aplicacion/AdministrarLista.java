package dream.aplicacion;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import dream.dao.HashtagDAO;
import dream.dao.IdeaDAO;
import dream.dao.ListaIdeaDAO;
import dream.dao.UsuarioDAO;
import Entidad.Hashtag;
import Entidad.Idea;
import Entidad.ListaIdea;
import Entidad.Usuario;

public class AdministrarLista {

	@Setter @Getter
	private int usuario_id;
	@Setter @Getter
	private int lista_id;
	@Setter @Getter
	private String lista_nombre;
	@Setter @Getter
	private String lista_descripcion;
	@Setter @Getter
	private boolean publica;
	
	@Setter @Getter
	private int idea_id;
	@Setter @Getter
	private String idea_nombre;
	@Setter @Getter
	private boolean idea_cumplida;
	
	@Setter @Getter
	private int hashtag_id;
	@Setter @Getter
	private String hashtag_nombre;
		
	private UsuarioDAO usuarioDAO;
	private ListaIdeaDAO listaDAO;
	private IdeaDAO ideaDAO;
	private HashtagDAO hashtagDAO;
	
	@Setter @Getter
	private Usuario usuario;
	@Setter @Getter
	private ListaIdea lista;
	@Setter @Getter
	private List<ListaIdea> listas;
	@Setter @Getter
	private Idea idea;
	@Setter @Getter
	private List<Idea> ideas;
	@Setter @Getter
	private Hashtag hashtag;
	@Setter @Getter
	private List<Hashtag> hashtags;
	
	public AdministrarLista(){
		usuarioDAO = new UsuarioDAO();
		listaDAO = new ListaIdeaDAO();
		ideaDAO = new IdeaDAO();
		hashtagDAO = new HashtagDAO();
		listas = new ArrayList<ListaIdea>();
		ideas = new ArrayList<Idea>();
		limpiar();
	}
	
	public void limpiar(){
		usuario_id = 1;
		lista_nombre = "";
		lista_descripcion = "";
		publica = true;
		idea_cumplida = false;
		idea_nombre = "";
		hashtag_nombre = "";		
	}
	
	public void doActionCrearLista(){
		usuario = new Usuario();		
		lista = new ListaIdea();
		
		usuario = usuarioDAO.consultarId(usuario_id);
		
		lista.setNombre(lista_nombre);
		lista.setDescripcion(lista_descripcion);
		lista.setUsuario(usuario);
		lista.setPublica(publica);
		
		listaDAO.insertar(lista);
		cargarListaDeUsuario();
		limpiar();
	}
	
	private void cargarListaDeUsuario(){
		listas = listaDAO.consultarPorUsuario(usuario_id);
	}
	
	public void doActionCargarListasUsuario(){
		cargarListaDeUsuario();
	}
	
	private void cargarListaDeIdeas(){
		ideas = ideaDAO.consultarPorLista(lista_id);
	}
	
	public void doActionCargarIdeas(){
		cargarListaDeIdeas();
	} 
	
	public void doActionAgregarIdea(){
		lista = listaDAO.consultarId(lista_id);
		idea = new Idea();
		idea.setDescripcion(idea_nombre);
		idea.setListaIdea(lista);
		idea.setCumplida(false);		
		ideaDAO.insertar(idea);
		
		cargarListaDeIdeas();
		limpiar();
	}
	
	public void doActionEditarIdea(){
		idea = ideaDAO.consultarId(idea_id);
		idea.setDescripcion(idea_nombre);
		idea.setListaIdea(lista);
		idea.setCumplida(idea_cumplida);
		ideaDAO.actualizar(idea);
		
		cargarListaDeIdeas();
		limpiar();
	}
	
	public void doActionEliminarIdea(){
		idea = ideaDAO.consultarId(idea_id);
		ideaDAO.eliminar(idea);
		
		cargarListaDeIdeas();
		limpiar();
	}
	
	public void doActionCumplirIdea(){
		idea = ideaDAO.consultarId(idea_id);
		idea.setCumplida(true);
		ideaDAO.actualizar(idea);
		
		cargarListaDeIdeas();
		limpiar();
	}
	
	public void doActionAgregarHashTag(){
		idea = ideaDAO.consultarId(idea_id);
		hashtag = new Hashtag();
		hashtag.setNombre(hashtag_nombre);
		hashtag.setIdea(idea);
		hashtagDAO.insertar(hashtag);
		
		cargarListaDeHash();
		limpiar();
	}
	
	public void doActionEliminarHashTag(){
		hashtag = hashtagDAO.consultarId(hashtag_id);
		hashtagDAO.eliminar(hashtag);
		
		cargarListaDeHash();
		limpiar();
	}
	
	private void cargarListaDeHash(){
		hashtags = hashtagDAO.consultarPorIdea(idea_id);
	}
	
	public void doActionCargarHash(){
		cargarListaDeHash();
	}
		
}