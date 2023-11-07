package chess_game;

import java.util.LinkedList;

/**
 * Represents a chess piece.
 * Each piece has a position (xp, yp) and a screen position (x, y) based on the board grid.
 * Pieces can be white or black, and they belong to a list of pieces in the chess game.
 * The name of the piece is also stored.
 *
 * @author Shaikh Yaqoob
 * Contribute by Muhammad Mubeen
 */

public class Piece {

    // Position on the board grid
    int xp;
    int yp;

    // Screen position
    int x;
    int y;

    // Color of the piece (true for white, false for black)
    boolean isWhite;

    // List of all pieces in the chess game
    LinkedList<Piece> ps;

    // Name of the piece (e.g., "rook", "pawn")
    String name;

    /**
     * Constructor to initialize a chess piece.
     *
     * @param xp       Initial x position on the board grid
     * @param yp       Initial y position on the board grid
     * @param isWhite  Color of the piece (true for white, false for black)
     * @param name     Name of the piece
     * @param ps       List of all pieces in the chess game
     */
    public Piece(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64; // Calculate screen position based on the board grid
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        ps.add(this); // Add the piece to the list of pieces
        this.name = name;
    }

    /**
     * Move the piece to a new position on the board.
     * If there is an opponent's piece at the destination, remove it.
     *
     * @param xp New x position on the board grid
     * @param yp New y position on the board grid
     */
    public void move(int xp, int yp) {
        // Check if there is an opponent's piece at the destination
        if (Chess_Game.getPiece(xp * 64, yp * 64) != null) {
            if (Chess_Game.getPiece(xp * 64, yp * 64).isWhite != isWhite) {
                // Kill the opponent's piece
                Chess_Game.getPiece(xp * 64, yp * 64).kill();
            } else {
                // If the destination has a piece of the same color, return to the original position
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }

        // Move the piece to the new position
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
    }

    /**
     * Remove the piece from the list of pieces (kill the piece).
     */
    public void kill() {
        ps.remove(this);
    }
}