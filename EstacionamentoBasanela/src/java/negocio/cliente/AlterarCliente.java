/*
 * Classe AlterarCliente
 */
package negocio.cliente;

import controller.ControllerInterface;
import dao.clienteDAO.ClienteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cidade.Cidade;
import modelo.cliente.Cliente;
import modelo.estado.Estado;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class AlterarCliente implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNome(request.getParameter("nome"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCelular(request.getParameter("celular"));
        Estado estado = new Estado();
        estado.setCodEstado(Integer.parseInt(request.getParameter("codEstado")));
        cliente.setEstado(estado);
        Cidade cidade = new Cidade();
        cidade.setCodCidade(Integer.parseInt(request.getParameter("codCidade")));
        cliente.setCidade(cidade);
        cliente.setEndereco(request.getParameter("endereco"));
        cliente.setPeriodo(Integer.parseInt(request.getParameter("codPeriodo")));
        try {
            clienteDAO.update(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(AlterarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Controller?name=ListarCliente";
    }
}
