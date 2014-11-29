package test;

import java.util.Date;

import Entidad.Categoria;
import Entidad.Hashtag;
import Entidad.Idea;
import Entidad.IdeaLike;
import Entidad.IdeaSeguidor;
import Entidad.ListaIdea;
import Entidad.Mensaje;
import Entidad.Usuario;
import dream.dao.CategoriaDAO;
import dream.dao.HashtagDAO;
import dream.dao.IdeaDAO;
import dream.dao.IdeaLikeDAO;
import dream.dao.IdeaSeguidorDAO;
import dream.dao.ListaIdeaDAO;
import dream.dao.MensajeDAO;
import dream.dao.UsuarioDAO;

public class Main {

	public static void main(String args[]){
		UsuarioDAO udao = new UsuarioDAO();
		
		Usuario u = new Usuario();
		u.setNombres("Laraisa");
		u.setApellidos("Arenas");
		u.setCorreo("larenas@certuit.com");
		u.setPassword("1234");
		u.setUsuarioTwitter("larenas");
		
	//	udao.insertar(u);
		
		ListaIdeaDAO listadao = new ListaIdeaDAO();
		ListaIdea listaIdea = new ListaIdea();
		listaIdea.setDescripcion("loista");
		listaIdea.setNombre("betza");
		listaIdea.setPublica(true);
		listaIdea.setUsuario(udao.consultarId(1));
//		listadao.insertar(listaIdea);
		
		CategoriaDAO cdao = new CategoriaDAO();
		
		Categoria categoria = new Categoria();
		categoria.setId(8);
		categoria.setNombre("algo");
		
		IdeaDAO ideaDAO = new IdeaDAO();
		Idea idea = new Idea();
		//idea.setCategoria(categoria);
		idea.setDescripcion("COMER MOLE");
		idea.setListaIdea(listadao.consultarId(1));
		ideaDAO.insertar(idea);
		
		
//		HashtagDAO hashtagDAO = new HashtagDAO();
//		Hashtag hashtag = new Hashtag();
//		hashtag.setIdea(idea);
//		hashtag.setNombre("#yomevale");
//		hashtagDAO.insertar(hashtag);
//		
//		IdeaLikeDAO ideaLikeDAO = new IdeaLikeDAO();
//		IdeaLike ideaLike = new IdeaLike();
//		ideaLike.setIdea(idea);
//		ideaLike.setUsuario(u);
//		ideaLikeDAO.insertar(ideaLike);
//		
//		IdeaSeguidorDAO ideaSeguidorDAO = new IdeaSeguidorDAO();
//		IdeaSeguidor ideaSeguidor = new IdeaSeguidor();
//		ideaSeguidor.setIdea(idea);
//		ideaSeguidor.setUsuario(u);
//		ideaSeguidorDAO.insertar(ideaSeguidor);
//		
//		MensajeDAO mdao = new MensajeDAO();
//
//		Mensaje m = new Mensaje();
//		m.setDescripcion("Mensajín");
//		m.setFechaEnvio(new Date());
//		m.setUsuarioDestinatario(u);
//		m.setUsuarioRemitente(u);
//
//		mdao.insertar(m);
	}
}