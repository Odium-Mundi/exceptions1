package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();
    private Product book1 = new Book(1, "book1", 20, "i", 20, 2020);
    private Product book2 = new Book(2, "book1", 20, "i", 20, 2020);

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);

        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfExists() {
        repository.save(book1);
        repository.save(book2);

        repository.removeById(1);
        Product[] expected = new Product[]{book2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfNoExists() {
        repository.save(book1);
        repository.save(book2);

        assertThrows(NotFoundException.class, () -> repository.removeById(3));
    }

}
