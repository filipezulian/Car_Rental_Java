/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.catalogo;

import dao.CatalogoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Catalogo;
import model.Veiculo;
import view.TelasCatalogo.PesquisarVeiculoView;

/**
 *
 * @author Filipe Zulian
 */
public class PesquisarVeiculoController {

    private PesquisarVeiculoView pvv;

    public PesquisarVeiculoController(PesquisarVeiculoView pvv) {
        this.pvv = pvv;
        exibirTela();
        adicionarAcaoBotao();
        popularComboBox();
        
    }
    
    private void popularComboBox() {
        List<Catalogo> catalogo = new ArrayList<>();

        for (Catalogo cat : CatalogoDAO.recuperarTodosCatalogos()) {
            catalogo.add(cat);
        }

        this.pvv.popularComboBox(catalogo);
    }


    public void exibirTela() {
        this.pvv.exibir();
    }

    public void adicionarAcaoBotao() {
        pvv.adicionarAcaoBotaoPesquisar(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = pvv.Placa();
                
                Catalogo cat = pvv.getTipo();
                
                Veiculo v = cat.buscarVeiculosCatalogo(placa);
                System.out.println(v);
                if (v != null) {
                   pvv.listagem(v.toString() + "\n");
                } else {
                    pvv.listagem("O catálogo não possui veículo com essa placa \n");
                }
            }

        });

    }

}


