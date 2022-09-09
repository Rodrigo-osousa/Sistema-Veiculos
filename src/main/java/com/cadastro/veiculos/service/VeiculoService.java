package com.cadastro.veiculos.service;

import com.cadastro.veiculos.controller.VeiculoController;
import com.cadastro.veiculos.exceptionPersonalizada.VeiculoException;
import com.cadastro.veiculos.model.Veiculo;
import com.cadastro.veiculos.model.VeiculoDTO;
import com.cadastro.veiculos.repository.VeiculoRepository;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;


    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo novoVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setVeiculo(veiculoDTO.getVeiculo());
        veiculo.setDescricao(veiculoDTO.getDescricao());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setVendido(veiculoDTO.isVendido());
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());

        veiculoRepository.save(veiculo);
        return veiculo;
    }

    public Veiculo updateVeiculo(Veiculo veiculo) throws VeiculoException {
        Optional<Veiculo> findVeiculo = veiculoRepository.findById(veiculo.getId());
        if (findVeiculo.isEmpty()) {
            throw new VeiculoException("Veiculo não cadastrado!");
        }
        Veiculo upVeiculo = new Veiculo();
        upVeiculo.setId(veiculo.getId());
        upVeiculo.setVeiculo(veiculo.getVeiculo());
        upVeiculo.setAno(veiculo.getAno());
        upVeiculo.setMarca(veiculo.getMarca());
        upVeiculo.setCreated(findVeiculo.get().getCreated());
        upVeiculo.setDescricao(veiculo.getDescricao());
        upVeiculo.setCor(veiculo.getCor());
        upVeiculo.setUpdated(LocalDateTime.now());

        return veiculoRepository.save(upVeiculo);
    }

    public Optional<Veiculo> findVeiculoById(long id) throws VeiculoException {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if (veiculo.isEmpty()) {
            throw new VeiculoException("Veículo não encontrado!");
        }

        veiculo.get().add(linkTo(methodOn(VeiculoController.class).findAll(null, null, null)).withRel("Lista de Veiculos"));
        return veiculo;
    }

    public void deleteVeiculo(long id) throws VeiculoException {
        if (findVeiculoById(id).isEmpty()) {
            throw new VeiculoException("Veículo não cadastrado!");
        }

        veiculoRepository.deleteById(id);
    }

    public List<Veiculo> findVeiculos(String marca, Integer ano, String cor) throws VeiculoException {
        List<Veiculo> veiculos = veiculoRepository.findAllByMarcaAndAnoAndCor(marca, ano, cor);
        if (veiculos.isEmpty()) {
            throw new VeiculoException("Nenhum Veiculo cadastrado!");
        } else {
            for (Veiculo veiculo : veiculos) {
                long id = veiculo.getId();
                Link selfLink = linkTo(VeiculoController.class).slash(id).withSelfRel();
                veiculo.add(selfLink);
            }
            return veiculos;
        }
    }

    public Veiculo updateParcialVeiculo(Long id, Map<Object, Object> objectMap) throws VeiculoException {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new VeiculoException("Veículo não Cadastrado!"));
        veiculo.setUpdated(LocalDateTime.now());
        objectMap.forEach((key, value) -> {
            Field field = org.springframework.util.ReflectionUtils.findField(Veiculo.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, veiculo, value);
        });
        return veiculoRepository.save(veiculo);
    }

}
