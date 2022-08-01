package com.openclassrooms.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    Calculator calculatorUnderTest;

    private static Instant startedAt;

    @BeforeAll
    public static void initStartingTime() {
        // va appeler la méthode avant tous les tests (1 fois)
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        // va appeler la méthode après tous les tests (1 fois)
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.printf("Durée des tests est de : %d ms", duration);
    }


    @BeforeEach
    // va instancier la méthode avant chaque test
    public void initCalculator() {
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    // va fermer une action après chaque test
    public void undefCalculator() {
        calculatorUnderTest = null;
    }

    @Test
    void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;
        // Act
        int somme = calculatorUnderTest.add(a, b);
        // Assert
        assertEquals(5, somme);
    }

    @Test
    void testMultiplyTwoPositiveNumber() {
        // Arrange
        int a = 5;
        int b = 4;
        // Act
        int produit = calculatorUnderTest.multiply(a, b);
        // Assert
        assertEquals(20, produit);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = {1, 2, 42, 50, 66565, 84846})
    //ps : le {0} ici correspond au premier paramètre de la méthode et donc a chaque énumérations a tour de role
    //dans le  "ValueSource"
    public void multiply_by_zero_shouldRetournZero(int args) {
        final int actualResult = calculatorUnderTest.multiply(args, 0);

        assertEquals(0, actualResult);
    }

    @ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    //ps : le {0} ici correspond au premier paramètre de la méthode et donc a chaque énumérations a tour de role dans le  "CsvSource"
    //{0} = arg1 = " 1 , .. , .."
    //{1} = arg2 = " .. , 1 , .."
    //{2} = expectedResult = " .. , .. , 2 "
    public void multiply_by_zero_shouldRetournZero(int arg1, int arg2, int expectResult) {
        final int actualResult = calculatorUnderTest.add(arg1,arg2);

        assertEquals(expectResult, actualResult);
    }

    @Test
    @Timeout(1)
    // Il va lancer un time out apres 1 seconde ( le code on a mis 2sec, normal que ca saute)
    public void longCalcul_shouldCOmputeInLessThan1Second(){
        calculatorUnderTest.longCalculation();
    }

    
}
