import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class RandomBingoSheetGenerator {
  static Random randGen = new Random(100);
  static String[][] bingoBoard = new String[5][5];
  static String[] bingoOptions = new String[] {"I have taken CS200"};



  public static void main(String[] args){

    GenerateBingoBoard();

  }

  public static void GenerateBingoBoard(){
    for (int i = 0; i < bingoBoard.length; i++){
      for (int j = 0; j < bingoBoard[i].length; j++){
        if (bingoBoard[i][j] != null){
          String bingoOption = GenerateNewRandomOption();
          while (CheckVertical(i,bingoOption) || CheckHorizontal(j, bingoOption)){
            bingoOption = GenerateNewRandomOption();
          }
          bingoBoard[i][j] = bingoOption;
        }
      }
    }
  }

  public static String GenerateNewRandomOption(){
    int x = randGen.nextInt(bingoOptions.length);
    return bingoOptions[x];
  }

  /**
   * Checks the vertical column in the generated bingo sheet for string that
   * is the same as the one that is about to be placed
   *
   * @param j index in the bingo sheet array
   * @param bingoOption string option that is about to be placed on the board
   * @return true id there is a repeat, false if no repeats
   */
  public static boolean CheckHorizontal(int j, String bingoOption){
    for (String[] strings : bingoBoard) {
      if (strings[j] != null) {
        if (strings[j].equals(bingoOption)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks the vertical column in the generated bingo sheet for string that
   * is the same as the one that is about to be placed
   *
   * @param i index in the bingo sheet array
   * @param bingoOption string option that is about to be placed on the board
   * @return true if the there is a repeat, false if no repeats
   */
  public static boolean CheckVertical(int i, String bingoOption){
    for (String[] strings : bingoBoard) {
      if (strings[i] != null) {
        if (strings[i].equals(bingoOption)) {
          return true;
        }
      }
    }
    return false;
  }
}
