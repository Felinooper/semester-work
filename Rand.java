import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Rand {

    public static void rand(File f, int n) throws IOException {
        FileWriter f1 = new FileWriter(f);
        for (int i = 0; i < n; i++) {
            f1.append(String.valueOf((int) (20 + Math.random() * 400000))).append(" ");
        }
        f1.flush();
    }
}
