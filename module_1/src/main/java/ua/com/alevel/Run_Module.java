package ua.com.alevel;

import ua.com.alevel.level1.Generic_Symbols;
import ua.com.alevel.level1.Chess_Horse;
public class Run_Module {
    public static void main(String[] args) {
        Generic_Symbols generic_symbols = new Generic_Symbols();
        generic_symbols.outputConsole();
        Chess_Horse chess_horse = new Chess_Horse();
        chess_horse.outputConsole();
    }
}
