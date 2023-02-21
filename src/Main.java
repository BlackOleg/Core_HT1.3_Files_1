import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder("начинаем log писать: \n");
        //Создаем 4 каталога
        final String dirIni = "C:\\Games\\";
        String[] dirs = {"src", "res", "savegames", "temp"};
        for (String s : dirs) {
            log.append(makeDir(dirIni + s));
            //log.append("\n");
        }
        //Создаем src
        final String dir1 = dirIni + "src\\";
        for (String s : new String[]{"main", "test"}) {
            log.append(makeDir(dir1 + s));
            //log.append("\n");
        }
        //Создаем main
        final String subdir = dir1 + "main\\";
        for (String s : new String[]{"Main.java", "Utils.java"}) {
            log.append(makeFile(subdir + s));
            //log.append("\n");
        }
        //Создаем res
        final String dir2 = dirIni + "res\\";
        for (String s : new String[]{"drawables", "vectors", "icons"}) {
            log.append(makeDir(dir2 + s));
            //log.append("\n");
        }
        //Создаем temp
        final String tempdir = dirIni + "temp\\";
        log.append(makeFile(tempdir + "temp.txt"));
        log.append("\n");
        //Заполняем log
        makeLog(tempdir + "temp.txt", log.toString());
    }

    static String makeDir(String dir) {
        if (new File(dir).mkdir()) {
            return "Каталог " + dir + " создан" + "\n";
        } else {
            return "Каталог " + dir + " уже  сущеcтвует или ошибка" + "\n";
        }
    }

    static String makeFile(String filename) {
        File myFile = new File(filename);
        String log = "Файл " + filename + " существует уже"+ "\n";;
        try {
            if (myFile.createNewFile())
                log = filename + " был создан" + "\n";;
        } catch (IOException ex) {
            log = "файл не создан, потому как: " + ex.getMessage() + "\n";;
        }
        return log;

    }

    static void makeLog(String fileName, String text) {
        byte[] buffer = text.getBytes();
        // создаем выходной байтовый поток и передаем его в выходной буферизированный поток
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            // производим запись от 0 до последнего байта из массива
            bos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println("Что-то пошло не так " + ex.getMessage());
        }
    }
}