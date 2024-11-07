public class CandyTest {
    public static void main(String[] args) {
        Candy noSugarCandy = new Candy("No Sugar", 0, "soft");
        System.out.println(noSugarCandy);
        noSugarCandy.lessSugarGrams();
        System.out.println(noSugarCandy);
        
        Candy negativeSugarCandy = new Candy("Error", -5, "hard");
        System.out.println(negativeSugarCandy);
        negativeSugarCandy.lessSugarGrams();
        System.out.println(negativeSugarCandy);
        
        Candy lessSugarCandy = new Candy("5G", 5, "hard");
        System.out.println(lessSugarCandy);
        lessSugarCandy.lessSugarGrams();
        System.out.println(lessSugarCandy);
        
        Candy sourPatchKids = new Candy("Sour Patch Kids", 24, "hard and sweet");
        System.out.println(sourPatchKids);
        sourPatchKids.lessSugarGrams();
        System.out.println(sourPatchKids);
    }
}
