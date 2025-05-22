import java.lang.annotation.*;
import java.lang.reflect.Method;

// ==== Анотація MethodInfo ====
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodInfo {
    String name();
    String returnType();
    String description();
}

// ==== Анотація Author ====
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Author {
    String firstName();
    String lastName();
}

// ==== Клас ArrayUtils з анотаціями ====
class ArrayUtils {

    @MethodInfo(name = "sumArray", returnType = "int", description = "Returns the sum of all elements in an array")
    @Author(firstName = "Іван", lastName = "Іванов")
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int num : array) sum += num;
        return sum;
    }

    @MethodInfo(name = "maxElement", returnType = "int", description = "Returns the maximum element in an array")
    @Author(firstName = "Олена", lastName = "Петренко")
    public static int maxElement(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) max = num;
        }
        return max;
    }
}

// ==== Демонстрація використання анотацій ====
public class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = {4, 2, 7, 1};

        System.out.println("Sum: " + ArrayUtils.sumArray(arr));
        System.out.println("Max: " + ArrayUtils.maxElement(arr));

        // Виведення інформації про методи та анотації
        Method[] methods = ArrayUtils.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)) {
                MethodInfo info = method.getAnnotation(MethodInfo.class);
                Author author = method.getAnnotation(Author.class);

                System.out.println("\nMethod: " + method.getName());
                System.out.println("Name: " + info.name());
                System.out.println("Return Type: " + info.returnType());
                System.out.println("Description: " + info.description());
                System.out.println("Author: " + author.firstName() + " " + author.lastName());
            }
        }
    }
}
