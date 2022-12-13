/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.aluguel;

import dao.AluguelDAO;
import dao.CatalogoDAO;
import dao.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Aluguel;
import model.Catalogo;
import model.Cliente;
import model.Veiculo;
import view.TelasAluguel.DevolucaoView;

/**
 *
 * @author Filipe Zulian
 */
public class DevolucaoController {

    private DevolucaoView dv;

    public DevolucaoController(DevolucaoView dv) {
        this.dv = dv;
        adicionarAcaoBotao();
        popularComboBox();
        exibirTela();
    }

    public void adicionarAcaoBotao() {
        dv.adicionarAcaoBotaoDevolver(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente c = (Cliente) dv.getCliente();
                int diasAtrasado = dv.getAtrasado();

                boolean devolveu = false;

                for (Catalogo cat : CatalogoDAO.recuperarTodosCatalogos()) {
                    for (Veiculo veic : cat.getVeiculos()) {
                        for (Aluguel alug : AluguelDAO.recuperarTodosAlugueis()) {
                            if (alug.getCliente().getCpf().equals(c.getCpf())) {
                                veic.setAlugado(false);
                                alug.setFinalizado(true);
                                AluguelDAO.removerAluguel(alug);
                                devolveu = true;
                                dv.listagem("O cliente " + c.getNome() + " deve pagar R$" + (veic.calcularMultaVeiculo(diasAtrasado) + alug.getPreco()) + "\n");
                                return;
                            }

                        }
                    }
                }
                System.out.println(devolveu);
                if (!devolveu) {
                    dv.listagem("O cliente " + c.getNome() + " não tem veículos para devolver" + "\n");
                }
            }
        });

    }

    public void exibirTela() {
        this.dv.exibir();
    }

    private void popularComboBox() {
        List<Cliente> clien = new ArrayList<>();

        for (Cliente c : ClienteDAO.recuperarTodosClientes()) {
            clien.add(c);
        }

        this.dv.popularComboBox(clien);
    }

}
