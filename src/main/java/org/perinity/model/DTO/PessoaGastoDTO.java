package org.perinity.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaGastoDTO {
	public String nome;
	public LocalDate periodoInicio;
	public LocalDate periodoFim;
	public Double mediaHoras;
}
