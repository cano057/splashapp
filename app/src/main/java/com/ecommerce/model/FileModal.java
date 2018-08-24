package com.ecommerce.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileModal {

	private long id;

	private Product product;
	
	private String fileName;
	
	private long fileSize;
	
	private String fileType;

	private LocalDateTime createDateTime;
}
