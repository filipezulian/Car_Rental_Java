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
import view.TelasCliente.ApagarClienteView;

/**
 *
 * @author Filipe Zulian
 */
public class ApagarClienteController {

    private ApagarClienteView acv;

    public ApagarClienteController(ApagarClienteView acv) {
        this.acv = acv;
        adicionarAcaoBotao();
        exibirTela();
        popularComboBox();
        
    }

       private void popularComboBox() {
        List<Cliente> clien = new ArrayList<>();

        for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
            clien.add(c);
        }

        this.acv.popularComboBox(clien);
    }
    
    public void exibirTela() {
        this.acv.exibir();
    }

    public void adicionarAcaoBotao() {
        acv.adicionarAcaoBotaoApagar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cli = acv.getCliente();

                if (cli == null) {
                    acv.exibirMensagem("Não ha nada para remover.");
                } else {
                    for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
                        if(c.getCpf().equals(cli.getCpf())){
                        ClienteDAO.removerCliente(c);
                        JOptionPane.showMessageDialog(null, "Cliente Apagado com sucesso.");

                        acv.limparcb();
                        popularComboBox();
                        return;
                        }
                        
                    }
                    
                }
               
            }
        });

    }
}
