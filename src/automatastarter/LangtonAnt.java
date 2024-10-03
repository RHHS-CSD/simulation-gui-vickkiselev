/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automatastarter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import java.util.Arrays;

/**
 *
 * @author vikyk
 */
public class LangtonAnt {

    // ant coordinates and characteristics
    static int antX = 0;
    static int antY = 0;
    static int antSpeed;

    static Graphics g;

    // ant's direction
    static String antDirection = "up";

    // grid of booleans to represent state of each square
    static Boolean[][] grid;

    /**
     * @param args the command line arguments
     */
    public LangtonAnt(int x, int y, int spd, Graphics g) {

        antSpeed = spd;
        grid = new Boolean[x][y];

        // fills each row with a boolean
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], true);
        }

        // counts amount of cycles the ant will go through
        int count = 0;

        // will end after 10 cycles of ant movement
        while (count < 11) {

            // turn the ant and move it
            turnAnt();
            moveAnt();

            // set the current part of the grid where the ant is on to the opposite
            grid[antX][antY] = !grid[antX][antY];

            // increase the count of cycles
            count++;
        }
    }
    
// getters for variables
    public Boolean[][] getGrid() {
        return grid;
    }

    public int getX() {
        return antX;
    }

    public int getY() {
        return antY;
    }

    // prints the grid based on what is in the array
    private static void printGrid(Boolean[][] square) {
        // loops through x and y components of the grid and prints out the according symbol that represents the spots state
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                if (i == antX && j == antY) {
                    System.out.print("[ ooo<]");    // represents the and
                } else if (square[i][j] == true) {
                    System.out.print("[  W  ]");    // represents the white square
                } else if (square[i][j] == false) {
                    System.out.print("[  B  ]");    // represents the black square
                }
            }

            System.out.print("\n");
        }
    }

    // change ant's location based on what direction the ant is facing
    private static void moveAnt() {

        // moves the ant by the inputted speed depending on the direction it is facing
        // uses a toroidal grid, so when ant walks off edge it goes to opposite side
        switch (antDirection) {
            case "right":
                if (antX < (grid[0].length - 1) - antSpeed) {
                    antX += antSpeed;
                    // adjust the formula slightly for if the ant is at the very edge of the grid
                } else if (antX == grid[0].length - 1) {
                    antX = antSpeed - 1;
                } else {
                    // if the ant exceeds the grid's bounds, take the value, find the amount of squares it goes out of bounds, and then add that to the other side of the grid
                    // change the below formula correspondingly for each direction
                    antX = antSpeed + antX - (grid[0].length - 1);
                }
                break;
            case "left":
                if (antX >= antSpeed) {
                    antX -= antSpeed;
                } else if (antX == 0) {
                    antX = grid[0].length - antSpeed;
                } else {
                    antX = (grid[0].length - 1) + (antX - antSpeed);
                }
                break;
            case "up":
                if (antY >= antSpeed) {
                    antY -= antSpeed;
                } else if (antY == 0) {
                    antY = grid[0].length - antSpeed;
                } else {
                    antY = (grid.length - 1) + (antY - antSpeed);
                }
                break;
            case "down":
                if (antY <= (grid[0].length - 1) - antSpeed) {
                    antY += antSpeed;
                } else if (antY == grid[0].length - 1) {
                    antY = antSpeed - 1;
                } else {
                    antY = (antSpeed + antY) - (grid.length - 1);
                }
                break;
            default:
                break;
        }

    }

    // turns the ant based on the type of square it is standing on
    private static void turnAnt() {
        // if the grid spot is white, turn the ant right
        if (grid[antX][antY] == true) {
            turnRight();
        } else if (grid[antX][antY] == false) {    // if the grid spot is black, turn the ant left
            turnLeft();
        }
    }

    // turns the ant right based on its previous direction
    private static void turnRight() {
        switch (antDirection) {
            case "right":
                antDirection = "down"; // if facing right, turn to face down
                break;
            case "left":
                antDirection = "up"; // if facing left, turn to face up
                break;
            case "up":
                antDirection = "right"; // if facing up, turn to face right
                break;
            case "down":
                antDirection = "left"; // if facing down, turn to face left
                break;
            default:
                break;
        }
    }

    // turns the ant left based on its previous direction
    private static void turnLeft() {
        switch (antDirection) {
            case "right":
                antDirection = "up";  // if facing right, turn to face up
                break;
            case "left":
                antDirection = "down";  // if facing left, turn to face down
                break;
            case "up":
                antDirection = "left"; // if facing up, turn to face left
                break;
            case "down":
                antDirection = "right"; // if facing down, turn to face right
                break;
            default:
                break;
        }
    }
}
