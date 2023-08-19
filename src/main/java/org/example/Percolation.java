package org.example;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numberOfOpenSites = 0;

    private final int VIRTUAL_BOTTOM_POINT;
    private final int VIRTUAL_TOP_POINT = 0;

    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private final int[][] grid;
    private final WeightedQuickUnionUF unionFind;


    public Percolation(int n) {
        grid = new int[n][n];

        VIRTUAL_BOTTOM_POINT = getNodeNumberFromRowColumn(n, 0);

        unionFind = getWeightedQuickUnionUF();
    }

    private WeightedQuickUnionUF getWeightedQuickUnionUF() {
        return new WeightedQuickUnionUF(VIRTUAL_BOTTOM_POINT + 1);
    }

    public void open(int row, int col) {
        grid[row][col] = OPEN;

        numberOfOpenSites++;

        uniteWithOtherOpenedNodes(row, col);
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == OPEN;
    }

    public boolean isFull(int row, int col) {
        int nodeNumber = getNodeNumberFromRowColumn(row, col);
        return unionFind.find(nodeNumber) == unionFind.find(VIRTUAL_TOP_POINT);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return unionFind.find(VIRTUAL_BOTTOM_POINT) == unionFind.find(VIRTUAL_TOP_POINT);
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
            unionFind.union(VIRTUAL_BOTTOM_POINT, nodeNumber);
        } else {
            uniteWithRegularBottomNode(nodeNumber, row, col);
        }
    }

    private void uniteWithTopNode(int row, int col, int nodeNumber) {
        boolean isNodeTopMost = row == 0;
        if (isNodeTopMost) {
            unionFind.union(VIRTUAL_TOP_POINT, nodeNumber);
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