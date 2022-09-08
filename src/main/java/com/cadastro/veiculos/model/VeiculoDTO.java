package com.cadastro.veiculos.model;

import java.time.LocalDateTime;

public class VeiculoDTO {

    private String veiculo;
    private String marca;
    private Integer ano;
    private String descricao;
    private String cor;
    private boolean vendido;
    private LocalDateTime created;
    private LocalDateTime updated;

    public VeiculoDTO() {
    }

    public VeiculoDTO(String veiculo, String marca, Integer ano, String descricao, String cor, boolean vendido, LocalDateTime created, LocalDateTime updated) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.cor = cor;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                ", veiculo='" + veiculo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", descricao='" + descricao + '\'' +
                ", cor='" + cor + '\'' +
                ", vendido=" + vendido +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
