/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.cliente;

import controller.UpdateInterface;
import dao.cidadeDAO.CidadeDAO;
import dao.clienteDAO.ClienteDAO;
import dao.clienteXVeiculoDAO.ClienteXVeiculoDAO;
import dao.estadoDAO.EstadoDAO;
import dao.marcaDAO.MarcaDAO;
import dao.modeloDAO.ModeloDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cidade.Cidade;
import modelo.cliente.Cliente;
import modelo.clienteXVeiculo.ClienteXVeiculo;
import modelo.estado.Estado;
import modelo.marca.Marca;
import modelo.modelo.Modelo;
import modelo.veiculo.Veiculo;
import negocio.veiculo.CadastrarVeiculo;

/**
 *
 * @author ismael
 */
public class CadastrarCliente implements UpdateInterface{
    public void executa(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();
        }catch(IOException exc){
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, exc);
        }
        
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNome(request.getParameter("nome"));
        Estado estado = new Estado();
        EstadoDAO estadoDAO = new EstadoDAO();
        estado.setCodEstado(Integer.parseInt(request.getParameter("estado")));
        try{
            estado = estadoDAO.selectById(estado);
        }catch(SQLException ex){
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.setEstado(estado);
        
        Cidade cidade = new Cidade();
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidade.setCodCidade(Integer.parseInt(request.getParameter("cidade")));
        try{
            cidade = cidadeDAO.selectById(cidade);
        }catch(SQLException ex){
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.setCidade(cidade);
        cliente.setPeriodo(Integer.parseInt(request.getParameter("periodo")));
        
        cliente.setEndereco(request.getParameter("endereco"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCelular(request.getParameter("celular"));
        cliente.setStatus(true);
        
                
        /*//Lista contendo veiculo do cliente
        List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        listaVeiculos.add(veiculo);
        cliente.setListaVeiculo(listaVeiculos);*/
        
        //inserindo o cliente
        try {
            clienteDAO.insert(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(request.getParameter("placaVeiculo"));
        VeiculoDAO veoiculoDAO = new VeiculoDAO();
        try{
            veiculo = veoiculoDAO.selectById(veiculo);
        }catch(SQLException ex){
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Inserir relacionamento entre o cliente e o veiculo
        ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
        ClienteXVeiculoDAO clienteXVeiculoDAO = new ClienteXVeiculoDAO();
        
        clienteXVeiculo.setCliente(cliente);
        clienteXVeiculo.setVeiculo(veiculo);
        
        try{
            clienteXVeiculoDAO.insert(clienteXVeiculo);
        }catch(SQLException ex){
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fim inserir relacionamento entre o cliente e o veiculo
        out.print("{\"placaVeiculo\":\""+veiculo.getPlaca()+"\"}");
    }
}
