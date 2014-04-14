/*
 * Classe ControllerInterface
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public interface ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response);
}
