import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {
        HashTable<MyTestingClass, String> table = new HashTable<>(50);

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(id, name);
            String value = "Value" + i;
            table.put(key, value);
        }

        int[] bucketSizes = table.getBucketSizes();
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }
    }
}
