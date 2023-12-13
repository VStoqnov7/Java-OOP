package P14UnitTesting.Tasks.java.P4TestDrivenDevelopmentExercises;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactionMap;
    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return this.transactionMap.size();
    }

    public void add(Transaction transaction) {
        int id = transaction.getId();
        if (!transactionMap.containsKey(id)) {
            this.transactionMap.put(id, transaction);
        }

    }
    public boolean contains(Transaction transaction) {
        return this.transactionMap.containsValue(transaction);
    }
    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Transaction transaction = this.transactionMap.get(id);
        transaction.setStatus(newStatus);
    }
    public void removeTransactionById(int id) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        transactionMap.remove(id);
    }
    public Transaction getById(int id) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return transactionMap.get(id);
    }
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : this.transactionMap.values()) {
            if (transaction.getStatus() == status) {
                filteredTransactions.add(transaction);
            }
        }

        if (filteredTransactions.size() == 0) {
            throw new IllegalArgumentException();
        }

        filteredTransactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        return filteredTransactions;

    }
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactions::add);


        filteredTransactions.stream().sorted(Comparator.comparing(Transaction::getAmount).reversed()).collect(Collectors.toList());
        List<String> senders = filteredTransactions.stream().map(Transaction::getFrom).collect(Collectors.toList());
        return senders;
    }
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        getByTransactionStatus(status).forEach(filteredTransactions::add);

        filteredTransactions.stream().sorted(Comparator.comparing(Transaction::getAmount).reversed()).collect(Collectors.toList());
        List<String> receivers = filteredTransactions.stream().map(Transaction::getTo).collect(Collectors.toList());
        return receivers;
    }
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactionMap.values()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount)
                        .reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }
    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction value : transactionMap.values()) {
            if (value.getFrom().equals(sender)) {
                filteredTransactions.add(value);
            }
        }
        if (filteredTransactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        filteredTransactions = filteredTransactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getFrom))
                .collect(Collectors.toList());

        return filteredTransactions;
    }
    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction value : transactionMap.values()) {
            if (value.getTo().equals(receiver)) {
                filteredTransactions.add(value);
            }
        }
        if (filteredTransactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        filteredTransactions = filteredTransactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
        return filteredTransactions;
    }
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction value : transactionMap.values()) {
            if (value.getStatus().equals(status) && value.getAmount() <= amount) {
                filteredTransactions.add(value);
            }
        }
        filteredTransactions = filteredTransactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());

        return filteredTransactions;
    }
    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction value : transactionMap.values()) {
            if (value.getFrom().equals(sender) && value.getAmount() > amount) {
                filteredTransactions.add(value);
            }
        }
        if (filteredTransactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        filteredTransactions = filteredTransactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());

        return filteredTransactions;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction value : transactionMap.values()) {
            if (value.getTo().equals(receiver) && value.getAmount() >= lo && value.getAmount() < hi){
                filteredTransactions.add(value);
            }
        }

        if (filteredTransactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        filteredTransactions = filteredTransactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount)
                .thenComparing(Transaction::getId))
                .collect(Collectors.toList());


        return filteredTransactions;
    }
    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return transactionMap
                .values()
                .stream()
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.transactionMap.values().iterator();
    }
}
