package Model;

public class UserAccount {
    private final String name;
    private final String email;
    private final String password;
    public UserAccount(String name, String email, String password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Ugyldig email");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password skal være mindst 6 tegn");
        }

        this.name = name.trim();
        this.email = email.trim().toLowerCase();
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

