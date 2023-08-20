package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    private static final int gridSize = 5;

    @Test
    void shouldOpenAtLimit() {
        Percolation percolation = new Percolation(gridSize);

        percolation.open(1, gridSize);
        percolation.open(1, 1);
    }

    @Test
    void shouldNotOpenAtLimit() {
        Percolation percolation = new Percolation(gridSize);

        assertThrows(IllegalArgumentException.class, () -> percolation.open(0, 1));
        assertThrows(IllegalArgumentException.class, () -> percolation.open(0, 0));
        assertThrows(IllegalArgumentException.class, () -> percolation.open(1, 0));
    }

    @Test
    void shouldPercolateWithOpenedStraightLine() {
        Percolation percolation = new Percolation(gridSize);
        for (int rowIndex = 1; rowIndex <= gridSize; rowIndex++) {
            percolation.open(rowIndex, 1);
        }

        assertEquals(gridSize, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    void shouldNotPercolateWithIncompleteStraightLine() {
        Percolation percolation = new Percolation(gridSize);
        for (int rowIndex = 1; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, 1);
        }

        assertEquals(gridSize - 1, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    void shouldNotPercolateWithDiagonals() {
        Percolation percolation = new Percolation(gridSize);

        int colIndex = 1;
        for (int rowIndex = 1; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, colIndex);

            colIndex++;
        }

        assertEquals(gridSize - 1, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    void shouldPercolateWithStairs() {
        Percolation percolation = new Percolation(gridSize);

        int colIndex = 1;
        for (int rowIndex = 1; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, colIndex);
            percolation.open(rowIndex + 1, colIndex);

            colIndex++;
        }

        assertEquals(8, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}