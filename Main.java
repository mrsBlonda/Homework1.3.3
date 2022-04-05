import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void openZip (String address, File file) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(address))) {
            ZipEntry entry;
            String name;
            File fos = new File(String.valueOf(file));
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                File newFile = new File(fos, name);
                FileOutputStream fout = new FileOutputStream(newFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String address) {
        GameProgress gamePogress = null;
        try (FileInputStream fis = new FileInputStream(address); ObjectInputStream ois = new ObjectInputStream(fis)) {
            gamePogress = (GameProgress) ois.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gamePogress);
    }

    public static void main(String[] args) {
        File dir = new File("savegames");
        if (dir.mkdir()) {
            System.out.println("Каталог создан");
        }
        String separator = File.separator;
        String userDir = System.getProperty("user.dir");
        String filepath = userDir + separator + "Games" + separator + "savegames" + separator;
        String filename = "save1.dat";


        openZip(filepath + separator + "zip_output.zip", dir);
        openProgress(userDir + separator + "savegames" + separator + filename);



    }

}
