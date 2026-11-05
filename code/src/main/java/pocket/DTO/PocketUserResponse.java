package pocket.DTO;

import pocket.entity.Account;
import pocket.entity.PocketUser;

import java.util.List;

public record PocketUserResponse(Long id, String email, List<Account> accounts){

    public static PocketUserResponse fromPocketUser(PocketUser pocketUser){
        return new PocketUserResponse(pocketUser.getId(), pocketUser.getEmail(), pocketUser.getAccount());
    }

}
