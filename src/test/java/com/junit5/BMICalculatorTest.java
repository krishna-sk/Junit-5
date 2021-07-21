package com.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll Unit Test Method");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("afterAll Unit Test Method");
	}
 
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

	@Test
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
	void shouldReturnCoderWithWorstBMIWhenCoderListIsNonmpty() {
		// given
		List<Coder> coders = Arrays.asList(new Coder(1.80, 60.0), new Coder(1.82, 98.0), new Coder(1.82, 64.7));

		// when
		Coder coderWithWorsrBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		/*
		 * If we didn't use assertAll then we need to write two assertions separately if
		 * first one fails then the second one doesn't execute so we use assertAll.
		 * assertEquals(1.82, coderWithWorsrBMI.getHeight());
		 * assertEquals(98.0, coderWithWorsrBMI.getWeight());
		 */
		assertAll(
			() -> assertEquals(1.82, coderWithWorsrBMI.getHeight()),
			() -> assertEquals(98.0, coderWithWorsrBMI.getWeight())
				);
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
	
	
	@Test
	void shouldReturnCoderWithWorstBMIin1MsWhenCoderListHas10000Elements() {
		//given
		List<Coder> coders = new ArrayList<>();
		for(int i=0; i<10000; i++) {
			coders.add(new Coder(1.0+i,10.0+i));
		}
		
		//when
		Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
	
		//then
		assertTimeout(Duration.ofMillis(10), executable);
	}
	
	
	
	
	
	
	
}
