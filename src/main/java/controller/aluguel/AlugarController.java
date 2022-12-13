/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.aluguel;

import dao.AluguelDAO;
import dao.CatalogoDAO;
import dao.ClienteDAO;
import exceptions.AlugadoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aluguel;
import model.Catalogo;
import model.Cliente;
import model.Veiculo;
import model.modelAluguel.Diario;
import model.modelAluguel.Mensal;
import model.modelAluguel.Semanal;
import view.TelasAluguel.AlugarView;

/**
 *
 * @author Filipe Zulian
 */
public class AlugarController {

    private AlugarView av;

    public AlugarController(AlugarView av) {
        this.av = av;
        adicionarAcaoBotao();
        popularComboBoxVeiculo();
        popularComboBoxCliente();
        exibirTela();
    }

    public void adicionarAcaoBotao() {
        av.adicionarAcaoBotaoAlugar(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculo veic = (Veiculo) av.getVeiculo();
                Cliente c = (Cliente) av.getCliente();
                String tipoAluguel = (String) av.getTipoAlug();
                int tempoAluguel = av.recuperarTempo();

                Aluguel alug;

                try {
                    verificarSeAlugado(veic);
                    if (tipoAluguel.equalsIgnoreCase("diário")) {
                        alug = new Diario();
                    } else if (tipoAluguel.equalsIgnoreCase("semanal")) {
                        alug = new Semanal();
                    } else {
                        alug = new Mensal();
                    }

                    alug.setVeiculo(veic);
                    alug.setCliente(c);
                    alug.setFinalizado(false);
                    veic.setAlugado(true);

                    alug.calcularValorAluguel(tempoAluguel);

                    System.out.println(alug.getPreco());

                    AluguelDAO.salvarAluguel(alug);

                    JOptionPane.showMessageDialog(null, "Cliente " + c.getNome() + " alugou o veículo " + veic.getModelo() + " com sucesso.");

                } catch (AlugadoException a) {
                    JOptionPane.showMessageDialog(null, a.getMessage());
                }

            }
        });

    }

    private void popularComboBoxVeiculo() {
        List<Veiculo> veiculos = new ArrayList<>();

        for (Catalogo cat : CatalogoDAO.recuperarTodosCatalogos()) {

            for (Veiculo veic : cat.getVeiculos()) {
                veiculos.add(veic);
            }
        }

        this.av.popularComboBoxVeiculo(veiculos);
    }

    private void popularComboBoxCliente() {
        List<Cliente> clien = new ArrayList<>();

        for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
            clien.add(c);
        }
        av.popularComboBoxCliente(clien);
    }

//        Collections.sort(ClienteDAO.recuperarTodosClientes());
//
//        for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
//            cbCliente.addItem(c);
//        }
//    }
    public void exibirTela() {
        this.av.exibir();
    }

    private void verificarSeAlugado(Veiculo veic) throws AlugadoException {
        if (veic.isAlugado()) {
            throw new AlugadoException();
        }
    }

//    Veiculo veic = (Veiculo) cbVeiculo.getSelectedItem();
//        Cliente c = (Cliente) cbCliente.getSelectedItem();
//        String tipoAluguel = (String) cbTipoAluguel.getSelectedItem();
//        int tempoAluguel = Integer.parseInt(tfTempo.getText());
//
//        Aluguel alug;
//
//        try {
//            verificarSeAlugado(veic);
//            if (tipoAluguel.equalsIgnoreCase("diário")) {
//                alug = new Diario();
//            } else if (tipoAluguel.equalsIgnoreCase("semanal")) {
//                alug = new Semanal();
//            } else {
//                alug = new Mensal();
//            }
//
//            alug.setVeiculo(veic);
//            alug.setCliente(c);
//            alug.setFinalizado(false);
//            veic.setAlugado(true); 
//            
//            alug.calcularValorAluguel(tempoAluguel);      
//            
//            System.out.println(alug.getPreco());
//            
//            AluguelDAO.salvarAluguel(alug);
//            
//            JOptionPane.showMessageDialog(null, "Cliente " + c.getNome() + " alugou o veículo " + veic.getModelo() + " com sucesso.");
//            
//        } catch (AlugadoException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
}
