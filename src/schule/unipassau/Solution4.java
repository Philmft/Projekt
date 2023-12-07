package schule.unipassau;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution4 {
    static class Network {
        String name;
        InetAddress inetAddress;
        int prefixLength;

        Network(String name, String cidr) throws Exception {
            this.name = name;
            String[] parts = cidr.split("/");
            inetAddress = InetAddress.getByName(parts[0]);
            prefixLength = Integer.parseInt(parts[1]);
        }

        boolean contains(InetAddress address) {
            byte[] networkBytes = inetAddress.getAddress();
            byte[] addressBytes = address.getAddress();

            int fullBytes = prefixLength / 8;
            byte endBits = (byte) (0xFF << (8 - (prefixLength % 8)));

            for (int i = 0; i < fullBytes; i++) {
                if (networkBytes[i] != addressBytes[i]) {
                    return false;
                }
            }

            if (fullBytes < networkBytes.length) {
                return (networkBytes[fullBytes] & endBits) == (addressBytes[fullBytes] & endBits);
            }

            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        scanner.nextLine();

        List<Network> networks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(": ");
            networks.add(new Network(parts[0], parts[1]));
        }

        scanner.nextLine();

        for (int i = 0; i < a; i++) {
            InetAddress address = InetAddress.getByName(scanner.nextLine());
            Network result = null;

            for (Network network : networks) {
                if (network.contains(address) && (result == null || network.prefixLength > result.prefixLength)) {
                    result = network;
                }
            }

            System.out.println(result != null ? result.name : "?");
        }

        scanner.close();
    }
}
