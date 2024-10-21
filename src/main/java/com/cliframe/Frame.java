package com.cliframe;

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
                window[i][j] = border[8];
                if((i == 0) || (i == height-1))         { window[i][j] = border[0]; } 
                if((j == 0) || (j == width-1))          { window[i][j] = border[1]; } 
                if((i == 0) && (j == 0))                { window[i][j] = border[2]; } 
                if((i == 0) && (j == width-1))          { window[i][j] = border[3]; }
                if((i == height-1) && (j == 0))         { window[i][j] = border[4]; }
                if((i == height-1) && (j == width-1))   { window[i][j] = border[5]; }
            }
        }
        return window;
    }

    // Reset
    public Frame empty() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                window[i][j] = border[8];
            }
        }
        return this;
    } public Frame empty(String mode) {
        if(mode.equalsIgnoreCase("keepBorderIntact")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(!((i == 0) || (i == height-1)) && !((j == 0) || (j == width-1))) {
                        window[i][j] = border[8];
                    }
                }
            }
        } else if(mode.equalsIgnoreCase("keepBorderString")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(((i == 0) || (i == height-1)) || ((j == 0) || (j == width-1))) {
                        if((window[i][j] == border[6]) || (window[i][j] == border[7])) { window[i][j] = border[1]; }
                    } else { window[i][j] = border[8]; }
                }
            }
        } else if(mode.equalsIgnoreCase("keepBorderChar")) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    if(!(((i == 0) || (i == height-1)) || ((j == 0) || (j == width-1)))) {
                        if(window[i][j] != border[0]) { window[i][j] = border[8]; }
                    } else { 
                        
                    }
                }
            }
        }
        return this;
    }

    // Util String
    public Frame setWindowXY(int y, int x, char input) { 
        if((x < width) && (y < height)) { window[y][x] = input;  }
        return this;
    }
    public Frame setWindowString(int y, int x, String input) {
        for(int i = 0; i < input.length(); i++) {
            setWindowXY(y, x+i, input.toCharArray()[i]);
        }
        return this;
    } public Frame setWindowString(int y, boolean center, String input) {
        int x = 0;
        System.out.println();
        if((width-2)-input.length() == 0) { x = 1; } 
        else { x = (((width-2)-input.length())/2)+1; } // rounds down
         
        for(int i = 0; i < input.length(); i++) {
            setWindowXY(y, x+i, input.toCharArray()[i]);
        }
        return this;
    } 

    public Frame setWindowEmptyString(int y, int x, int length) {
        for(int i = 0; i < length; i++) { setWindowXY(y, x+i, border[8]); }
        return this;
    }

    // Util divisor
    public Frame divider(int y) {
        for(int i = 0; i < width; i++) {
            if(i == 0) { setWindowXY(y, i, border[6]); }
            else if(i == width-1) { setWindowXY(y, i, border[7]); }
            else { setWindowXY(y, i, border[0]); }
        }
        return this;
    }

    public void print() {
        System.out.print("\033c\033[2J");
        for(int i = 0; i < window.length; i++) {
            String line = "";
            for(int j = 0; j < window[i].length; j++) {
                line += window[i][j];
            }
            System.out.print(line+"\n");
        } 
    }

    // Border Builder
    // https://en.wikipedia.org/wiki/Box-drawing_characters
    public static char[] borderBuilder(int mode) {
        char[] border = new char[9];
        if(mode == 0) {
            border[0] = '─'; border[1] = '│'; border[2] = '┌';
            border[3] = '┐'; border[4] = '└'; border[5] = '┘';
            border[6] = '├'; border[7] = '┤'; border[8] = ' ';
        } else if(mode == 1) {
            border[0] = '═'; border[1] = '║'; border[2] = '╔';
            border[3] = '╗'; border[4] = '╚'; border[5] = '╝';
            border[6] = '╠'; border[7] = '╣'; border[8] = ' ';
        }
        return border;
    }
}
