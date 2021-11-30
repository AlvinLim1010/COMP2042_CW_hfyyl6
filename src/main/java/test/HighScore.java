package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore extends JComponent implements MouseListener, MouseMotionListener {

    private Rectangle menuFace;
    private GameFrame owner;
    private Rectangle backButton;
    private Font buttonFont;
    private Font greetingFont;
    private Font textFont;
    private Font scoreFont;
    private BasicStroke borderStoke;
    private BufferedImage image;
    private boolean buttonClicked;
    private String scoreText;
    private String currentScoreText;


    private static int i, j;
    public static int[][] score;


    public HighScore(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 13);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(2);

        score = new int[8][3];

        buttonFont = new Font("Helvetica",Font.PLAIN,20);
        greetingFont = new Font("Serif",Font.BOLD,50);
        textFont = new Font("SansSerif",Font.PLAIN,15);
        scoreFont = new Font("SansSerif",Font.PLAIN,20);

        fileRead();

    }

    public static void fileRead(){
        i = 0;
        try{
            File myObj = new File("resources/HighScores.txt");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()){
                for (j = 0; j < 3; j++){
                    score[i][j] = myReader.nextInt();
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void fileWrite(){
        try {
            FileWriter myWriter = new FileWriter("resources/HighScores.txt");
            myWriter.write("Bricks\tMinutes\tSeconds");
            for(i = 0; i < 7; i++){
                if(score[i][0] == 0 && score[i][1] == 0 && score[i][2] == 0){
                    break;
                }
                myWriter.write("\n" + score[i][0] + "\t" + score[i][1] + "\t" + score[i][2]);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void sortingAfterGame(){
        if(score[6][0] < Wall.getTotalBrickBroken()){
            score[6][0] = Wall.getTotalBrickBroken();
            score[6][1] = GameTimer.getMinutes();
            score[6][2] = GameTimer.getSeconds();
        }

        for (i = 0; i < 7; i++) {
            for (int j = i + 1; j < 7; j++) {
                if (score[j][0] > score[i][0]) {
                    int temp1 = score[i][0];
                    int temp2 = score[i][1];
                    int temp3 = score[i][2];
                    score[i][0] = score[j][0];
                    score[i][1] = score[j][1];
                    score[i][2] = score[j][2];
                    score[j][0] = temp1;
                    score[j][1] = temp2;
                    score[j][2] = temp3;
                }
            }
        }
        fileWrite();
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        try {
            image = ImageIO.read(getClass().getResource("/HighScore.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(image, 0, 0, 450, 300, this);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.BLUE);

        FontRenderContext frc = g2d.getFontRenderContext();

        scoreText = String.format("#%02d %dBricks %dMinutes %dSeconds", i+1, score[i][0], score[i][1], score[i][2]);

        Rectangle2D greetingsRect = greetingFont.getStringBounds("HIGH SCORE",frc);
        Rectangle2D scoreRect = textFont.getStringBounds(scoreText,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(greetingFont);
        g2d.drawString("HIGH SCORE",sX,sY);

        sY += (int) scoreRect.getHeight() * 0.5;

        g2d.setFont(textFont);
        g2d.setColor(Color.WHITE);
        for(i = 0; i < 7; i++){
            if(score[i][0] == 0 && score[i][1] == 0 && score[i][2] == 0){
                break;
            }
            sX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
            sY += (int) scoreRect.getHeight() * 1.1;
            scoreText = String.format("#%02d %02dBricks %02dMinutes %02dSeconds", i+1,
                    score[i][0], score[i][1], score[i][2]);
            g2d.drawString(scoreText,sX,sY);
        }

        sX = (int) ((menuFace.width - backButton.width) / 2 * 0.2);
        sY = (int) ((menuFace.height - backButton.height) * 0.8 * 1.1);
        scoreText = String.format("Current Score: %02dBricks %02dMinutes %02dSeconds", Wall.getTotalBrickBroken()
                , GameTimer.getMinutes(), GameTimer.getSeconds());
        g2d.setFont(scoreFont);
        g2d.setColor(Color.CYAN);
        g2d.drawString(scoreText,sX,sY);

    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds("⮘ HOME MENU",frc);

        g2d.setFont(buttonFont);

        int x = (int) ((menuFace.width - backButton.width) / 2 * 0.2);
        int y =(int) ((menuFace.height - backButton.height) * 0.8 * 1.2);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(backButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(buttonClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(Color.RED);
            g2d.draw(backButton);
            g2d.setColor(Color.RED);
            g2d.drawString("⮘ HOME MENU",x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString("⮘ HOME MENU",x,y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.backToHomePageFromHighScore();

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)) {
            buttonClicked = true;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);

        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(buttonClicked){
            buttonClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}