package com.company;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Main {

    public static void main(String[] args) {
        
        int width = 540;
        int height = 360;

        World world = new World(width,height);

        BufferedImage imageField = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                imageField.setRGB(column,row,world.getCreatureColor(column,row));
            }
        }

        //create frame


        JFrame frame = new JFrame("predator and prey");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //TODO: create magic
        //frame.setMinimumSize(new Dimension(width,height));
        JLabel label1 = new JLabel(new ImageIcon(imageField));
        JLabel label2 = new JLabel("cycle: 0");
        frame.add(label2);
        frame.add(label1);


        // size the frame
        frame.pack();

        // show it
        frame.setVisible(true);

        int counter = 0;

        while(true){
            counter++;
            world.updateWorld();

            for(int row = 0; row < height; row++){
                for(int column = 0; column < width; column++){
                    imageField.setRGB(column,row,world.getCreatureColor(column,row));
                }
            }
            label1.setIcon(new ImageIcon(imageField));
        }
    }
}
