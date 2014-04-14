/*
 * Classe Controller
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import readxml.MainReadPropertiesSAX;
import readxml.ReadPropertiesSaxException;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class Controller extends HttpServlet {

    //HashMap para Controller Interface
    private Map hashMapCI;
    //HashMap para Update Interface
    private Map hashMapUI;
    // Variável para controle do tipo de Interface (CI ou UI)
    private int tipo = 0;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            super.init(servletConfig);
            //Instanciando Objetos
            hashMapCI = new HashMap();
            hashMapUI = new HashMap();
            //Lendo Arquivos
            MainReadPropertiesSAX reader = new MainReadPropertiesSAX();
            reader.readXML(getServletContext().getRealPath("/") + "CONFIG-INF/framework-configCI.xml", this.hashMapCI);
            reader.readXML(getServletContext().getRealPath("/") + "CONFIG-INF/framework-configUI.xml", this.hashMapUI);
        } catch (ReadPropertiesSaxException ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String name = null;
        String bussinesClass = null;
        String jspName = null;
        name = request.getParameter("name");

        // Recupera a regra de negócio
        try {
            if (name == null) {
                throw new ControllerException("Classe de serviço inválida");
            } else {
                bussinesClass = (String) hashMapCI.get(name);
                tipo = 1; // CI
                if (bussinesClass == null) {
                    bussinesClass = (String) hashMapUI.get(name);
                    tipo = 2; // UI
                }
                if (bussinesClass == null) {
                    throw new ControllerException("Classe de serviço não econtrada");
                }
            }
            // Verifica o tipo de interface (CI ou UI)
            if (tipo == 1) {
                ControllerInterface ci = (ControllerInterface) Class.forName(bussinesClass).newInstance();
                jspName = ci.call(request, response);
                RequestDispatcher rd = request.getRequestDispatcher("/" + jspName);
                rd.forward(request, response);
            } else {
                UpdateInterface ci = (UpdateInterface) Class.forName(bussinesClass).newInstance();
                ci.executa(request, response);
            }
        } catch (ClassNotFoundException exc) {
            request.setAttribute("erro", "Classe de serviço " + bussinesClass + " não encontrada!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            exc.printStackTrace();
            dispatcher.forward(request, response);
        } catch (InstantiationException exc) {
            request.setAttribute("erro", "Problemas em instanciar a classe de serviço" + bussinesClass + "!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            exc.printStackTrace();
            dispatcher.forward(request, response);
        } catch (IllegalAccessException exc) {
            request.setAttribute("erro", "Acesso ilegal a classe" + bussinesClass + "!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            exc.printStackTrace();
            dispatcher.forward(request, response);
        } catch (ControllerException exc) {
            request.setAttribute("erro", exc.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador de Requisições!";
    }
}
