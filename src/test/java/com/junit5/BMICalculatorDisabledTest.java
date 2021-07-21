package com.junit5;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorDisabledTest {
	
	@Test
	void shouldReturnTrueWhenDietRecommended() {
		// given
		double weight = 89.0;
		double height = 1.72;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}
	/*
	 * @Disabled makes a method to skip
	 */

	@Test
	@Disabled
	void shouldReturnFalseWhenDietNotRecommended() {
		// given
		double weight = 50.0;
		double height = 1.92;

		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertFalse(recommended);
	}

	@Test
	@Disabled
	void shouldThrowArithmeticExceptionWhenHeightIsZero() {
		// given
		double weight = 50.0;
		double height = 0;

		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(Exception.class, executable);
	}
	
	@Test
	void shouldReturnNullWhenCoderListIsEmpty() {
		// given
		List<Coder> coders = new ArrayList<Coder>();

		// when
		Coder coderWithWorsrBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coderWithWorsrBMI);

	}
	
	@Test
	void shouldReturnCorrectBMIScoreArrayWhenCoderListIsNonEMpty() {
		//given
		List<Coder> coders = Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new Coder(1.82, 64.7));
		double[] expected = {18.52,29.59,19.53};
		
		//when
		double[] bmiScores = BMICalculator.getBMIScores(coders);
		
		//then
		assertArrayEquals(expected, bmiScores);
	}
	
}
