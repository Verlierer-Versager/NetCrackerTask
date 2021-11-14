package sort;

import contracts.Contract;
import repository.Repository;

import java.util.Comparator;

public class BubbleSort implements ISorter {
    @Override
    public void sort(Repository repository, Comparator<Contract> comparator) {
        Contract[] repo = repository.getRepository();
        Contract temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < repository.getSize() - 1; i++) {
                if (comparator.compare(repo[i], repo[i + 1]) > 0) {
                    temp = repo[i];
                    repo[i] = repo[i + 1];
                    repo[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        repository.setRepository(repo);
    }
}
