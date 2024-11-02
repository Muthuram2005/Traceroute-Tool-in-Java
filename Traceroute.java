package exp4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Traceroute {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the IP address or hostname to traceroute: ");
        String ipAddress = scanner.nextLine();

        try {
            System.out.println("Tracing route to " + ipAddress + "...");
            String command = isWindows() ? "tracert" : "traceroute";
            ProcessBuilder processBuilder = new ProcessBuilder(command, ipAddress);
            Process process = processBuilder.start();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String commandOutput;

            while ((commandOutput = inputStream.readLine()) != null) {
                System.out.println(commandOutput);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
