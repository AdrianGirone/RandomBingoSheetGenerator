import java.io.*;
import java.util.*;

public class RandomBingoSheetGenerator {
  static Random randGen = new Random(100);
  static String[][] bingoBoard = new String[5][5];
  static ArrayList<String> bingoOptions;

  /**
   * Main method
   *
   * @param args input arguments if any
   */
  public static void main(String[] args){
    StartGeneratingBingoBoard();
  }

  /**
   * Runs the program in the proper order
   */
  public static void StartGeneratingBingoBoard(){
    try{
      File file = new File("BingoOptions.csv");
      bingoOptions = (ArrayList<String>) LoadCsvToArraylist(file);
    }catch (IOException e1){
      e1.printStackTrace();
    }
    GenerateBingoBoard();
    try{
      SaveToCsv();
    }catch (IOException e1){
      e1.printStackTrace();
    }
  }

  /**
   * Loads .csv file to arraylist
   *
   * @param file file for reference
   * @return arraylist containing the .csv files
   * @throws IOException if file does not exist or improper data format
   */
  public static List<String> LoadCsvToArraylist(File file) throws IOException {
    List<String> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("BingoOptions.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        records.add(Arrays.toString(line.split(",")));
      }
    }
    return records;
  }

  /**
   * Save the BingoBoard array to .csv file
   *
   * @throws IOException if file does not exist or improper data format
   */
  public static void SaveToCsv() throws IOException {
    FileWriter fileWriter = null;
    File csvFile = new File("bingoBoard.csv");
    fileWriter = new FileWriter(csvFile);

    for (String[] data : bingoBoard) {
      StringBuilder line = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        line.append("\"");
        line.append(data[i].replaceAll("\"", "\"\""));
        line.append("\"");
        if (i != data.length - 1) {
          line.append(',');
        }
      }
      line.append("\n");
      fileWriter.write(line.toString());
    }
    fileWriter.close();
  }

  /**
   * Fills new bingo board
   */
  public static void GenerateBingoBoard(){
    for (int i = 0; i < bingoBoard.length; i++){
      for (int j = 0; j < bingoBoard[i].length; j++){
        if (bingoBoard[i][j] == null){
          String bingoOption = GenerateNewRandomOption();
          while (CheckVertical(i,bingoOption) || CheckHorizontal(j, bingoOption)){
            bingoOption = GenerateNewRandomOption();
          }
          bingoBoard[i][j] = bingoOption;
        }
      }
    }
  }

  /**
   * Generates new random number and inputs that as index in bingoOptions Arraylist
   *
   * @return random bingo option
   */
  public static String GenerateNewRandomOption(){
    int x = randGen.nextInt(bingoOptions.size());
    return bingoOptions.get(x);

    /*
    int x = randGen.nextInt(bingoOptions.length);
    return bingoOptions[x];

     */
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
