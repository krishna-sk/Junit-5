package com.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DietPlannerObjectTest {
	
	private DietPlanner dietPlanner;
	
	@BeforeEach
	void setUp() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}
	
	@BeforeEach
	void destroy() {
		System.out.println("This method will execute once for every single unit test case");
	}

	@Test
	void shouldReturnCorrectDietPlanWhenCorrectCoder() {
		//given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expected = new DietPlan(2202, 110, 73, 275);
		
		//when
		DietPlan actual = dietPlanner.calculateDiet(coder);
		
		//then
		assertAll(
			() -> assertEquals(expected.getCalories(), actual.getCalories()),
			() -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
			() -> assertEquals(expected.getFat(), actual.getFat()),
			() -> assertEquals(expected.getProtein(), actual.getProtein())
				);
	}

}
