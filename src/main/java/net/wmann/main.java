package net.wmann;

import org.iota.jota.IotaAPI;
import org.iota.jota.dto.response.SendTransferResponse;
import org.iota.jota.model.Transaction;
import org.iota.jota.model.Transfer;
import org.iota.jota.utils.SeedRandomGenerator;
import org.iota.jota.utils.TrytesConverter;

import java.util.ArrayList;
import java.util.List;

public class main {

    private static final String SEED = SeedRandomGenerator.generateNewSeed();
    private static final int MIN_WEIGHT_MAGNITUDE = 14;
    private static final int DEPTH = 9;

    public static void main(String ... args) {
        // List of public iota nodes: https://iota-nodes.net/
        IotaAPI iotaClient = new IotaAPI.Builder().protocol("http").host("iota-fn01.sairai.de").port(14265).build();
        String targetAddress = iotaClient.getAddressesUnchecked(SEED, 2, true, 0, 1).first();

        List<Transfer> transfers = new ArrayList<>();
        List<Transaction> tips = new ArrayList<>();

        // for each 2187 trytes in a message one transfer is necessary
        transfers.add(new Transfer(targetAddress, 0, TrytesConverter.asciiToTrytes("HELLO WORLD"), "DUESSELDORF"));

        SendTransferResponse str = iotaClient.sendTransfer(SEED, 2, DEPTH, MIN_WEIGHT_MAGNITUDE, transfers, null, null, false, false, tips);
        System.out.println(str.toString());
    }

}
