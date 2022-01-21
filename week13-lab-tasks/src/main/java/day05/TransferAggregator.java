package day05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TransferAggregator {
    public List<TransferPerClient> readTransfers(Path path) {
        List<String> lines = readLines(path);
        return summarise(lines);
    }

    private List<TransferPerClient> summarise(List<String> lines) {
        Map<String, TransferPerClient> transfers = new TreeMap<>();
        for (String line : lines) {
            String[] datas = line.split(",");
            String key1 = datas[0];
            String key2 = datas[1];
            int amount = Integer.parseInt(datas[2]);

            TransferPerClient sourceClient = transfers.computeIfAbsent(key1,k->new TransferPerClient(key1));
            sourceClient.decrease(amount);
            TransferPerClient targetClient = transfers.computeIfAbsent(key2,k->new TransferPerClient(key2));
            targetClient.increase(amount);
        }
        return transfers.values().stream().toList();
    }

    private List<String> readLines(Path path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file!", ioe);
        }
    }

    public List<String> formatResult(List<TransferPerClient> transfersClientSum) {
        List<String> lines = new ArrayList<>();
        for (TransferPerClient transferPerClient : transfersClientSum) {
            lines.add(String.format("%s %,12d %5d", transferPerClient.getClientsId(), transferPerClient.getSum(), transferPerClient.getNumberOfTransactions()));
        }
        return lines;
    }

    public void writeToFile(Path path, List<String> lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(path))) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not write file!");
        }
    }

    public static void main(String[] args) {
        TransferAggregator transferAggregator = new TransferAggregator();
        List<TransferPerClient> transfersClientSum = transferAggregator.readTransfers(Paths.get("src/main/resources/transfers.csv"));
        List<String> formattedString = transferAggregator.formatResult(transfersClientSum);
        transferAggregator.writeToFile(Paths.get("src/main/resources/transfers-sum-KL.txt"), formattedString);
    }
}
