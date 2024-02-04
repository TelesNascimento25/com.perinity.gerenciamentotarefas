package org.perinity.model.DTO;

import java.time.LocalDate;

public class PessoaGastoDTO {
    public String nome;
    public LocalDate periodoInicio;
    public LocalDate periodoFim;
    public Double mediaHoras;

    public PessoaGastoDTO(String nome, LocalDate periodoInicio, LocalDate periodoFim, Double mediaHoras) {
        this.nome = nome;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.mediaHoras = mediaHoras;
    }
}
