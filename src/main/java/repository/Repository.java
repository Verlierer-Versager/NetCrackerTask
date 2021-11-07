package repository;

import contracts.Contract;

import java.util.Arrays;
import java.util.Objects;

public class Repository {
    private Contract[] repository = new Contract[16];
    private int size = 0;

    public void add(Contract contract) {
        increaseSize();
        repository[size] = contract;
        size++;
    }

    public <T> T getById(long id, Class<T> expectedClass) {
        for (int i = 0; i < size; i++) {
            if (repository[i].getId() == id && repository[i].getClass().equals(expectedClass)) {
                return (T) repository[i];
            }
        }
        return null;
    }

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
