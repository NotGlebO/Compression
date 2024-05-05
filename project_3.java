import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("comp, decomp, size, equal, about, exit");
                String command = reader.readLine();

                switch (command) {
                    case "comp":
                        compressFile(reader);
                        break;
                    case "decomp":
                        decompressFile(reader);
                        break;
                    case "size":
                        getFileSize(reader);
                        break;
                    case "equal":
                        checkEquality(reader);
                        break;
                    case "about":
                        printAbout();
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Invalid command, please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compressFile(BufferedReader reader) throws IOException {
        System.out.println("Enter source file name:");
        String sourceFileName = reader.readLine();

        System.out.println("Enter archive name:");
        String archiveName = reader.readLine();

        try (FileInputStream fis = new FileInputStream(sourceFileName);
             FileOutputStream fos = new FileOutputStream(archiveName);
             DeflaterOutputStream dos = new DeflaterOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("File compressed successfully.");
    }

    private static void decompressFile(BufferedReader reader) throws IOException {
        System.out.println("Enter archive name:");
        String archiveName = reader.readLine();

        System.out.println("Enter file name:");
        String fileName = reader.readLine();

        try (FileInputStream fis = new FileInputStream(archiveName);
             InflaterInputStream iis = new InflaterInputStream(fis);
             FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = iis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("File decompressed successfully.");
    }

    private static void getFileSize(BufferedReader reader) throws IOException {
        System.out.println("Enter file name:");
        String fileName = reader.readLine();

        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("Size: " + file.length() + " bytes");
        } else {
            System.out.println("File not found.");
        }
    }

    private static void checkEquality(BufferedReader reader) throws IOException {
        System.out.println("Enter first file name:");
        String firstFileName = reader.readLine();

        System.out.println("Enter second file name:");
        String secondFileName = reader.readLine();

        boolean isEqual = compareFiles(firstFileName, secondFileName);
        System.out.println("Files are equal: " + isEqual);
    }

    private static boolean compareFiles(String firstFileName, String secondFileName) throws IOException {
        FileInputStream fis1 = new FileInputStream(firstFileName);
        FileInputStream fis2 = new FileInputStream(secondFileName);
        BufferedInputStream bis1 = new BufferedInputStream(fis1);
        BufferedInputStream bis2 = new BufferedInputStream(fis2);

        int byte1, byte2;
        while ((byte1 = bis1.read()) != -1 && (byte2 = bis2.read()) != -1) {
            if (byte1 != byte2) {
                return false;
            }
        }

        return true;
    }

    private static void printAbout() {
		System.out.println("Darija Rumjanceva 1.grupa 221RMC087");
        System.out.println("Gļebs Ostapko 8.grupa 231RDB186;");
        System.out.println("Veronika Kudrjavceva 1.grupa 231RDB238.");
    }
}
