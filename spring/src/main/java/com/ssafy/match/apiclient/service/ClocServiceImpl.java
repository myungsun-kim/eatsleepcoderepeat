package com.ssafy.match.apiclient.service;


import com.ssafy.match.apiclient.dto.UserGitRepository;
import com.ssafy.match.apiclient.dto.UserGitStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;

/**
 * execute cloc-git service.
 *
 */
@Service
public class ClocServiceImpl {
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Map<String, UserGitStatus> result;

        public StreamGobbler(InputStream inputStream, Map<String, UserGitStatus> ret) {
            this.inputStream = inputStream;
            this.result = ret;
        }

        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
                // try{
                //     new BufferedReader(new InputStreamReader(inputStream, "utf-8")).lines()
                //             .forEach(consumer);

                int idx = 0;
                System.out.println("!!");
                while(idx < 4){
                    // System.out.println(idx);
                    // System.out.println(br.readLine());
                    String str = br.readLine();
                    if(str.startsWith("---------")) {
                        idx++;
                        if(idx == 1){
                            String line = br.readLine();
                            if(line.startsWith("-----------")){
                                System.exit(0);
                            }
                            StringTokenizer st = new StringTokenizer(line);
                            String lang = st.nextToken();
                            long files = Long.parseLong(st.nextToken());
                            long blank = Long.parseLong(st.nextToken());
                            long comment = Long.parseLong(st.nextToken());
                            long codes = Long.parseLong(st.nextToken());
                            if(!result.containsKey(lang)){
                                result.put(lang, new UserGitStatus(lang));
                            }
                            UserGitStatus s = result.get(lang);
                            s.addBlank(blank);
                            s.addCode(codes);
                            s.addComment(comment);
                            s.addFiles(files);
                        }
                    }
                    System.out.println("org: "+ str);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e ){
                e.printStackTrace();
                System.out.println("IOException");
            }

            System.exit(-1);
            // return;
        }
    }
    public static Map<String,UserGitStatus> getGitStatus (String fullName) throws Exception{
        Map<String,UserGitStatus> ret = new HashMap<String, UserGitStatus>();
        boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder("/home/daebalprime");
        if (isWindows) {
            builder.command("cmd.exe", "/c", "cloc", "git", "https://github.com/daebalprime/19FW_REST_PROJECT");
        } else {
            builder.command("/bin/bash", "-c", "./cloc-git https://github.com/"+fullName);
        }
        // builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();
        StreamGobbler streamGobbler =
            new StreamGobbler(process.getInputStream(), ret);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        process.waitFor();
        return ret;
    }
}
