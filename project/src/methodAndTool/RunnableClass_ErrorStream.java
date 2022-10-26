package methodAndTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RunnableClass_ErrorStream implements Runnable {
    private ArrayList<String> lines;
    private Process p;

    public RunnableClass_ErrorStream(ArrayList<String> lines, Process p) {
        this.lines = lines;
        this.p = p;

    }

    @Override
    public void run() {
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        try {
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }

            int count = 0;
            for (String s : lines) {
                if (s.contains("EOFError: EOF when reading a line")) {
                    count++;
                }
            }

            if (count > 0) {
                lines.clear();
            }

            // System.out.println(lines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
