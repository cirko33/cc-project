package lib.others.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lib.others.models.BookRental;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
    BookRental findByIsbn(String isbn);
}
