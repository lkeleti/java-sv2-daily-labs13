package day05;

public class TransferPerClient {
    private String ClientsId;
    private int sum;
    private int numberOfTransactions;

    public TransferPerClient(String clientsId) {
        ClientsId = clientsId;
    }

    public String getClientsId() {
        return ClientsId;
    }

    public int getSum() {
        return sum;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void increase(int amount) {
        sum+= amount;
        numberOfTransactions++;
    }

    public void decrease(int amount) {
        sum-= amount;
        numberOfTransactions++;
    }

    @Override
    public String toString() {
        return "TransferPerClient{" +
                "ClientsId='" + ClientsId + '\'' +
                ", sum=" + sum +
                ", numberOfTransactions=" + numberOfTransactions +
                '}';
    }
}
