package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class SelectionSort implements ISorter {
    @Override
    public void sort(Repository repository, Comparator<Contract> comparator) {
        Contract[] repo = repository.getRepository();
        for (int i = 0; i < repository.getSize() - 1; i++) {
            int least = i;
            for (int j = i + 1; j < repository.getSize(); j++) {
                if (comparator.compare(repo[least], repo[j]) > 0) {
                    least = j;
                }
            }
            Contract tmp = repo[i];
            repo[i] = repo[least];
            repo[least] = tmp;
        }
        repository.setRepository(repo);
    }
}
