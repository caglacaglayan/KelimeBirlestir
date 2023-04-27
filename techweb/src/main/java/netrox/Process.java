package netrox;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Process {
    public DatabaseConnection connection = new DatabaseConnection();
    Affixes affixes = new Affixes();
    public static Document sampleDoc;
    public static String birlesikMetin;
    public static long start, end;
    public static double gecenSure;

    public Process() {
    }

    public int GetLastId() {
        if (connection.col.countDocuments() > 0) {
            Document last = (Document) connection.col.find().sort(new Document("_id", -1)).first();
            assert last != null;
            BasicDBObject obj = new BasicDBObject(last);
            return Integer.parseInt(String.valueOf(obj.get("_id")));
        } else {
            return 0;
        }
    }

    public String Combine(List<String> metinler) {
        birlesikMetin = CombineWords(metinler.get(0), metinler.get(1));
        for (int i = 2; i < metinler.size(); i++) {
            birlesikMetin = CombineWords(birlesikMetin, metinler.get(i));
        }
        return birlesikMetin;
    }

    public String CombineWords(String metin1, String metin2) {
        start = System.nanoTime();

        List<String> combination = new ArrayList<>();
        StringBuilder combi = new StringBuilder();

        String[] words1 = {};
        String[] words2 = {};

        if (metin1 != null) {
            words1 = metin1.trim().split("\\s+");
        }
        if (metin2 != null) {
            words2 = metin2.trim().split("\\s+");
        }

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> common = affixes.FindInfix(metin1, metin2);

        char[] prefix;
        List<String> prefixes = new ArrayList<>();
        String prefixs;

        if (common != null) {
            for (int i = 0; i < words1.length; i++) {
                if (!common.contains(words1[i])) {
                    list1.add(words1[i]);
                }
            }
            for (int i = 0; i < words2.length; i++) {
                if (!common.contains(words2[i])) {
                    list2.add(words2[i]);
                }
            }
            if (!list2.isEmpty() && !list1.isEmpty()) {
                common.addAll(list2);
                list1.addAll(common);
                combination = list1;
            }
            if (!list1.isEmpty() && list2.isEmpty() ) {
                list1.addAll(common);
                combination = list1;
            }
            if (list1.isEmpty() && !list2.isEmpty() ) {
                common.addAll(list2);
                combination = common;
            }
            if (list1.isEmpty() && list2.isEmpty() ) {
                if (!list1.containsAll(list2)) {
                    list1.addAll(list2);
                }
                combination = list1;
            }
        }
        else {
            list1.addAll(list2);
            combination = list1;
        }

        for (int i = 0; i < combination.size(); i++) {
            for (int j = i+1; j < combination.size(); j++) {
                prefix = affixes.FindPrefix(combination.get(i), combination.get(j));
                if (prefix.length > 1) {
                    prefixs = String.valueOf(prefix);
                    prefixes.add(prefixs);
                }
            }
        }

        for (int i = 0; i < combination.size(); i++) {
            if (!prefixes.contains(combination.get(i))) {
                if (i == combination.size()-1) {
                    combi.append(combination.get(i));
                    break;
                }
                combi.append(combination.get(i)).append(" ");
            }
        }

        end = System.nanoTime();
        return combi.toString();
    }

    public boolean CheckEquality(String metin1, String metin2) {
        if (metin1 != null && metin2 != null) {
            if (metin1.equals(metin2)) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public double CalculateTime () {
        DecimalFormat df = new DecimalFormat("0.00");
        long sureL = (end - start);
        gecenSure = (double) sureL;
        gecenSure = gecenSure / 1000; // ms
        String sure = df.format(gecenSure);
        String str = sure.replace(",", ".");
        gecenSure = Double.parseDouble(str);
        return gecenSure;
    }

    public void SetRecordValues (Record record) {
        record.SetId();
        for (int i = 0; i < record.adet; i++) {
            record.SetMetin(record.metinler.get(i), i);
        }
        record.SetBirlesikMetin(Combine(record.GetMetinler()));
        record.SetGecenSure(CalculateTime());
        record.SetSonuc(CheckEquality(record.metinler.get(0), record.metinler.get(1)));
    }

    public void SaveRecord (Record record) {
        if (record.adet > 1) {
            sampleDoc = new Document("_id", record._id);
            for (int i = 0; i < record.adet; i++) {
                sampleDoc.append("metin" + (i + 1), record.metinler.get(i));
            }
            sampleDoc.append("birlesikMetin", record.birlesikMetin)
                    .append("gecenSure", record.gecenSure)
                    .append("sonuc", record.sonuc);

            connection.col.insertOne(sampleDoc);
        }
    }

    public List<String> ListRecords() {
        List<String> records = new ArrayList<>();
        String record;
        List<Document> docs = ListDocuments();
        BasicDBObject obj;

        for (int i = 0; i < docs.size(); i++) {
            obj = new BasicDBObject(docs.get(i));
            record = obj.toJson();
            records.add(record);
        }
        return records;
    }

    public List<Document> ListDocuments() {
        List<Document> docs = new ArrayList<>();
        Document doci;
        MongoCollection<Document> collection = connection.col;
        FindIterable<Document> iter = collection.find();
        Iterator<Document> it = iter.iterator();
        while (it.hasNext()) {
            doci = it.next();
            docs.add(doci);
        }
        return docs;
    }
}