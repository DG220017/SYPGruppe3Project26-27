package pocket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.entity.PocketUser;
import pocket.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Beim Login werden nur E-Mail und Passwort benötigt, keine Benutzer-ID.
    public static class LoginData {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginData login,
                                      HttpServletRequest request) {
        // Ohne E-Mail oder Passwort ist keine Anmeldung möglich.
        if (login.getEmail() == null || login.getPassword() == null
                || login.getPassword().isBlank()) {
            return ResponseEntity.status(401).build();
        }

        // Das vorhandene Benutzerkonto in der Datenbank suchen.
        PocketUser user = userRepository
                .findUserByEmail(login.getEmail().trim())
                .orElse(null);

        // Vergleich mit dem Passwort im bestehenden Datenmodell.
        if (user == null || !login.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        // Eine alte Sitzung ersetzen und nur die Benutzer-ID merken.
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        request.getSession(true).setAttribute("userId", user.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public synchronized ResponseEntity<String> register(@RequestBody LoginData data) {
        // Dieselben zwei Felder wie bei der Anmeldung.
        if (data.getEmail() == null || !data.getEmail().trim().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")
                || data.getPassword() == null || data.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Bitte gültige E-Mail und mindestens 8 Zeichen als Passwort eingeben.");
        }

        String email = data.getEmail().trim();
        if (userRepository.findUserByEmail(email).isPresent()) {
            return ResponseEntity.status(409).body("Diese E-Mail ist bereits registriert. Bitte anmelden.");
        }

        // Neues Konto im bestehenden Benutzermodell speichern.
        PocketUser user = new PocketUser();
        user.setEmail(email);
        user.setPassword(data.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(201).body("Konto erstellt. Du kannst dich jetzt anmelden.");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }
}
