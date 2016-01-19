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

    /**
     * Elimina un usuario
     *
     * @param idUsuario EL id del usuario.
     */
    public void eliminar(int idUsuario) {
        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario);
        usuarioDAO.eliminar(u);
    }

    /**
     * Verifica si los datos ingresados pertenecen a un usuario registrado
     *
     * @param username El nick.
     * @param password La clave.
     * @return True si existe.
     */
    public boolean iniciarSesion(String username, String password) {
        Usuario usuario = usuarioDAO.buscar(username);
        String claveMD5 = Encriptar.encriptaEnMD5(password);
        return usuario != null && usuario.getNick().equals(username) && usuario.getClave().equals(claveMD5);
    }

    /**
     * Busca un usuario por su nick.
     *
     * @param username El nick.
     * @return EL usuario.
     */
    public Usuario getUsuarioByNick(String username) {
        return usuarioDAO.buscar(username);
    }

    /**
     * Busca un usuario por su email.
     *
     * @param email El email.
     * @return El usuario.
     */
    public Usuario getUsuarioByEmail(String email) {
        return usuarioDAO.buscarEmail(email);
    }

    /**
     * Actualiza los datos del usuario. Encripta la clave en MD5.
     *
     * @param u El usuario.
     */
    public void actualizar(Usuario u) {
        u.setApellido(WordUtils.capitalize(u.getApellido()));
        u.setNombre(WordUtils.capitalize(u.getNombre()));
        u.setClave(Encriptar.encriptaEnMD5(u.getClave()));
        usuarioDAO.actualizar(u);
    }

    /**
     * Actualiza los datos del usuario sin encriptar.
     *
     * @param u El usuario.
     */
    public void recuperarClave(Usuario u) {
        usuarioDAO.actualizar(u);
    }

    /**
     * Crea un codigo de recuperacion para el usuario, actualiza el usuario en
     * la BD con el codigo creado y la fecha de la creacion.
     *
     * @param u El usuario.
     * @return El codigo creado.
     */
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
