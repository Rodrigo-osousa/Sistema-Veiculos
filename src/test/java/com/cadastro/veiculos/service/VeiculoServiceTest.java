package com.cadastro.veiculos.service;

import com.cadastro.veiculos.exceptionPersonalizada.VeiculoException;
import com.cadastro.veiculos.model.Veiculo;
import com.cadastro.veiculos.model.VeiculoDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VeiculoServiceTest {

    @Autowired
    VeiculoService veiculoService;

    @BeforeAll
    void setUp() {
        VeiculoDTO veiculo = new VeiculoDTO();
        veiculo.setVeiculo("Uno");
        veiculo.setMarca("Fiat");
        veiculo.setDescricao("Movido a Gasolina");
        veiculo.setCor("Verde");
        veiculo.setAno(2018);
        veiculo.setVendido(false);
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());

        veiculoService.novoVeiculo(veiculo);
    }

    @Order(1)
    @Test
    void novoVeiculo() throws VeiculoException {
        VeiculoDTO veiculo = new VeiculoDTO();
        veiculo.setVeiculo("F8");
        veiculo.setMarca("Ferrari");
        veiculo.setDescricao("Muito Rápido!");
        veiculo.setCor("Vermelha");
        veiculo.setAno(2018);
        veiculo.setVendido(false);
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());

        veiculoService.novoVeiculo(veiculo);

        Veiculo confirmCreated = veiculoService.findVeiculoById(2).get();

        Assertions.assertEquals("Muito Rápido!", confirmCreated.getDescricao());
    }

    @Order(2)
    @Test
    void updateVeiculo() throws VeiculoException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(2);
        veiculo.setVeiculo("F8");
        veiculo.setMarca("Ferrari");
        veiculo.setDescricao("Muito Rápido!");
        veiculo.setCor("Amarela");
        veiculo.setAno(2018);
        veiculo.setVendido(false);
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());
        veiculoService.updateVeiculo(veiculo);

        Veiculo confirmUpdated = veiculoService.findVeiculoById(2).get();

        Assertions.assertEquals("Amarela", confirmUpdated.getCor());
    }

    @Order(3)
    @Test
    void findVeiculoById() throws VeiculoException {
        Assertions.assertTrue(veiculoService.findVeiculoById(1).isPresent());
    }

    @Order(4)
    @Test
    void findCarsWithoutParam() throws VeiculoException {
        Assertions.assertTrue(veiculoService.findVeiculos(null, null, null).size() == 2);
    }

    @Order(5)
    @Test
    void findCarsWithParam() throws VeiculoException {
        Assertions.assertTrue(veiculoService.findVeiculos("Fiat", 2018, "Verde").size() == 1);
    }

    @Order(6)
    @Test
    void updateParcialVeiculo() throws VeiculoException {
        Map<Object, Object> obj = new HashMap<Object, Object>();
        obj.put("cor", "Verde");
        long id = 2;

        veiculoService.updateParcialVeiculo(id, obj);
        Veiculo parcialUpdate = veiculoService.findVeiculoById(2).get();
        Assertions.assertEquals("Verde", parcialUpdate.getCor());
    }

    @Order(6)
    @Test
    void deleteVeiculo() throws VeiculoException {
        veiculoService.deleteVeiculo(1);
        Assertions.assertTrue(veiculoService.findVeiculos(null, null, null).size() == 1);
    }
}