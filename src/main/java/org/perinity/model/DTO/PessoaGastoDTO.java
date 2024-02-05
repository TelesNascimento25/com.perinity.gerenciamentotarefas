package org.perinity.model.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaGastoDTO {
	private String nome;
	private LocalDate periodoInicio;
	private LocalDate periodoFim;
	private Double mediaHoras;
}
