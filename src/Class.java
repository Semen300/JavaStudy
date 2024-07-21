public class Class {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age<120 && age>0) {
            this.age = age;
        }
        else System.out.println("Enter valid age between 0 and 120");
    }
}
