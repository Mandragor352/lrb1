public class SetCollectionTest {
    public static void check(boolean condition, int test){
        if(!condition){
            throw new RuntimeException("Test " + test + " failed");
        } else {
            System.out.println("Test " + test + " passed");
        }
    }
    public static void main(String[] args){
        SetCollection a = new SetCollection(new int[]{1,2,3});
        SetCollection b = new SetCollection(new int[]{3,4,5});
        SetCollection union = a.union(b);

        int test = 1;

        try {
            check(a.contains(2),test++);
            check(!a.contains(5),test++);
            check(a.findMin() == 1,test++);
            check(b.findMax() == 5,test++);
            check(union.eqal(new SetCollection(new int[]{1,2,3,4,5})), test++);

            a.addElement(6);
            check(a.contains(6), test++);

            a.removeElement(1);
            check(!a.contains(1), test++);

            System.out.println("All test passed");
        } catch (RuntimeException e){
            System.out.println("Tests aborted");
            System.exit(1);
        }
    }
}
