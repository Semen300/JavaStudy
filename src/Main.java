import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        Class myClass = new Class();
        myClass.setAge(21);
        System.out.println("Age, set by setter: "+myClass.getAge());
        try {
            Field age = myClass.getClass().getDeclaredField("age");
            age.setAccessible(true);
            age.set(myClass, 121);
        } catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println("Age, set by reflection: "+myClass.getAge());

        try {
            Field age = myClass.getClass().getDeclaredField("age");
            age.setAccessible(true);
            System.out.println("Age, gotten by reflection: "+age.get(myClass));
        } catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

    }
}