/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.cliente;

import dao.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import view.TelasCliente.EditarClienteView;

/**
 *
 * @author Filipe Zulian
 */
public class EditarClienteController {
    
    private EditarClienteView ecc;

    public EditarClienteController(EditarClienteView ecc) {
        this.ecc = ecc;
        adicionarAcaoBotao();
        exibirTela();
        popularComboBox();
        
    }
    
    public void exibirTela() {
        this.ecc.exibir();
    }
    
    public void adicionarAcaoBotao() {
        ecc.adicionarAcaoBotaoAtualizar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Cliente c = ecc.getCliente();
        String nome = ecc.getNome();
        String telefone = c.getTelefone();
        int dataExp = c.getCnh().getAnoExp();
        
        atualizarDados(c,dataExp, nome, telefone);
        
        popularComboBox();
        
        ecc.limparTela();
        
                        
                    }
        });

    }
    
    private void popularComboBox() {
        List<Cliente> clien = new ArrayList<>();

        for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
            clien.add(c);
        }

        this.ecc.popularComboBox(clien);
    }
    
     private void atualizarDados(Cliente c, int dataExp, String nome, String telefone) {
        if(!ecc.getNome().isBlank()) {
            c.setNome(ecc.getNome());
        } else {
            c.setNome(nome);
        }
        
        if(!ecc.getTelefone().isBlank()) {
            c.setTelefone(ecc.getTelefone());
        } else {
            c.setTelefone(telefone);
        }
            c.getCnh().setAnoExp(dataExp);
        
        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
    }
}
