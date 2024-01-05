package lib.central.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lib.central.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByJmbg(String jmbg);
    public Optional<User> findById(Long id);
}
