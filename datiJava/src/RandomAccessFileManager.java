import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileManager {
    private RandomAccessFile raf;

    public RandomAccessFileManager(String file, String permissions) {
        try {
            raf = new RandomAccessFile(file, permissions);
        } catch (FileNotFoundException e) {}
    }

    public void index(long index) {
        try {
            raf.seek(index);
        } catch (IOException e) {}
    }

    public byte[] read(int length) {
        try {
            byte[] b = new byte[length];
            raf.read(b);
            return b;
        } catch (IOException e) {}
        return null;
    }

    public void write(String string) {
        try {
            raf.write(string.getBytes());
        } catch (IOException e) {}
    }

    public String findLine(String key, int lineLength) {
        index(0);
        String s = new String(read(lineLength));
        while (!s.contains("\u0000" + "")) {
            if (s.contains(key)) {
                return s;
            }
            s = new String(read(lineLength));
        }
        return "";
    }

    public void close() {
        try {
            raf.close();
        } catch (IOException e) {}
    }
}