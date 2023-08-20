package org.example;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] estimates;
    private final int gridSize;
    private final int numberOfTrials;

    public PercolationStats(int gridSize, int numberOfTrials) {
        if (gridSize <= 0 || numberOfTrials <= 0) {
            throw new IllegalArgumentException("All arguments have to be positive, non zero values.");
        }

        this.gridSize = gridSize;
        this.numberOfTrials = numberOfTrials;

        estimates = new double[numberOfTrials];

        performExperimentNTimes();
    }

    public double mean() {
        return StdStats.mean(estimates);
    }

    public double stddev() {
        return StdStats.stddev(estimates);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 / Math.sqrt(numberOfTrials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 / Math.sqrt(numberOfTrials));
    }

    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int numberOfTrials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(gridSize, numberOfTrials);

        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

    private void performExperimentNTimes() {
        for (int i = 0; i < numberOfTrials; i++) {
            estimates[i] = getPercolationThresholdEstimate();
        }
    }

    private double getPercolationThresholdEstimate() {
        Percolation percolation = new Percolation(gridSize);
        while (!percolation.percolates()) {
            openRandomSite(percolation);
        }

        return (double) percolation.numberOfOpenSites() / gridSize;
    }

    private void openRandomSite(Percolation percolation) {
        int row = (int) getRandomNumberUpTo(gridSize);
        int col = (int) getRandomNumberUpTo(gridSize);

        percolation.open(row, col);
    }

    private static double getRandomNumberUpTo(int max) {
        return StdRandom.uniformDouble(0, max);
    }
}