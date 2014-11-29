package dream.aplicacion;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "pruebaBean", eager = true)
@ViewScoped
public class PruebaBean {
private String message = "Hello World!";
	
	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
