package pocket.DTO;

import org.springframework.stereotype.Component;

@Component
public class LoginData {
        private String email;
        private String password;

        public LoginData(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public LoginData(){
            // spring
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

}
