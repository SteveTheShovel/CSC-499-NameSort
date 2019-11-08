import java.io.*;
import java.util.Scanner;
public class NameSorter {
    public static class ExpandingNameArray {
        String[] list;
        public ExpandingNameArray() {
            list = new String[0];
        }
        
        public void addName(String name) {
            String[] newList = new String[list.length + 1];
            
            int i;
            for(i = 0; i < list.length; i++) {
                newList[i] = list[i];
            }
            newList[i] = name;
            list = newList;
        }
        
        String dropName(int n) {
            if(list.length > 0) {
                String[] newList = new String[list.length - 1];
                
                for(int i = 0; i < n; i++) {
                    newList[i] = list[i];
                }
                for(int i = n + 1; i < list.length; i++) {
                    newList[i-1] = list[i];
                }
                String holder = list[n];
                list = newList;
                return holder;
            }
            return "";
        }
        
        void exchange(int position1, int position2) {
            String holder = list[position1];
            list[position1] = list[position2];
            list[position2] = holder;
        }
    }
    
    static String trimNonLetters(String oldString) {
        String newString = "";
        
        for (int i = 0; i < oldString.length(); i++) {
            if ((oldString.charAt(i) >= 'A' && oldString.charAt(i) <= 'Z') || (oldString.charAt(i) >= 'a' && oldString.charAt(i) <= 'z')) {
                newString += oldString.charAt(i);
            }
        }
        return newString;
    }
    
    
    static String[] extractNames(File text) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(text));
        ExpandingNameArray names = new ExpandingNameArray();
        String st;
        
        while ((st = br.readLine()) != null) {
            st = trimNonLetters(st);
            if(!st.equals(""))
                names.addName(st);
        }
        
        for (int i = 0; i < names.list.length; i++) {
            //System.out.println(names.list[i]);
        }
        return names.list;
    }
    
    static String[] sortNames(String[] oldOrder) {
        String[] newOrder = new String[oldOrder.length];
        
        int i = 0;
        //sort according to length
        while(i < oldOrder.length) {
            for(int j = 1; i < oldOrder.length; j++) {
                for(int k = 0; k < oldOrder.length; k++) {
                    if (oldOrder[k].length() == j) {
                        newOrder[i] = oldOrder[k];
                        i++;
                    }
                }
            }
        }
        String holder;
        for(int k = 0; k < newOrder.length; k++) {
            for(int j = k; j < newOrder.length && newOrder[j].length() == newOrder[k].length(); j++) {
                if(wordIsFirstEQ(newOrder[j].toLowerCase(), newOrder[k].toLowerCase())) {
                    holder = newOrder[j];
                    newOrder[j] = newOrder[k];
                    newOrder[k] = holder;
                    j = k;
                }
            }
        }
        return newOrder;
    }
    
    static boolean wordIsFirstEQ(String first, String second) {
        for(int i = 0; i < first.length(); i++) {
            if(second.charAt(i) < first.charAt(i)) {
                return false;
            }
            else if(second.charAt(i) > first.charAt(i)) {
                return true;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.print("Enter path to file to be sorted: ");
        String path = in.nextLine();
        path.replace("\\", "\\\\");
        //"C:\Users\STEVE\Documents\CSC 499\Developer Exam Resources\Sort Me.txt"
        File file = new File(path);
        
        String[] entries = new String[0];
        entries = extractNames(file);
        
        entries = sortNames(entries);
        
        
        for(int i = 0; i < entries.length; i++) {
            System.out.println(entries[i]);
        }
    }
    
}
