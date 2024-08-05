public class MyClass {
    private String name;
    private String surname;
    private int age;

    MyClass(){}
    MyClass(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age<120 && age>0) {
            this.age = age;
        }
        else System.out.println("Enter valid age between 0 and 120");
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void printall(){
        System.out.println("Hello, my name is "+this.name+" "+this.surname+", I am "+this.age+" years old");
    }
}
