package util;

public class ExceptionHandler {
    public static void handle(Exception e) {
        if(e instanceof FileReadException) {
            System.out.println("Error reading file: ");
            System.out.println(e.getMessage());
        }

        else if(e instanceof FileWriteException) {
            System.out.println("Error writing file: ");
            System.out.println(e.getMessage());
        }

        else{
            System.out.println("Unexpceted erorr: ");
            System.out.println(e.getMessage());
        }
    }
    public static class FileReadException extends Exception {
        public FileReadException(String message) {
            super(message);}
    }
    public static class FileWriteException extends Exception{
        public FileWriteException(String message) {
            super(message);
        }
    }
}
