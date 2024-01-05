package lib.others.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lib.others.models.BookRental;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
    Optional<BookRental> findById(Long id);
    List<BookRental> findByReturnDateIsNull();
}
