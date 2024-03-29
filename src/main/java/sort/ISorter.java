package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public interface ISorter {
    void sort(Repository repository, Comparator<Contract> comparator);
}
