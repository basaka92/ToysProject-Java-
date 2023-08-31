package model;
import java.io.*;

public class FileHandler{

    public Object readShop(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path));
        Object obj = objectInputStream.readObject();
        objectInputStream.close();
        return obj;
    }
    public void saveShop(Object obj, String path) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path));
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
    }
    public void savePrize(String str, String path) throws IOException {
        FileWriter writer = new FileWriter(path, true);
        writer.write(str);
        writer.append('\n');
        writer.flush();
        writer.close();
    }
}

