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
    public LangtonAnt(int x, int y, int spd) {

        antSpeed = spd;
        grid = new Boolean[x][y];

        // fills each row with a boolean
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], true);
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

    // change ant's location based on what direction the ant is facing
    public void moveAnt() {

        this.turnAnt();
        System.out.println(antDirection + antX + antY);

        /*
        // moves the ant by the inputted speed depending on the direction it is facing
        switch (antDirection) {
            case "right":
                if (antX <= (grid[0].length - 1) - antSpeed) {
                    antX += antSpeed;
                }
                break;
            case "left":
                if (antX >= antSpeed) {
                    antX -= antSpeed;
                }
                break;
            case "up":
                if (antY >= antSpeed) {
                    antY -= antSpeed;
                }
                break;
            case "down":
                if (antY <= (grid.length - 1) - antSpeed) {
                    antY += antSpeed;
                }
                break;
            default:
                break;
        }
         */
        
        // moves the ant by the inputted speed depending on the direction it is facing
        // uses a toroidal grid, so when ant walks off edge it goes to opposite side
        switch (antDirection) {
            case "right":
                if (antX <= (grid[0].length - 1) - antSpeed) {
                    antX += antSpeed;
                    // adjust the formula slightly for if the ant is at the very edge of the grid
                } else {
                    // if the ant exceeds the grid's bounds, take the value, find the amount of squares it goes out of bounds, and then add that to the other side of the grid
                    // change the below formula correspondingly for each direction
                    antX += antSpeed;
                    antX = antX - (grid[0].length);
                }
                break;
            case "left":
                if (antX >= antSpeed) {
                    antX -= antSpeed;
                } else {
                    antX -= antSpeed;
                    antX = (grid[0].length) + antX;
                }
                break;
            case "up":
                if (antY >= antSpeed) {
                    antY -= antSpeed;
                } else {
                    antY -= antSpeed;
                    antY = (grid.length) + antY;
                }
                break;
            case "down":
                if (antY <= (grid.length - 1) - antSpeed) {
                    antY += antSpeed;
                } else {
                    antY += antSpeed;
                    antY = antY - (grid.length);
                }
                break;
            default:
                break;
        }

    }

    // turns the ant based on the type of square it is standing on
    public void turnAnt() {
        // if the grid spot is white, turn the ant right
        if (grid[antX][antY] == true) {
            turnRight();
        } else {    // if the grid spot is black, turn the ant left
            turnLeft();
        }

        grid[antX][antY] = !grid[antX][antY];
        System.out.println(antDirection + antX + antY);
    }

    // turns the ant right based on its previous direction
    public void turnRight() {
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
    public void turnLeft() {
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
