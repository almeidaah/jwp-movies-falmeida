package br.com.voffice.java.jwptf02.week1;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public  class Movie {
	private Long id;
	private String title;
	private LocalDate releasedDate;
	private Double budget;
	private String poster;
	private Integer rating;
	private String category;
	private Boolean result;
}
