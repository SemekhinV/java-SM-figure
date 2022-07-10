import Figure.Rectangles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;



public class Game extends JComponent {

    Game(){}

    static class GameField extends JPanel implements ActionListener {

        Timer timer;

        public java.util.List<Rectangles> RectangleList;
        int i;
        boolean isPaintActive = false;
        int figureCount;
        int[] Cords;
        int maxFigureCount;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (Rectangles xi : RectangleList) {
                xi.tick();
                CheckCollusion();
            }
            repaint();
        }

        private void CheckCollusion() {
            for (int i = 0; i < RectangleList.size(); i++) {
                for (int j = 0; j < RectangleList.size(); j++) {
                    if (i != j) {
                        if (RectangleList.get(i).getX() < (RectangleList.get(j).getX() + RectangleList.get(i).getSize()) &&
                                (RectangleList.get(j).getX() < (RectangleList.get(i).getX() + RectangleList.get(i).getSize())) &&
                                (RectangleList.get(i).getY() < (RectangleList.get(j).getY() + RectangleList.get(j).getSize())) &&
                                (RectangleList.get(j).getY() < (RectangleList.get(i).getY() + RectangleList.get(i).getSize()))) {

                            if (RectangleList.get(j).left){
                                // -------------i-rectangle-----------------//
                                RectangleList.get(i).right = false;
                                RectangleList.get(i).left = false;
                                RectangleList.get(i).down = true;
                                RectangleList.get(i).up = false;
                                // -------------j-rectangle-----------------//
                                RectangleList.get(j).up = true;
                                RectangleList.get(j).down = false;
                                RectangleList.get(j).left = false;
                                RectangleList.get(j).right = false;

                                RectangleList.get(i).newColor(RectangleList.get(i).GetColorNumber() + 1);
                                RectangleList.get(j).newColor(RectangleList.get(i).GetColorNumber() + 1);

                                RectangleList.get(j).nextSize();

                                RectangleList.get(i).moveCurrentPosition(  RectangleList.get(j).getSize()/4, -RectangleList.get(j).getSize()/4);
                                RectangleList.get(j).moveCurrentPosition(  -RectangleList.get(j).getSize()/4, RectangleList.get(j).getSize()/4);

                            }

                            if (RectangleList.get(j).up){
                                // -------------i-rectangle-----------------//
                                RectangleList.get(i).right = false;
                                RectangleList.get(i).left = false;
                                RectangleList.get(i).down = false;
                                RectangleList.get(i).up = true;
                                // -------------j-rectangle-----------------//
                                RectangleList.get(j).up = false;
                                RectangleList.get(j).down = false;
                                RectangleList.get(j).left = true;
                                RectangleList.get(j).right = false;

                                RectangleList.get(i).newColor(RectangleList.get(i).GetColorNumber() + 1);
                                RectangleList.get(j).newColor(RectangleList.get(i).GetColorNumber() + 1);

                                RectangleList.get(j).nextSize();

                                RectangleList.get(i).moveCurrentPosition(  RectangleList.get(i).getSize()/4, -RectangleList.get(i).getSize()/4);
                                RectangleList.get(j).moveCurrentPosition(  -RectangleList.get(j).getSize()/4, RectangleList.get(j).getSize()/4);

                            }

                            if (RectangleList.get(j).down){
                                // -------------i-rectangle-----------------//
                                RectangleList.get(i).right = false;
                                RectangleList.get(i).left = false;
                                RectangleList.get(i).down = true;
                                RectangleList.get(i).up = false;
                                // -------------j-rectangle-----------------//
                                RectangleList.get(j).up = false;
                                RectangleList.get(j).down = false;
                                RectangleList.get(j).left = true;
                                RectangleList.get(j).right = false;

                                RectangleList.get(i).newColor(RectangleList.get(i).GetColorNumber() + 1);
                                RectangleList.get(j).newColor(RectangleList.get(i).GetColorNumber() + 1);

                                RectangleList.get(j).nextSize();

                                RectangleList.get(i).moveCurrentPosition(  RectangleList.get(i).getSize()/4,RectangleList.get(i).getSize()/4);
                                RectangleList.get(j).moveCurrentPosition(  -RectangleList.get(j).getSize()/4, -RectangleList.get(j).getSize()/4);
                            }

                            if (RectangleList.get(j).right){
                                // -------------i-rectangle-----------------//
                                RectangleList.get(i).right = false;
                                RectangleList.get(i).left = false;
                                RectangleList.get(i).down = false;
                                RectangleList.get(i).up = true;
                                // -------------j-rectangle-----------------//
                                RectangleList.get(j).up = false;
                                RectangleList.get(j).down = true;
                                RectangleList.get(j).left = false;
                                RectangleList.get(j).right = false;

                                RectangleList.get(i).newColor(RectangleList.get(i).GetColorNumber() + 1);
                                RectangleList.get(j).newColor(RectangleList.get(i).GetColorNumber() + 1);

                                RectangleList.get(j).nextSize();

                                RectangleList.get(i).moveCurrentPosition(  RectangleList.get(i).getSize()/4, -RectangleList.get(i).getSize()/4);
                                RectangleList.get(j).moveCurrentPosition(  -RectangleList.get(j).getSize()/4, RectangleList.get(i).getSize()/4);
                            }
                        }
                    }
                }
            }
        }

