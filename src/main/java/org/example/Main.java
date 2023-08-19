package org.example;

public class Main {
    private static final int gridSize = 5;
    private static final int bottomRowIndex = gridSize - 1;
    public static void main(String[] args) {
        Percolation percolation = new Percolation(gridSize);
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++) {
            percolation.open(rowIndex, 0);
        }

        System.out.println("Open sites: " + percolation.numberOfOpenSites());
        System.out.println("Percolates: " + percolation.percolates());
    }
}
