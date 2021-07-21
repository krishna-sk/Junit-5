package com.junit5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorParameterizedTest {
	
	@ParameterizedTest
	@ValueSource(doubles = {89.0,95.0,110.0})
	void shouldReturnTrueWhenDietRecommended(Double coderWeight) {
		// given
		double weight = coderWeight;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}
	
	@ParameterizedTest(name= "weight={0}")
	@ValueSource(doubles = {89.0,95.0,110.0})
	void shouldReturnTrueWhenDietRecommendedWithLabel(Double coderWeights) {
		// given 
		double weight = coderWeights;
		double height = 1.72; 

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}
	
	@ParameterizedTest
	@CsvSource(value= {"89.0, 1.72","95.0, 1.75","110.0, 1.78"})
	void shouldReturnTrueWhenDietRecommendedTwoValues(Double coderWeight,Double coderHeight) {
		// given
		double weight = coderWeight;
		double height = coderHeight;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}
	
	@ParameterizedTest(name= "weight={0}, height={1}")
	@CsvSource(value= {"89.0, 1.72","95.0, 1.75","110.0, 1.78"})
	void shouldReturnTrueWhenDietRecommendedTwoValuesWithLabels(Double coderWeights,Double coderHeights) {
		// given
		double weight = coderWeights;
		double height = coderHeights;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}

	@ParameterizedTest(name= "weight={0}, height={1}")
	@CsvFileSource(resources="/diet-recommended-input-data.csv",numLinesToSkip=1)
	void shouldReturnTrueWhenDietRecommendedTwoValuesWithLabelsAndCsvFileSource(Double coderWeightz,Double coderHeightz) {
		// given
		double weight = coderWeightz;
		double height = coderHeightz;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}
	
	
	
	
	
	
	
	
	
}
