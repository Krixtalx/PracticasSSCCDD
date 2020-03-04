
import java.util.Date;
import java.util.concurrent.Callable;

/**
 *
 * @author pedroj
 */
public class TareaImpresion implements Callable<Resultado> {
    
    private final String nombre;
    private final TrabajoImpresion trabajo;
    
    public TareaImpresion(String nombre, TrabajoImpresion trabajo) {
        this.nombre = nombre;
        this.trabajo = trabajo;
    }
    
    @Override
    public Resultado call() throws Exception {
        Date fecha = trabajo.impresion();
        return new Resultado(nombre, trabajo, fecha);
    }
    
}
