package main;

public class CrossWord {
    public int countWords(String[] board, int size) {
        int count = 0;
        for (String row :
                board) {
            int counting = 0;
            char[] charArray = row.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char currentChar = charArray[i];
                if (currentChar == '.') {
                    if (counting > 0) {
                        ++counting;
                    } else {
                        counting = 1;
                    }
                }
                if (currentChar == 'X' || i == charArray.length - 1){
                    if (counting >= 0) {
                        if (counting == size) {
                            ++count;
                        }
                        counting = -1;
                    }
                }
            }
        }
        return count;
    }
}
