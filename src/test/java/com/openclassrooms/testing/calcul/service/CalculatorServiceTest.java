package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    @Mock
    Calculator calculator;

    CalculatorService classUnderTest;

    @BeforeEach
    public void setup() {
        classUnderTest = new CalculatorServiceImpl(calculator);
    }

    @AfterEach
    public void setupClosed() {
        classUnderTest = null;
    }

    @Test
    public void calculate_shouldUseCalculator_forAddition() {
        // GIVEN
        when(calculator.add(1, 2)).thenReturn(3);

        // WHEN
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.ADDITION, 1, 2))
                .getSolution();

        // THEN
        verify(calculator).add(1, 2); // Verifier l'égalité à 3, car bien paramétré au début
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void calculate_shouldUseCalculator_forSoustraction(){
        //GIVEN
        when(calculator.sub(2,1)).thenReturn(1);

        // WHEN
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.SUBTRACTION,2,1))
                .getSolution();

        //THEN
        verify(calculator).sub(2,1);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void calculate_shouldUseCalculator_forMultiply(){
        //GIVEN
        when(calculator.multiply(2,2)).thenReturn(4);

        //WHEN
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.MULTIPLICATION,2,2))
                .getSolution();

        //THEN
        verify(calculator).multiply(2,2);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void calculate_shouldUseCalculator_forDivise(){
        //GIVEN
        when(calculator.divide(2,2)).thenReturn(1);

        //WHEN
        final int result = classUnderTest.calculate(
                new CalculationModel(CalculationType.DIVISION,2,2))
                .getSolution();

        //THEN
        verify(calculator,times(1)).divide(2,2); // times(1) au moins appelé 1 fois
        verify(calculator,times(0)).add(2,2); // times(0) n'a jamais été appelé
        verify(calculator,never()).multiply(2,2); // never() n'a jamais été appelé
        assertThat(result).isEqualTo(1);
    }

    // SI le test du given renvoie une ArithmeticException
    // Alors le service va lui renvoyer une IllegalArgumentException
    @Test
    public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0(){
        //GIVEN
        when(calculator.divide(1,0)).thenThrow(new ArithmeticException());

        //WHEN
        assertThrows(IllegalArgumentException.class,() -> classUnderTest.calculate(
                new CalculationModel(CalculationType.DIVISION,1,0)));

        //THEN
        // on vérifie que le calculator a été appelé au moins 1 fois avec les bons arguments
        verify(calculator,times(1)).divide(1,0);
    }
}
