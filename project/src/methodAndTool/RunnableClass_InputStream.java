package methodAndTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RunnableClass_InputStream implements Runnable {
    private ArrayList<String> lines;
    private Process p;

    public RunnableClass_InputStream(ArrayList<String> lines, Process p) {
        this.lines = lines;
        this.p = p;

    }

    @Override
    public void run() {
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        try {
            while ((line = r.readLine()) != null) {
                // System.out.println(line);
                lines.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
