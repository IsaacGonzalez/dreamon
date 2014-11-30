package dream.aplicacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dream.dao.UsuarioDAO;
import Entidad.Usuario;

@ManagedBean
@ViewScoped

public class AdministrarUsuario {

	private String correo;
	private String password;
	private String nombre;
	private String about;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public void crearUsuario(){
		UsuarioDAO udao=new UsuarioDAO();
		Usuario u = new Usuario();
		u.setNombres(nombre);
		u.setCorreo(correo);
		u.setPassword(password);

		udao.insertar(u);
	}

	public String doActionIniciarSesion(){
		UsuarioDAO udao=new UsuarioDAO();
		if(udao.verificarUsuario(correo, password)){
			return "resultados.jsf";
		}		
		else{
			return null;
		}	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}