import java.util.ArrayList;

public class BoggleBoard {
    ArrayList<String> dictionary = new ArrayList<String>();
    ArrayList<int[]> possiblePathways = new ArrayList<int[]>();
    ArrayList<String> possibleWordList = new ArrayList<String>();
    char[][] board = new char[4][4];
    char[] fBoard = new char[16]; // fBoard stands for "flattened board"

    public boolean isAdjacent(int startNum, int endNum) {
        return (Math.abs(startNum/board.length-endNum/board.length) <= 1 && Math.abs(startNum%board[0].length-endNum%board[0].length) <= 1);
    }

    // Each pathway is a 16 character long string, and pathways will contains all strings of this type.
    public void generateBasePathways() {
        for (int tileIndex = 0; tileIndex < board.length*board[0].length; tileIndex++) {
            fBoard[tileIndex] = board[tileIndex/board.length][tileIndex%board[0].length];
        }
        for (int start = 0; start < fBoard.length; start++) {
            for (int end = 0; end < fBoard.length; end++) {
                if (isAdjacent(start, end)) {
                    int[] branch = {start, end};
                    possiblePathways.add(branch);
                }
            }
        }
    }

    public boolean contains(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return true;
            }
        }
        return false;
    }

    public boolean allOver(ArrayList<int[]> arr, int val) {
        for (int[] element : arr) {
            if (element.length < val) {
                return false;
            }
        }
        return true;
    }
    public ArrayList<int[]> mergePathways(ArrayList<int[]> currentPathways) {
        if (allOver(currentPathways, fBoard.length)) {
            return currentPathways;
        }
        ArrayList<int[]> newPathways = new ArrayList<int[]>();
        for (int[] path : currentPathways) {
            for (int[] path2 : currentPathways) {
                if (path[path.length-1] == path2[0]) {
                    boolean valid = true;
                    for (int i = 1; i < path.length; i++) {
                        if (path.length-1 + i >= fBoard.length) {
                            int[] branch = path;
                            for (int j = 0; j < fBoard.length - path.length; j++) {
                                branch[j+path.length] = path2[j];
                            }
                            valid = false;
                            break;
                        }
                        if (contains(path2, path[i])) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        int[] branch = path;
                        for (int i = 1; i < path2.length; i++) {
                            branch[i+path.length] = path2[i];
                        }
                        newPathways.add(branch);
                    }
                }
            }
        }
        return mergePathways(newPathways);

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

        for (int i = pathway.length()-(subLength+1); i >= 0; i++) {
            if (dictionary.contains(pathway.substring(i, i+subLength))) {
                possibleWordList.add(pathway.substring(i, i+subLength));
            }
        }
        wordSearch(pathway, subLength-1);
    }
}
