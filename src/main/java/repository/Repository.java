package repository;

import contracts.Contract;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class Repository {

    /**
     * @param repository contracts array
     * @param size number of contracts in a repository
     **/

    private Contract[] repository = new Contract[16];
    private int size = 0;

    /**
     * add method
     * after checking free space
     * adds a new item to the end of the repository
     *
     * @param contract new contract to add
     **/

    public void add(Contract contract) {
        increaseSize();
        repository[size] = contract;
        size++;
    }

    /**
     * getById method
     * searches for a contract for a given id
     *
     * @param id            given id
     * @param expectedClass expected type of contract
     **/

    public <T> T getById(long id, Class<T> expectedClass) {
        for (int i = 0; i < size; i++) {
            if (repository[i].getId() == id && repository[i].getClass().equals(expectedClass)) {
                return (T) repository[i];
            }
        }
        return null;
    }

    public void setRepository(Contract[] repository) {
        this.repository = repository;
    }

    public <T> T search(Predicate<Contract> predicate, Class<T> expectedClass) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(repository[i]) && repository[i].getClass().equals(expectedClass)) {
                return (T) repository[i];
            }
        }
        return null;
    }

    /**
     * removeById method
     * searches for a contract for a given id
     * and delete it
     *
     * @param id given id
     **/

    public void removeById(long id) {
        for (int i = 0; i < size; i++) {
            if (repository[i].getId() == id) {
                int j = i;
                while (j != size - 1) {
                    repository[j] = repository[j + 1];
                    j++;
                }
                size--;
                break;
            }
        }
    }

    /**
     * increaseSize method
     * checks the number of contracts in a repository
     * if it is equal to array length
     * method increase array length
     **/

    private void increaseSize() {
        if (size == repository.length) {
            repository = Arrays.copyOf(repository, (int) (repository.length * 1.5));
        }
    }

    public Contract[] getRepository() {
        return repository;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return size == that.size && Arrays.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(repository);
        return result;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repository=" + Arrays.toString(repository) +
                ", size=" + size +
                '}';
    }
}
