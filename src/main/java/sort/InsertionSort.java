package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class InsertionSort implements ISorter {
    @Override
    public void sort(Repository repository, Comparator<Contract> comparator) {
        Contract[] repo = repository.getRepository();
        for (int i = 0; i < repository.getSize(); i++) {
            Contract value = repo[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (comparator.compare(repo[j], value) > 0) {
                    repo[j + 1] = repo[j];
                } else {
                    break;
                }
            }
            repo[j + 1] = value;
        }
        repository.setRepository(repo);
    }
}
