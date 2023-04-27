package netrox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Affixes {
    public char[] FindPrefix (String metin1, String metin2){
        int sayac = 0;
        int index = FindShortest(metin1, metin2).length();
        char[] meto1, meto2;
        char[] prefix;
        meto1 = metin1.toCharArray();
        meto2 = metin2.toCharArray();
        for (int i = 0; i < index; i++) {
            if (meto1[i] == meto2[i]) {
                sayac++;
            } else {
                break;
            }
        }
        if (sayac > 0 && !metin1.equals(metin2)) {
            prefix = new char[sayac];
            for (int i = 0; i < prefix.length; i++) {
                prefix[i] = meto1[i];
            }
        }
        else {
            prefix = new char[0];
        }
        return prefix;
    }

    public List<String> FindInfix(String metin1, String metin2) {
        String inf = "";
        String[] words1 = {};
        String[] words2 = {};

        if (metin1 != null) {
            words1 = metin1.trim().split("\\s+");
        }
        if (metin2 != null) {
            words2 = metin2.trim().split("\\s+");
        }

        List<String> common = new ArrayList<>();
        List<String> list1 = Arrays.asList(words1);
        List<String> list2 = Arrays.asList(words2);
        List<String> biggest = FindBiggest(list1, list2);
        List<String> smallest = FindSmallest(list1, list2);

        for(int i = 0; i < smallest.size(); i++) {
            if (biggest.contains(smallest.get(i))) {
                common.add(smallest.get(i));
            }
        }
        for (int i = 0; i < common.size(); i++) {
            inf += common.get(i) + " ";
        }
        return common;
    }

    public char[] FindSuffix(String metin1, String metin2) {
        int sayac = 0;
        String shortest = FindShortest(metin1, metin2);
        int index = shortest.length();
        char[] meto1, meto2, shorti;
        meto1 = metin1.toCharArray();
        meto2 = metin2.toCharArray();
        shorti = shortest.toCharArray();
        for (int i = index-1; i >= 0; i--) {
            if (meto1[i] == meto2[i]) {
                sayac++;
            } else {
                break;
            }
        }
        char[] suffix = new char[sayac];
        for (int i = 1; i <= suffix.length; i++) {
            suffix[suffix.length-i] = shorti[shorti.length-i];
        }
        return suffix;
    }

    public String FindShortest(String metin1, String metin2) {
        if (metin1.length() > metin2.length()) {
            return metin2;
        }
        else if (metin1.length() < metin2.length()) {
            return metin1;
        }
        else {
            return metin1;
        }
    }

    public String FindLongest(String metin1, String metin2) {
        if (metin1.length() > metin2.length()) {
            return metin1;
        }
        else if (metin1.length() < metin2.length()) {
            return metin2;
        }
        else {
            return metin2;
        }
    }

    public List<String> FindBiggest(List<String> metin1, List<String> metin2) {
        if (metin1.size() > metin2.size()) {
            return metin1;
        }
        else if (metin1.size() < metin2.size()) {
            return metin2;
        }
        else {
            return metin2;
        }
    }

    public List<String> FindSmallest(List<String> metin1, List<String> metin2) {
        if (metin1.size() > metin2.size()) {
            return metin2;
        }
        else if (metin1.size() < metin2.size()) {
            return metin1;
        }
        else {
            return metin1;
        }
    }
}