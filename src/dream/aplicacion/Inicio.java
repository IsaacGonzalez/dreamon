
package dream.aplicacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Entidad.Usuario;

@ManagedBean
@ViewScoped
public class Inicio {

	private Usuario usuario;
	public void doActionHello(){
		System.out.println("Ouh yeah!");
	}
}
