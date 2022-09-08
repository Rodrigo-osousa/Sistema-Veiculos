package com.cadastro.veiculos.repository;

import com.cadastro.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT c FROM Veiculo c WHERE (:marca is null or c.marca = :marca) and (:ano is null"
            + " or c.ano = :ano) and (:cor is null or c.cor = :cor)")
    List<Veiculo> findAllByMarcaAndAnoAndCor(@Param("marca") String marca, @Param("ano") Integer ano, @Param("cor") String cor);
}
