package com.junit5;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorAssumptionsTest {
	
	
	private String environment = "dev";

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
	void shouldReturnNullWhenCoderListIsEmpty() {
		// given
		List<Coder> coders = new ArrayList<Coder>();

		// when
		Coder coderWithWorsrBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coderWithWorsrBMI);

	}
	
	/*
	 * there will be some test cases which are specific to some conditions,
	 * they need to be run only when they met those conditions, in that case
	 * assumptions are useful.
	 * 
	 * some test are need to be run in production environment only then we use this
	 * assumptions based on environment
	 */
	
	@Test
	void shouldReturnCoderWithWorstBMIin1MsWhenCoderListHas10000Elements() {
		//given
		assumeTrue(this.environment.equals("prod"));
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
