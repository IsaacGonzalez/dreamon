package dream.aplicacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Inicio {

	public void doActionHello(){
		System.out.println("Ouh yeah!");
	}
}
