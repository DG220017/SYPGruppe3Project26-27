package repository;

import entity.PocketUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PocketUser, Long> {

    PocketUser findUserByEmail(String name);

}
