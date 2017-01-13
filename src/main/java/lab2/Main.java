package lab2;

/**
 * Created by ihb on 28.12.16.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Order false");

        MyLinkedHashMap<String, Integer> j = new MyLinkedHashMap<>(false);
        System.out.println(j.size());
        j.put("Hello", 1);
        j.put("Hell", 1);
        j.put("Java", 1);
        j.put("Hell", 5);
        j.put("Hell", 5);
        System.out.println(j.size());




        j.get("Hell");
        j.get("Hello");
        j.containsKey("Hello");
        j.containsKey("AAAAAAAAAAAAAAAAAA");
        j.remove("Me");
        System.out.println(j.size());

        j.remove("Hello");
        System.out.println(j.size());
        j.put("Hello", 1);
        System.out.println(j.size());


        System.out.println(j.entrySet());





        System.out.println("Order true");
        MyLinkedHashMap<String, Integer> a = new MyLinkedHashMap<>(true);

        a.put("first", 1);
        a.put("second", 2);
        a.put("third", 3);
        a.put("fourth", 4);


        System.out.println(a.entrySet());

        a.put("second", 2);

        System.out.println(a.entrySet());

        a.get("third");

        System.out.println(a.entrySet());





    }
}
