package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    private static final int gridSize = 5;
    @Test
    void shouldPercolateWithOpenedStraightLine() {
        Percolation percolation = new Percolation(gridSize);
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, 0);
        }

        assertEquals(gridSize, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    void shouldNotPercolateWithIncompleteStraightLine() {
        Percolation percolation = new Percolation(gridSize);
        for (int rowIndex = 0; rowIndex < gridSize - 1; rowIndex++) {
            percolation.open(rowIndex, 0);
        }

        assertEquals(gridSize - 1, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    void shouldNotPercolateWithDiagonals() {
        Percolation percolation = new Percolation(gridSize);

        int colIndex = 0;
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, colIndex);

            colIndex++;
        }

        assertEquals(gridSize, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    void shouldPercolateWithStairs() {
        Percolation percolation = new Percolation(gridSize);

        int colIndex = 0;
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, colIndex);
            percolation.open(rowIndex + 1, colIndex);

            colIndex++;
        }

        assertEquals(9, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }
}