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

	
	private int usuario_id;
	
	private int lista_id;
	
	private String lista_nombre;
	
	private String lista_descripcion;
	
	private boolean publica;
	
	
	private int idea_id;
	
	private String idea_nombre;
	
	private boolean idea_cumplida;
	
	
	private int hashtag_id;
	
	private String hashtag_nombre;
		
	private UsuarioDAO usuarioDAO;
	private ListaIdeaDAO listaDAO;
	private IdeaDAO ideaDAO;
	private HashtagDAO hashtagDAO;
	
	
	private Usuario usuario;
	
	private ListaIdea lista;
	
	private List<ListaIdea> listas;
	
	private Idea idea;
	
	private List<Idea> ideas;
	
	private Hashtag hashtag;
	
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

	public int getUsuarioId() {
		return usuario_id;
	}

	public void setUsuarioId(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getListaId() {
		return lista_id;
	}

	public void setListaId(int lista_id) {
		this.lista_id = lista_id;
	}

	public String getListaNombre() {
		return lista_nombre;
	}

	public void setListaNombre(String lista_nombre) {
		this.lista_nombre = lista_nombre;
	}

	public String getListaDescripcion() {
		return lista_descripcion;
	}

	public void setListaDescripcion(String lista_descripcion) {
		this.lista_descripcion = lista_descripcion;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public int getIdeaId() {
		return idea_id;
	}

	public void setIdeaId(int idea_id) {
		this.idea_id = idea_id;
	}

	public String getIdeaNombre() {
		return idea_nombre;
	}

	public void setIdeaNombre(String idea_nombre) {
		this.idea_nombre = idea_nombre;
	}

	public boolean isIdeaCumplida() {
		return idea_cumplida;
	}

	public void setIdeaCumplida(boolean idea_cumplida) {
		this.idea_cumplida = idea_cumplida;
	}

	public int getHashtagId() {
		return hashtag_id;
	}

	public void setHashtagId(int hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public String getHashtagNombre() {
		return hashtag_nombre;
	}

	public void setHashtagNombre(String hashtag_nombre) {
		this.hashtag_nombre = hashtag_nombre;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public ListaIdeaDAO getListaDAO() {
		return listaDAO;
	}

	public void setListaDAO(ListaIdeaDAO listaDAO) {
		this.listaDAO = listaDAO;
	}

	public IdeaDAO getIdeaDAO() {
		return ideaDAO;
	}

	public void setIdeaDAO(IdeaDAO ideaDAO) {
		this.ideaDAO = ideaDAO;
	}

	public HashtagDAO getHashtagDAO() {
		return hashtagDAO;
	}

	public void setHashtagDAO(HashtagDAO hashtagDAO) {
		this.hashtagDAO = hashtagDAO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ListaIdea getLista() {
		return lista;
	}

	public void setLista(ListaIdea lista) {
		this.lista = lista;
	}

	public List<ListaIdea> getListas() {
		return listas;
	}

	public void setListas(List<ListaIdea> listas) {
		this.listas = listas;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
		
}