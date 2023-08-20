package org.example;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.jupiter.api.Test;

class PercolationStatsTest {
    @Test
    void twoGridSizeHundredThousandTrials() {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(2, 100000);

        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "s");
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

    @Test
    void twoGridSizeTenThousandTrials() {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(2, 10000);

        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "s");
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

    @Test
    void twoHundredGridSizeHundredTrials() {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(200, 100);

        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "s");
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

}