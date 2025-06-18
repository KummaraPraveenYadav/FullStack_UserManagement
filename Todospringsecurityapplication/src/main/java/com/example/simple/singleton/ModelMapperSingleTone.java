package com.example.simple.singleton;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperSingleTone {
	private static ModelMapper mapper = null;

	private ModelMapperSingleTone() {
	}

	static {
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public static ModelMapper modelMapper() {	
		return mapper;
	}
}
