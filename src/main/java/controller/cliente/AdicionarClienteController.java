/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.cliente;

import dao.ClienteDAO;
import dao.CnhDAO;
import exceptions.CNHRegistroException;
import exceptions.CPFException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Cnh;
import view.TelasCliente.AdicionarClienteView;

/**
 *
 * @author Filipe Zulian
 */
public class AdicionarClienteController {

    public AdicionarClienteView acv;

    public AdicionarClienteController(AdicionarClienteView acv) {
        this.acv = acv;
        adicionarAcaoBotao();
        exibirTela();

    }

    public void adicionarAcaoBotao() {
        acv.adicionarAcaoBotaoAdicionar(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = acv.getName();
                String telefone = acv.getTelefone();
                String cpf = acv.getCPF();
                String registro = acv.getRegistro();
                int idade = acv.getIdade();
                int anoExp = acv.getAnoExp();

                try {
                    Cliente cliente = criarCliente(nome, telefone, cpf, idade);

                    Cnh cnh = criarCnh(registro, anoExp);

                    ClienteDAO.salvarCliente(cliente);
                    CnhDAO.salvarCnh(cnh);

                    cliente.setCnh(cnh);

                    JOptionPane.showMessageDialog(null, "Cliente Adicionado");

                } catch (CPFException | CNHRegistroException a) {
                    JOptionPane.showMessageDialog(null, a.getMessage());

                }

            }
        });
    }
    
     public Cliente criarCliente(String nome, String telefone, String cpf, int idade) throws CPFException {
        Cliente c = new Cliente(nome, telefone, cpf, idade);
        
        return c;
    }

    public Cnh criarCnh(String registro, int anoExp) throws CNHRegistroException {
        Cnh cnh = new Cnh(acv.getRegistro(), acv.getAnoExp());

        return cnh;
    }

    public void exibirTela() {
        this.acv.exibir();
    }
}
