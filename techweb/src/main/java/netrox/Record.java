package netrox;

import java.util.ArrayList;
import java.util.List;

public class Record {
    Process process = new Process();
    public int _id;
    public int adet;
    public List<String> metinler = new ArrayList<>();
    public String birlesikMetin;
    public double gecenSure;
    public boolean sonuc;

    public Record() {}

    public Record(int __id, int _adet, List<String> _metinler, String _birlesikMetin, double _gecenSure, boolean _sonuc) {
        _id = __id;
        adet = _adet;
        metinler = _metinler;
        birlesikMetin = _birlesikMetin;
        gecenSure = _gecenSure;
        sonuc = _sonuc;
    }

    public int GetId() {
        return _id;
    }
    public int GetAdet() {
        return adet;
    }
    public List<String> GetMetinler() {
        return metinler;
    }
    public String GetMetin(int index) {
        return metinler.get(index);
    }
    public String GetMetin1() {
        return metinler.get(0);
    }
    public String GetMetin2() {
        return metinler.get(1);
    }
    public String GetMetin3() {
        return metinler.get(2);
    }
    public String GetMetin4() {
        return metinler.get(3);
    }
    public String GetMetin5() {
        return metinler.get(4);
    }
    public String GetBirlesikMetin() {
        return birlesikMetin;
    }
    public double GetGecenSure() {
        return gecenSure;
    }
    public boolean GetSonuc() {
        return sonuc;
    }

    public void SetId() {
        _id = process.GetLastId()+1;
    }
    public void SetAdet(int _adet) {
        adet = _adet;
    }
    public void SetMetinler(List<String> _metinler) {
        metinler = _metinler;
    }
    public void SetMetin(String _metin, int index) {
        metinler.set(index, _metin);
    }
    public void SetMetin1(String _metin1) {
        metinler.set(0, _metin1);
    }
    public void SetMetin2(String _metin2) {
        metinler.set(1, _metin2);
    }
    public void SetMetin3(String _metin3) {
        metinler.set(2, _metin3);
    }
    public void SetMetin4(String _metin4) {
        metinler.set(3, _metin4);
    }
    public void SetMetin5(String _metin5) {
        metinler.set(4, _metin5);
    }
    public void SetBirlesikMetin(String _birlesikMetin) {
        birlesikMetin = _birlesikMetin;
    }
    public void SetGecenSure(double _gecenSure) {
        gecenSure = _gecenSure;
    }
    public void SetSonuc(boolean _sonuc) {
        sonuc = _sonuc;
    }
}