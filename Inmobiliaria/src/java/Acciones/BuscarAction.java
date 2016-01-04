/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorPropiedad;
import Controlador.ControladorTipoPropiedad;
import Persistencia.Modelo.Propiedad;
import Persistencia.Modelo.TipoPropiedad;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angelo
 */
public class BuscarAction extends ActionSupport {

    private final ControladorPropiedad cp = new ControladorPropiedad();
    private final ControladorTipoPropiedad ctp = new ControladorTipoPropiedad();
    private List<Propiedad> propiedades = new ArrayList<Propiedad>();
    private List<TipoPropiedad> tiposPropiedades = new ArrayList<TipoPropiedad>();
    private TipoPropiedad tipoPropiedad = new TipoPropiedad();
    private int habitacion;
    private int bano;

    public String cargarPagina() {
        propiedades = cp.getTodos();

        for (Propiedad cadaPropiedad : propiedades) {
            try {
                cadaPropiedad.setImagenDefault(cadaPropiedad.getImagenes().get(0));

            } catch (IndexOutOfBoundsException e) {
                addActionError("Algunas propiedades no poseen imagenes.");
            }
        }

        tiposPropiedades = ctp.getTodos();
        return SUCCESS;
    }

    public String buscar() {
        propiedades = cp.buscar(tipoPropiedad.getIdTipoPropiedad(), habitacion, bano);
        for (Propiedad cadaPropiedad : propiedades) {
            try {
                cadaPropiedad.setImagenDefault(cadaPropiedad.getImagenes().get(0));

            } catch (IndexOutOfBoundsException e) {
                addActionError("Algunas propiedades no poseen imagenes.");
            }
        }
//        propiedades = cp.getTodos();
        tiposPropiedades = ctp.getTodos();
        return SUCCESS;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public List<TipoPropiedad> getTiposPropiedades() {
        return tiposPropiedades;
    }

    public void setTiposPropiedades(List<TipoPropiedad> tiposPropiedades) {
        this.tiposPropiedades = tiposPropiedades;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public int getBano() {
        return bano;
    }

    public void setBano(int bano) {
        this.bano = bano;
    }

}
