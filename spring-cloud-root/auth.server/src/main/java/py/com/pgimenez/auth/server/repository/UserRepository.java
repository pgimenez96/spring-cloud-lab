package py.com.pgimenez.auth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.pgimenez.auth.server.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
