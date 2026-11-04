package pocket.repository;

import pocket.entity.PocketUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<PocketUser, Long> {

    Optional<PocketUser> findUserByEmail(String name);

}
