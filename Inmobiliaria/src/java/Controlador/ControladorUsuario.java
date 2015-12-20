/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.UsuarioDAO;
import Persistencia.Modelo.Usuario;
import Soporte.Encriptar;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author angelo
 */
public class ControladorUsuario {

    private final UsuarioDAO usuarioDAO;

    public ControladorUsuario() {
        usuarioDAO = new UsuarioDAO();
    }

    public void eliminar(int idUsuario) {
        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario);
        usuarioDAO.eliminar(u);
    }

    public boolean iniciarSesion(String username, String password) {
        Usuario usuario = usuarioDAO.buscar(username);
        String claveMD5 = Encriptar.encriptaEnMD5(password);
        return usuario != null && usuario.getNick().equals(username) && usuario.getClave().equals(claveMD5);
    }

    public Usuario getUsuarioByNick(String username) {
        return usuarioDAO.buscar(username);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioDAO.buscarEmail(email);
    }

    public void actualizar(Usuario u) {
        u.setApellido(WordUtils.capitalize(u.getNombre()));
        u.setNombre(WordUtils.capitalize(u.getNombre()));
        usuarioDAO.actualizar(u);
    }

    public String crearCodigoRecuperacion(Usuario u) {
        SecureRandom random = new SecureRandom();
        String codigo = new BigInteger(130, random).toString(32);
        u.setCodigo(codigo);
        Date d = new Date();
        u.setFechaCreacionCodigo(d);
        usuarioDAO.actualizar(u);
        return codigo;
    }
}
