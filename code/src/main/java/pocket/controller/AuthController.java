package pocket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pocket.entity.PocketUser;
import pocket.exception.PocketUserNotFoundException;

import pocket.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:63342")
public class AuthController {

    // Nur diese beiden Werte werden vom Loginformular gesendet.
    public static class Credentials {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Credentials login,
                                      HttpServletRequest request) {
        // Ohne E-Mail oder Passwort ist keine Anmeldung möglich.
        if (login.getEmail() == null || login.getPassword() == null
                || login.getPassword().isBlank()) {
            return ResponseEntity.status(401).build();
        }

        // Das vorhandene Benutzerkonto in der Datenbank suchen.
        try{
            PocketUser user = userService.getUserByEmail(login.getEmail().trim());
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
        }catch (PocketUserNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public synchronized ResponseEntity<String> register(@RequestBody Credentials data) {
        // Dieselben zwei Felder wie bei der Anmeldung.
        if (data.getEmail() == null || !data.getEmail().trim().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")
                || data.getPassword() == null || data.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Bitte gültige E-Mail und mindestens 8 Zeichen als Passwort eingeben.");
        }

        String email = data.getEmail().trim();

        try {
            userService.getUserByEmail(email);
            return ResponseEntity.status(409).body("Diese E-Mail ist bereits registriert. Bitte anmelden.");
        } catch (PocketUserNotFoundException e) {
            PocketUser user = new PocketUser();
            user.setEmail(email);
            user.setPassword(data.getPassword());
            // Neues Konto im bestehenden Benutzermodell speichern.
            userService.addUser(user);
            return ResponseEntity.status(201).body("Konto erstellt. Du kannst dich jetzt anmelden.");
        }
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
