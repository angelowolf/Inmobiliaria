/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorImagenPropiedad;
import Persistencia.Modelo.ImagenPropiedad;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Angelo
 */
public class ImagenAction extends ActionSupport {

    private final int BUFFER_LENGTH = 4096;
    private static final String STORAGE_PATH = System.getenv("OPENSHIFT_DATA_DIR") == null ? "D:/imagenes/tmp/" : System.getenv("OPENSHIFT_DATA_DIR");
    ControladorImagenPropiedad controladorImagenPropiedad = new ControladorImagenPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();

    @Override
    public String execute() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        File file = null;
        String sss = "gil";
        try {
            int idImagen = Integer.parseInt(request.getParameter("idImagen"));
            ImagenPropiedad id = controladorImagenPropiedad.getOne(idImagen);
            String path = id.getRuta();
            sesion.put("ruta_1", path);
//            String pathToWeb = "D:/imagenes/tmp/ImagenPropiedad/654/123.jpg";
            response.setContentType("image/jpeg,Image/jpg,Image/png");
            file = new File(path);
            sss = " " + file.canRead() + " " + file.canExecute() + " " + file.getAbsolutePath() + " " + file.length();
            response.setContentLength((int) file.length());

            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[BUFFER_LENGTH];
            int count = 0;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
                out.flush();
            }
            out.close();
            in.close();

        } catch (Exception e) {
            sesion.put("exito", e.toString());
            e.printStackTrace();
        }

        sesion.put("exito", sss);
        return null;
    }
}
