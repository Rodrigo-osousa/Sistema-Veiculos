package com.cadastro.veiculos.controller;


import com.cadastro.veiculos.exceptionPersonalizada.VeiculoException;
import com.cadastro.veiculos.model.Veiculo;
import com.cadastro.veiculos.model.VeiculoDTO;
import com.cadastro.veiculos.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Veiculo novoVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        return veiculoService.novoVeiculo(veiculoDTO);
    }


    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Veiculo updateVeiculo(@RequestBody Veiculo veiculo) throws VeiculoException {
        return veiculoService.updateVeiculo(veiculo);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Veiculo> findVeiculosById(@PathVariable long id) throws VeiculoException {
        return veiculoService.findVeiculoById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable int id) throws VeiculoException {
        veiculoService.deleteVeiculo(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public List<Veiculo> findAll(@RequestParam(required = false) String marca, @RequestParam(required = false) Integer ano, @RequestParam(required = false) String cor) throws VeiculoException {
        return veiculoService.findVeiculos(marca, ano, cor);
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public Veiculo parcialUpdateVeiculo(@PathVariable Long id, @RequestBody Map<Object, Object> objectMap) throws VeiculoException {
        return veiculoService.updateParcialVeiculo(id, objectMap);
    }

}
