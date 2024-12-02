package com.cliframe;

import java.util.ArrayList;

public class Frame {

    private int height, width;
    private char[] border;

    private char[][] window;

    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public char[] getBorder() { return border; }

    // Builder
    public Frame(int height, int width, char[] border) {
        this.height = height;
        this.width = width;
        this.border = border;
        window = newFrame(this.height, this.width, this.border);
    }
    private char[][] newFrame(int height, int width, char[] border) {
        char[][] window = new char[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                window[i][j] = border[Piece.empty.ordinal()];
                if((i == 0) || (i == height-1))         { window[i][j] = border[Piece.horizont.ordinal()]; } 
                if((j == 0) || (j == width-1))          { window[i][j] = border[Piece.vertical.ordinal()]; } 
                if((i == 0) && (j == 0))                { window[i][j] = border[Piece.tlCorner.ordinal()]; } 
                if((i == 0) && (j == width-1))          { window[i][j] = border[Piece.trCorner.ordinal()]; }
                if((i == height-1) && (j == 0))         { window[i][j] = border[Piece.blCorner.ordinal()]; }
                if((i == height-1) && (j == width-1))   { window[i][j] = border[Piece.brCorner.ordinal()]; }
            }
        }
        return window;
    }

    // Reset
    public Frame empty() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                window[i][j] = border[Piece.empty.ordinal()];
            }
        }
        return this;
    } public Frame empty(String mode) {
        if(mode.equalsIgnoreCase("keepBorderIntact")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(!((i == 0) || (i == height-1)) && !((j == 0) || (j == width-1))) {
                        window[i][j] = border[Piece.empty.ordinal()];
                    }
                }
            }
        } else if(mode.equalsIgnoreCase("keepBorderString")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(((i == 0) || (i == height-1)) || ((j == 0) || (j == width-1))) {
                        if((window[i][j] == border[Piece.lDivider.ordinal()]) || (window[i][j] == border[Piece.rDivider.ordinal()])) { window[i][j] = border[Piece.vertical.ordinal()]; }
                    } else { window[i][j] = border[Piece.empty.ordinal()]; }
                }
            }
        } else if(mode.equalsIgnoreCase("keepBorderChar")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(!(((i == 0) || (i == height-1)) || ((j == 0) || (j == width-1)))) {
                        if(window[i][j] != border[Piece.horizont.ordinal()]) { window[i][j] = border[Piece.empty.ordinal()]; }
                    }
                }
            }
        }
        return this;
    } public Frame empty(int line) {
        for(int j = 0; j < width; j++) {
            if(!(((line == 0) || (line == height-1)) || ((j == 0) || (j == width-1)))) {
                if(window[line][j] != border[Piece.horizont.ordinal()]) { window[line][j] = border[Piece.empty.ordinal()]; }
            }
        }
        return this;
    }

    // Util String
    public Frame setWindowXY(int x, int y, char input) { 
        if((x < width) && (y < height)) { window[y][x] = input;  }
        return this;
    }
    public Frame setWindowString(int x, int y, String input) {
        for(int i = 0; i < input.length(); i++) {
            setWindowXY(x+i, y, input.toCharArray()[i]);
        }
        return this;
    } public Frame setWindowString(int y, boolean center, String input) {
        int x = 0;
        System.out.println();
        if((width-2)-input.length() == 0) { x = 1; } 
        else { x = (((width-2)-input.length())/2)+1; } // rounds down
        if(y < 0) y = 0; // avoids errors if string is longer than width
        for(int i = 0; i < input.length(); i++) {
            setWindowXY(x+i, y, input.toCharArray()[i]);
        }
        return this;
    } 

    public Frame setWindowEmptyString(int x, int y, int length) {
        for(int i = 0; i < length; i++) { setWindowXY(x+i, y, border[Piece.empty.ordinal()]); }
        return this;
    }

    // Util x center
    public Frame centerLinesVertically(ArrayList<centerXLine> lines) {
        int offset = (height/2)-(lines.size()/2);
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).getCenter()) this.setWindowString(lines.get(i).getX()+offset, true, lines.get(i).getInput());
            else this.setWindowString(lines.get(i).getX()+offset, lines.get(i).getY(), lines.get(i).getInput());
        }
        return this;
    }

    public static class centerXLine {
        private int x;
        private int y;
        private boolean center;
        private String input;

        public int getX() { return x; }
        public int getY() { return y; }
        public boolean getCenter() { return center; }
        public String getInput() { return input; }

        public centerXLine(int x, boolean center, String input) {
            this.x = x;
            this.center = center;
            this.input = input;
        } public centerXLine (int x, int y, String input) {
            this.x = x;
            this.y = y;
            this.input = input;
        }
    }

    // Util divisor
    public Frame divider(int y) {
        for(int i = 0; i < width; i++) {
            if(i == 0) { setWindowXY(i, y, border[Piece.lDivider.ordinal()]); }
            else if(i == width-1) { setWindowXY(i, y, border[Piece.rDivider.ordinal()]); }
            else { setWindowXY(i, y, border[Piece.horizont.ordinal()]); }
        }
        return this;
    }

    public Frame print() {
        System.out.print("\033c\033[2J");
        for(int i = 0; i < window.length; i++) {
            String line = "";
            for(int j = 0; j < window[i].length; j++) {
                line += window[i][j];
            }
            System.out.print(line+"\n");
        } 
        return this;
    }

    // Border Builder
    // https://en.wikipedia.org/wiki/Box-drawing_characters
    public static char[] borderBuilder(int mode) { // TODO update cliFrame on github + add self center with array [[y+offset, x, text],[y+offset, x, text]]
        char[] border = new char[9];
        if(mode == 0) {
            border[Piece.horizont.ordinal()] = '─'; border[Piece.vertical.ordinal()] = '│'; border[Piece.tlCorner.ordinal()] = '┌';
            border[Piece.trCorner.ordinal()] = '┐'; border[Piece.blCorner.ordinal()] = '└'; border[Piece.brCorner.ordinal()] = '┘';
            border[Piece.lDivider.ordinal()] = '├'; border[Piece.rDivider.ordinal()] = '┤'; border[Piece.empty.ordinal()] = ' ';
        } else if(mode == 1) {
            border[Piece.horizont.ordinal()] = '═'; border[Piece.vertical.ordinal()] = '║'; border[Piece.tlCorner.ordinal()] = '╔';
            border[Piece.trCorner.ordinal()] = '╗'; border[Piece.blCorner.ordinal()] = '╚'; border[Piece.brCorner.ordinal()] = '╝';
            border[Piece.lDivider.ordinal()] = '╠'; border[Piece.rDivider.ordinal()] = '╣'; border[Piece.empty.ordinal()] = ' ';
        }
        return border;
    }

    private enum Piece {
        horizont,
        vertical,
        tlCorner,
        trCorner,
        blCorner,
        brCorner,
        lDivider,
        rDivider,
        empty
    }
}
