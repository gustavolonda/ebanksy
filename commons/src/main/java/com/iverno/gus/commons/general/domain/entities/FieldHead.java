package com.iverno.gus.commons.general.domain.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldHead {
	
	private int num ; //Init 1  
	private String title;

}