        public void CreateFigure(int count) {
            figureCount = count;

            if (count >= maxFigureCount){
                System.out.println("Too many figures!\nPrint only 42 max.");
                count = maxFigureCount;
                figureCount = count;
            }

            int dSize = 55;
            int dSizePrev = 0;

            int ColorN = 1;

            int xPrev = 0;
            int yPrev = 0;

            int x = (int)(Math.random() * 700) + 5;
            int y = (int)(Math.random() * 700) + 5;

            Point cords = new Point(x, y);
            Dimension size = new Dimension(dSize,dSize);

            for (int i = 0; i < count; i++){

                RectangleList.add(new Rectangles(cords, size, ColorN));

                dSizePrev = dSize;

                if (dSize > 5){
                    dSize -= 10;
                }
                else {
                    dSize = 55;
                }
                size = new Dimension(dSize, dSize);

                xPrev = x;
                yPrev = y;

                x  = (int)(Math.random() * 700) + 5;
                y = (int)(Math.random() * 700) + 5;

                if ((x < (xPrev + dSizePrev + 5)) && (xPrev < (x + dSize + 5)) && (y < (yPrev + dSizePrev) + 5) && (yPrev < (y + dSize + 5))) {
                    while ((x < (xPrev + dSizePrev + 5)) && (xPrev < (x + dSize + 5)) && (y < (yPrev + dSizePrev) + 5) && (yPrev < (y + dSize + 5))) {
                        x = (int) (Math.random() * 695) + 5;
                        y = (int) (Math.random() * 695) + 5;
                    }
                }

                cords = new Point(x,y);
                ColorN ++;
                if (ColorN >= 8){ ColorN = 1; }

            }
        }

        public int getFigureCount(){return figureCount;}

        GameField() {
            Cords = new int[4];
            figureCount = 0;
            maxFigureCount = 42;
            Cords = new int[4];
            Scanner scanner = new Scanner(System.in);
            timer = new Timer(30, this);
            RectangleList = new ArrayList<Rectangles>();
            i = 0;
            CreateFigure(12);
        }

            public void TimerStart(){
                timer.start();
            }
            public void TimerStop(){
                timer.stop();
            }

        @Override
        public void paint(Graphics g){
            super.paint(g);
            try{
                for(int i = 0; i < getFigureCount(); i++) {
                    g.setColor(RectangleList.get(i).getColor());
                    g.drawRect(RectangleList.get(i).getX(), RectangleList.get(i).getY(), (int)RectangleList.get(i).getDimension().getHeight(), (int) RectangleList.get(i).getDimension().getWidth());
                    g.fillRect(RectangleList.get(i).getX(), RectangleList.get(i).getY(), (int)RectangleList.get(i).getDimension().getHeight(), (int) RectangleList.get(i).getDimension().getWidth());
                }
                isPaintActive = !isPaintActive;
            }finally {
                g.dispose();
            }
        }
    }

    public static void main(String[] args) {

            JFrame frame = new JFrame("Moving square");
            frame.setSize(new Dimension(800, 800));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JMenuBar menuBar = new JMenuBar();

            Game g = new Game();
            GameField GameMaster = new GameField();
            GameMaster.setBackground(Color.BLACK);
            frame.setBackground(Color.BLACK);
            frame.add(GameMaster, BorderLayout.CENTER);

            JMenu menu = new JMenu("Menu");

            JMenuItem animate = new JMenuItem("Animate");
            JMenuItem stop = new JMenuItem("Stop");
            JMenuItem quit = new JMenuItem("Quit");

            menu.add(animate);
            menu.add(stop);
            menu.addSeparator();
            menu.add(quit);


            menuBar.add(menu);

            quit.addActionListener(e -> System.exit(1));


            animate.addActionListener(e -> {
                GameMaster.TimerStart();
            });

            stop.addActionListener(e -> {
                GameMaster.TimerStop();

            });

            frame.setJMenuBar(menuBar);
            frame.setVisible(true);
        }

}







