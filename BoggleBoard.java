import java.util.ArrayList;

public class BoggleBoard {
    ArrayList<String> dictionary = new ArrayList<String>();
    ArrayList<String> possibleWordList = new ArrayList<String>();
    char[][] board = new char[4][4];

    public void generatePathways(String currWord, char[] checkedNeighbors, int startIndex) {
        // if (currWord.equalsIgnoreCase("")) {
        //     return;
        // }
        // boolean valid = false;
        // for (String word : dictionary) {
        //     if (word.substring(0, currWord.length()).equalsIgnoreCase(currWord)) {
        //         valid = true;
        //         if (currWord.equalsIgnoreCase(word)) {
        //             possibleWordList.add(word);
        //         }
        //     }
        // }
        // if (!valid) {
        //     return;
        // }
        // for (int i = 0; i < board.length; i++) {
        //     for (int j = 0; j < board[i].length; j++) {
        //         if (Math.abs(startIndex%4 - j) <= 1 && Math.abs(startIndex/4 - i) <= 1 && !contains(checkedNeighbors, board[i][j])) {

        //         }
        //     }
        // }
    }

    public void wordSearch(String pathway, int subLength) {
        if (subLength <= 3) {
            return;
        }
        for (int i = 0; i < pathway.length() - subLength; i++) {
            if (dictionary.contains(pathway.substring(i, i+subLength))) {
                possibleWordList.add(pathway.substring(i, i+subLength));
            }
        }
        wordSearch(pathway, subLength-1);
    }
}
