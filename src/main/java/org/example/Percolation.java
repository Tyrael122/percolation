package org.example;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int virtualTopPoint = 0;
    private static final boolean OPEN = true;

    private int numberOfOpenSites = 0;
    private final int virtualBottomPoint;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF unionFind;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The grid size has to be greater than 0.");
        }

        grid = new boolean[n][n];

        virtualBottomPoint = getNodeNumberFromRowColumn(n, 0);

        unionFind = getWeightedQuickUnionUF();

        openFirstRow();
    }

    public void open(int row, int col) {
        validateInputOutOfBounds(row, col);

        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = OPEN;

        numberOfOpenSites++;

        uniteWithOtherOpenedNodes(row, col);
    }

    public boolean isOpen(int row, int col) {
        validateInputOutOfBounds(row, col);

        return grid[row][col] == OPEN;
    }

    public boolean isFull(int row, int col) {
        validateInputOutOfBounds(row, col);

        int nodeNumber = getNodeNumberFromRowColumn(row, col);
        return unionFind.find(nodeNumber) == unionFind.find(virtualTopPoint);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return unionFind.find(virtualBottomPoint) == unionFind.find(virtualTopPoint);
    }

    private WeightedQuickUnionUF getWeightedQuickUnionUF() {
        return new WeightedQuickUnionUF(virtualBottomPoint + 1);
    }

    private void openFirstRow() {
        for (int colIndex = 0; colIndex <= grid.length; colIndex++) {
            open(0, colIndex);
        }

        numberOfOpenSites = 0;
    }

    private void validateInputOutOfBounds(int row, int col) {
        if (isOutsideBounds(row) || isOutsideBounds(col)) {
            throw new IllegalArgumentException("The input values (" + row + ", " + col + ") are out of the bounds of the grid.");
        }
    }

    private boolean isOutsideBounds(int row) {
        return row >= grid.length || row < 0;
    }

    private void uniteWithOtherOpenedNodes(int row, int col) {
        int nodeNumber = getNodeNumberFromRowColumn(row, col);

        uniteWithBottomNode(row, col, nodeNumber);
        uniteWithTopNode(row, col, nodeNumber);

        uniteWithLeftNode(nodeNumber, row, col);
        uniteWithRightNode(nodeNumber, row, col);
    }

    private void uniteWithBottomNode(int row, int col, int nodeNumber) {
        boolean isNodeBottomMost = row == grid.length - 1;
        if (isNodeBottomMost) {
            unionFind.union(virtualBottomPoint, nodeNumber);
        } else {
            uniteWithRegularBottomNode(nodeNumber, row, col);
        }
    }

    private void uniteWithTopNode(int row, int col, int nodeNumber) {
        boolean isNodeTopMost = row == 0;
        if (isNodeTopMost) {
            unionFind.union(virtualTopPoint, nodeNumber);
        } else {
            uniteWithRegularTopNode(nodeNumber, row, col);
        }
    }

    private void uniteWithRegularBottomNode(int nodeNumber, int row, int col) {
        int bottomNodeRowIndex = row + 1;
        if (bottomNodeRowIndex >= grid.length) {
            return;
        }

        uniteNodeWithNeighborRowColumn(bottomNodeRowIndex, col, nodeNumber);
    }

    private void uniteWithRegularTopNode(int nodeNumber, int row, int col) {
        int topNodeRowIndex = row - 1;
        if (topNodeRowIndex < 0) {
            return;
        }

        uniteNodeWithNeighborRowColumn(topNodeRowIndex, col, nodeNumber);
    }

    private void uniteWithRightNode(int nodeNumber, int row, int col) {
        int rightNodeColIndex = col + 1;
        if (rightNodeColIndex >= grid.length) {
            return;
        }

        uniteNodeWithNeighborRowColumn(row, rightNodeColIndex, nodeNumber);
    }

    private void uniteWithLeftNode(int nodeNumber, int row, int col) {
        int leftNodeColIndex = col - 1;
        if (leftNodeColIndex < 0) {
            return;
        }

        uniteNodeWithNeighborRowColumn(row, leftNodeColIndex, nodeNumber);
    }

    private void uniteNodeWithNeighborRowColumn(int row, int col, int nodeNumber) {
        if (!isOpen(row, col)) {
            return;
        }

        int leftNodeNumber = getNodeNumberFromRowColumn(row, col);
        unionFind.union(nodeNumber, leftNodeNumber);
    }

    private int getNodeNumberFromRowColumn(int row, int col) {
        return (grid.length * row) + (col + 1);
    }
}