import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass("Simon", "Smirnov", 21);
        String filepath = "C:\\text.txt";
        objectIntoFile(myClass, filepath);
        Object obj = fileIntoObject(filepath);
        try {
            Method method = obj.getClass().getDeclaredMethod("printall");
            method.setAccessible(true);
            method.invoke(obj);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void objectIntoFile(Object object, String filepath){
        Field[] fields = object.getClass().getDeclaredFields();

        try (FileOutputStream output = new FileOutputStream(filepath, false)) {
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String value;
                value = field.get(object).toString();
                byte[] out = (fieldName + ": " + value+"\n").getBytes();
                output.write(out);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static Object fileIntoObject(String filepath){
        MyClass myClass = new MyClass();
        try(FileInputStream input = new FileInputStream(filepath)){
            byte[] buffer = new byte[1000];
            if(input.read(buffer)!=-1){
                String string = new String(buffer);
                string = string.substring(0, string.indexOf(0));
                String[] lines = string.split("\n");

                List<String> fields = new ArrayList<>();
                List<String> values = new ArrayList<>();

                for (String s : lines) {
                    String[] line = s.split(": ");
                    fields.add(line[0]);
                    values.add(line[1]);
                }
                for(int i=0;i< fields.size();i++){
                    Field field = myClass.getClass().getDeclaredField(fields.get(i));
                    field.setAccessible(true);
                    if(field.getType().getName().equals("int")){
                        int intField = Integer.parseInt(values.get(i));
                        field.set(myClass, intField);
                    }
                    else {
                        field.set(myClass, values.get(i));
                    }
                }
                return myClass;
            } else{
                throw new IOException("Попытка чтения из пустого файла");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return myClass;
    }
}
