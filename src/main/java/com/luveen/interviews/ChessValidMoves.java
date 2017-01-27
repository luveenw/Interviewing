package com.luveen.interviews;

/**
 * Created by luvee on 1/27/2017.
 *
 * Design a system that can represent valid moves for a chess piece, given the type of piece and current board conditions.
 * E.g. a pawn can move two squares if it is its first move; diagonally to capture a piece; one forward or back otherwise.
 * A move should never result in a checkmate (i.e. should not expose the king).
 *
 * @interview Udacity Onsite 01/27/2017
 */
public class ChessValidMoves {

//    PieceType
//    {
//        PAWN
//                KING
//  ...
//    }
//
//    PieceColor {
//        WHITE
//                BLACK
//    }
//
//    Cell {
//        int row
//        int col
//    }
//
//    Board {
//        Piece[8][8] pieces
//    }
//
//    Piece {
//        PieceType pieceType
//        PieceColor color
//        int numMoves
//        // Cell curPos
//    }
//
//    RuleSet
//    PieceType -> <Rule1>, <Rule2>
//
//    Rule {
//        Predicate pred
//        Cell delta
//
//    }
//
//    Predicate {
//        Piece piece
//        Cell cell
//        Board board
//
//        // addl elements to define predicate wrt board position and adjacent cells
//
//        boolean isApplicable()
//    }
//
//    static List<Cell> getPossibleMoves(Piece piece, Board board) {
//        Set<Rule> rules = getRulesFor(piece, board);
//
//        rules.filter(r -> r.pred.isApplicable());
//    }
}
