package chess_game;

// Libraries
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Shaikh Yaqoob
 * Contributed By Muhammad Mubeen
 */

// Chess Games Class
public class Chess_Game {

    // List to store all pieces on the chess board
    public static  LinkedList <Piece> ps=new LinkedList<>();
    public static Piece selectedPiece=null;

    /**
     * Main method to start the chess game.
     */
    public static void main(String[] args)throws IOException {

    // Load the image containing chess piece sprites
    BufferedImage all=ImageIO.read(new File("E:\\chess.png"));

    // Array to store individual piece images
    Image imgs[]=new Image[12];

        int ind=0;
        for(int y=0;y<400;y+=200){
        for(int x=0;x<1200;x+=200){
            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
            ind++;
         }
        }

        // Create Pieces
        Piece brook=new Piece(0, 0, false, "rook", ps);
        Piece bkinght=new Piece(1, 0, false, "knight", ps);
        Piece bbishop=new Piece(2, 0, false, "bishop", ps);
        Piece bqueen=new Piece(3, 0, false, "queen", ps);
        Piece bking=new Piece(4, 0, false, "king", ps);
        Piece bbishop2=new Piece(5, 0, false, "bishop", ps);
        Piece bkight2=new Piece(6, 0, false, "knight", ps);
        Piece brook2=new Piece(7, 0, false, "rook", ps);
        Piece bpawn1=new Piece(1, 1, false, "pawn", ps);
        Piece bpawn2=new Piece(2, 1, false, "pawn", ps);
        Piece bpawn3=new Piece(3, 1, false, "pawn", ps);
        Piece bpawn4=new Piece(4, 1, false, "pawn", ps);
        Piece bpawn5=new Piece(5, 1, false, "pawn", ps);
        Piece bpawn6=new Piece(6, 1, false, "pawn", ps);
        Piece bpawn7=new Piece(7, 1, false, "pawn", ps);
        Piece bpawn8=new Piece(0, 1, false, "pawn", ps);

        Piece wrook=new Piece(0, 7, true, "rook", ps);
        Piece wkinght=new Piece(1, 7, true, "knight", ps);
        Piece wbishop=new Piece(2, 7, true, "bishop", ps);
        Piece wqueen=new Piece(3, 7, true, "queen", ps);
        Piece wking=new Piece(4, 7, true, "king", ps);
        Piece wbishop2=new Piece(5, 7, true, "bishop", ps);
        Piece wkight2=new Piece(6, 7, true, "knight", ps);
        Piece wrook2=new Piece(7, 7, true, "rook", ps);
        Piece wpawn1=new Piece(1, 6, true, "pawn", ps);
        Piece wpawn2=new Piece(2, 6, true, "pawn", ps);
        Piece wpawn3=new Piece(3, 6, true, "pawn", ps);
        Piece wpawn4=new Piece(4, 6, true, "pawn", ps);
        Piece wpawn5=new Piece(5, 6, true, "pawn", ps);
        Piece wpawn6=new Piece(6, 6, true, "pawn", ps);
        Piece wpawn7=new Piece(7, 6, true, "pawn", ps);
        Piece wpawn8=new Piece(0, 6, true, "pawn", ps);


    JFrame frame = new JFrame ();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);

        // Create a panel to draw the chess board and pieces
        JPanel panel = new JPanel(){
        @Override
        public void paint(Graphics g) {
            boolean white= true;

            for(int i = 0;i<8;i++) {
               for(int j=0;j<8;j++) {

                   if(white){
                       g.setColor(new Color(235,235, 208));
                   }

                   else{
                       g.setColor(new Color(119, 148, 85));
                   }

                g.fillRect(i*64, j*64, 64, 64);
                white=!white;
               }
               white=!white;
            }

            for(Piece p:ps){
            int ind=0;
             if(p.name.equalsIgnoreCase("king")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("queen")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("knight")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("rook")){
                        ind=4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")){
                        ind=5;
                    }
                    if(!p.isWhite){
                        ind+=6;
                    }
                   g.drawImage(imgs[ind], p.x, p.y, this);
            }
        }
    };

    frame.add(panel);
    frame.addMouseMotionListener(new MouseMotionListener(){
        @Override
        public void mouseDragged(MouseEvent e) {
       if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    frame.repaint();
                }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    });

    frame.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        System.out.println((getPiece(e.getX(), e.getY()).isWhite?"white ":"balck ")+getPiece(e.getX(), e.getY()).name);
        selectedPiece=getPiece(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           selectedPiece.move(e.getX()/64, e.getY()/64);
                frame.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    });


    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);
    }

    public static Piece getPiece(int x,int y){

        int xp=x/64;
        int yp=y/64;
        for(Piece p:ps){

             if(p.xp==xp&&p.yp==yp){
                return p;
            }
        }
        return null;
    }
}