package com.zsq.util;

public class Student {
    private long userId;
    private int [][][]matrix;

    public Student(long userId, int[][][] matrix) {
        this.matrix = new int[25][][];
        for(int i=0;i<25;++i) this.matrix[i] = new int[6][];
        for(int i=0;i<25;++i) {
            for(int j=0;j<6;++j) {
                this.matrix[i][j] = new int[5];
            }
        }
        this.userId = userId;
        for(int k=0;k<25;++k) {
            for(int i=1;i<=5;++i) {
                for(int j=1;j<=4;++j) {
                    this.matrix[k][i][j] = matrix[k][i][j];
                }
            }
        }

    }

    public long getUserId() {
        return userId;
    }
    public int[][][] getMatrix() { return matrix; }
    public void minusMatrixItem(int k, int x, int y) {
        matrix[k][x][y]--;
    }
    public int getMatrixItem(int k, int x, int y) {
        return matrix[k][x][y];
    }
}
