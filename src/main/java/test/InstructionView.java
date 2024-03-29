package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * InstructionView class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class InstructionView implements ImageObserver {

    /**
     * Instruction Viewing Constructor that will shows the instruction of the game
     * @param g graphics parameter that shows the screen stuffs
     */
    public InstructionView(Graphics g){
        paint(g);
    }

    /**
     * Paint the screen, shows text, button and background images
     * @param g graphics parameter that shows the screen stuffs
     */
    public void paint(Graphics g){
        drawInstructionMenu((Graphics2D)g);
    }

    /**
     * Draw the screen, text, button and images
     * @param g2d graphics2d parameter
     */
    public void drawInstructionMenu(Graphics2D g2d){

        drawInstructionContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = InstructionModel.getMenuFace().getX();
        double y = InstructionModel.getMenuFace().getY();

        g2d.translate(x,y);

        //methods calls
        drawInstructionText(g2d);
        drawInstructionButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Draw the instruction text
     * @param g2d graphics2d parameter
     */
    private void drawInstructionText(Graphics2D g2d){


        InstructionModel.setColor(Color.BLUE);
        InstructionModel.setTextFont(new Font("Serif",Font.BOLD,50));
        InstructionModel.setText("INSTRUCTIONS");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 0),
                InstructionModel.getText());

        InstructionModel.setColor(Color.BLACK);
        InstructionModel.setTextFont(new Font("Serif",Font.PLAIN,15));
        InstructionModel.setText("Welcome to Brick Destroyer!");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 1.1),
                InstructionModel.getText());

        InstructionModel.setText("The player's goal is to break all the bricks while not using all the balls.");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 2.1),
                InstructionModel.getText());

        InstructionModel.setText("There are 5 levels. 31 Bricks per level. Each impact will deal 1 strength.");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 3.1),
                InstructionModel.getText());

        InstructionModel.setText("ClayBrick has a strength of 1, CementBrick has a strength of 2,");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 4.1),
                InstructionModel.getText());

        InstructionModel.setText("SteelBrick has a strength of 1 and 0.4 chance of breaking,");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 5.1),
                InstructionModel.getText());

        InstructionModel.setText("SlowBrick will slow the player for 4 seconds, and lastly");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 6.1),
                InstructionModel.getText());

        InstructionModel.setText("a SpecialBrick, where when impacted it will invert the movement.");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 7.1),
                InstructionModel.getText());

        InstructionModel.setText("Press A to move LEFT. Press D to move RIGHT.");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 8.1),
                InstructionModel.getText());

        InstructionModel.setText("Press Esc or Space to PAUSE the game.");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 9.1),
                InstructionModel.getText());

        InstructionModel.setText("ENJOY THE GAME!!");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, InstructionModel.getColor(), InstructionModel.getTextFont(),
                drawTextCoordX(InstructionModel.getRect()), drawTextCoordY(InstructionModel.getRect(), 10.1),
                InstructionModel.getText());

    }

    /**
     * The coordinate X of the text
     * @param rect rectangle of the text
     * @return the X coordinate for the text to be placed at
     */
    public int drawTextCoordX(Rectangle2D rect){
        return (int)(InstructionModel.getMenuFace().getWidth() - rect.getWidth()) / 2;
    }

    /**
     * The coordinate Y of the text
     * @param rect rectangle of the text
     * @param spacingY spacing of text from one another
     * @return the Y coordinate for the text to be placed at
     */
    public int drawTextCoordY(Rectangle2D rect, double spacingY){
        return (int) ((int) InstructionModel.getMenuFace().getHeight() / 5 + rect.getHeight() * spacingY);
    }

    /**
     * Draw the text
     * @param g2d graphics2d parameter
     * @param color colour of the text
     * @param font font of the text
     * @param x X coordinate of text
     * @param y Y coordinate of text
     * @param text text to put down
     */
    private void drawTextPlace(Graphics2D g2d, Color color, Font font, int x, int y, String text){

        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text,x,y);
    }

    /**
     * Draw the back button
     * @param g2d graphics2d parameter
     */
    private void drawInstructionButton(Graphics2D g2d){

        InstructionModel.setTextFont(new Font("Helvetica",Font.PLAIN,20));

        g2d.setFont(InstructionModel.getTextFont());

        int x = (InstructionModel.getMenuFace().width - InstructionModel.getButton().width) / 2;
        int y =(int) ((InstructionModel.getMenuFace().height - InstructionModel.getButton().height) * 0.8);

        x *= 0.2;
        y *= 1.2;

        InstructionModel.getButton().setLocation(x,y);

        InstructionModel.setText("⮘ BACK");
        InstructionModel.setRect(InstructionModel.getTextFont().getStringBounds(InstructionModel.getText(),g2d.getFontRenderContext()));


        x = (int)(InstructionModel.getButton().getWidth() - InstructionModel.getRect().getWidth()) / 2;
        y = (int)(InstructionModel.getButton().getHeight() - InstructionModel.getRect().getHeight()) / 2;

        x += InstructionModel.getButton().x;
        y += InstructionModel.getButton().y + (InstructionModel.getButton().height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(InstructionModel.getButton());
        g2d.setStroke(InstructionModel.getBorderStoke());
        g2d.setColor(Color.BLACK);

        if(InstructionModel.getButtonClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(Color.RED);
            g2d.draw(InstructionModel.getButton());
            g2d.setColor(Color.RED);
            g2d.drawString(InstructionModel.getText(),x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(InstructionModel.getButton());
            g2d.setColor(Color.BLACK);
            g2d.drawString(InstructionModel.getText(),x,y);
        }

    }

    /**
     * Draw the instruction background image
     * @param g2d graphics2d parameter
     */
    private void drawInstructionContainer(Graphics2D g2d){
        try {
            InstructionModel.setImage(ImageIO.read(getClass().getResource("/Instruction.jpg")));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(InstructionModel.getImage(), 0, 0, 450, 300, this);
    }

    /**
     * Update image, the background image
     * @param img image
     * @param infoflags image flag
     * @param x x coordinate of image
     * @param y y coordinate of image
     * @param width width of image
     * @param height height of image
     * @return true or false of the image
     */
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
