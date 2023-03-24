import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Hashing {
    public static void main(String[] args) throws Exception {

        // reading the file
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("EdgarAllanPoeBellsB2022groomed.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        Hashtable<Integer, String> hashedStuff = new Hashtable<Integer, String>();
        ArrayList<HashClass> hashData = new ArrayList<HashClass>();
        ArrayList<String> words = new ArrayList<String>();
        HashMap<String, Integer> hashValues = new HashMap<String, Integer>();

        // variables to keep track for question three
        int c = 123;
        int m = 293;
        int empt = 0;
        int emptStreak = 0;
        int highEmpt = 0;
        int cluster = 0;
        int highValue = 0;
        String string = "";
        int mostocc = 0;
        int mostoccnum = 0;
        int nevempt = 0;
        // reading the text file
        while ((string = br.readLine()) != null) {

            for (String s : string.split("\\s+")) {

                String dile = s.replaceAll("[^'\\-a-zA-Z]*", "");
                words.add(dile);

            }
        }

        for (String str : words) {
            int hash = 0;
            for (Character ch : str.toCharArray()) {
                hash = (hash * c + (int) (ch)) % m;
            }

            int startHash = hash;

            while (hashedStuff.get(hash) != null) {
                if (str.equals(hashedStuff.get(hash)))
                    break;
                ++hash;
                if (startHash == hash) {
                    break;
                }
                if (hash >= 1000) {
                    hash = 0;
                }
            }

            hashedStuff.put(hash, str);

            hashValues.put(str, startHash);

            System.out.println("The word '" + str + "' has a hashing of " + hash);
            hashedStuff.put(hash, str);

            // puts the data in the new hashing class
            hashData.add(new HashClass(hash, startHash, str));
        }
        // makes new type null to hash the values
        for (int M = 0; M < m; ++M) {

            String stuff = hashedStuff.get(M);
            String value;
            if (hashValues.get(stuff) == null) {
                value = "null";
            } else {
                value = hashValues.get(stuff).toString();
            }

            if (value == "null") {
                cluster = 0;
                empt++;
                emptStreak++;
                if (emptStreak > highEmpt) {
                    nevempt = emptStreak;
                } else {
                    nevempt = highEmpt;
                }
                highEmpt = nevempt;
            }

            else {
                emptStreak = 0;
                cluster++;
                if (cluster > highValue) {
                    nevempt = cluster;
                } else {
                    nevempt = highValue;
                }
                highEmpt = nevempt;
            }

            System.out.println("Hash address: " + M + "  Hashed Word: " + stuff + "  Hash Value of Word: " + value);

        }

        HashMap<Integer, Integer> occur = new HashMap<Integer, Integer>();

        for (String key : hashValues.keySet()) {
            int hash = hashValues.get(key);

            if (occur.get(hash) == null) {
                occur.put(hash, 1);
            } else {
                int temp = occur.get(hash);
                occur.put(hash, temp + 1);
            }
        }

        for (Integer hash : occur.keySet()) {
            int value = occur.get(hash);
            if (value > mostocc) {
                mostocc = value;
                mostoccnum = hash;
            }
        }

        HashClass far = new HashClass(0, 0, "");
        int farthestDistance = 0;

        for (HashClass h : hashData) {
            int distance = Math.abs(h.hashad - h.hashkey);
            // half the distance
            if (distance >= 146) {
                distance = 293 - distance;
            }
            if (distance > farthestDistance) {
                far = h;
                farthestDistance = distance;
            }
        }

        System.out.println("Number of empty addresses: " + empt);
        System.out.println("Longest empty streak: " + highEmpt);
        System.out.println("Longest cluster: " + highValue);
        System.out.println("Hash address that has the most disctinct words: " + mostoccnum + " with # " + mostocc
                + " distinct words.");
        System.out.println("Load Factor: " + c / m);
        System.out.println(" Far word: " + far.word + ", Spaces away:" + Math.abs(far.hashkey - far.hashad)
                + ",  orginal hash of:" + far.hashkey);

    }

}
