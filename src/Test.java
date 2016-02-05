/**
 * Created by spencesouthard on 2/5/16.
 */
public class Test {
    public static void main(String args[]){
        Database.update(1, "Spence", "12656 SE 53ct Belleview, FL 34420");
        System.out.println(Database.list());
    }
}
