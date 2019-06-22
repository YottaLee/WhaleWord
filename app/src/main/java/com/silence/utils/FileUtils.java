package com.silence.utils;

import android.content.Context;

import com.silence.word.R;

import java.io.*;

/**
 * Created by Autumn on 2019/6/11 0011.
 */
public class FileUtils {
    public static final String PREFIX_PATH = "";
    private FileUtils() {
    }

    public static void writeData(Context context) {
        String dbPath = context.getDir(Const.DB_DIR, Context.MODE_PRIVATE) + File.separator + Const.DB_NAME;
        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            InputStream inputStream = context.getResources().openRawResource(R.raw.words);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(dbFile);
                int len;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean writeFile(String fileName, String content) {

        try {
            String filePath = PREFIX_PATH + fileName;
            File file = new File(filePath);
            String mkdirPath = PREFIX_PATH + fileName.split("/")[0];

            File mkfirFile = new File(mkdirPath);
            if(!mkfirFile.exists())
                mkfirFile.mkdir();
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.write(content);
            printWriter.println();
            writer.close();
            printWriter.close();

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String readFile(Context context, String fileName) {
        String content = "";
        try {
            System.out.println(fileName);
            InputStream inputStream;
            if (fileName.equals("day")) {
                inputStream = context.getResources().openRawResource(R.raw.day);
            } else if (fileName.equals("word")) {
                inputStream = context.getResources().openRawResource(R.raw.word);
            } else  {
                inputStream = context.getResources().openRawResource(R.raw.today);
            }

            content = getString(inputStream);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return content;
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "gbk");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
