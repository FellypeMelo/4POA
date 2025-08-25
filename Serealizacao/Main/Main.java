package Serealizacao.Main;

import Serealizacao.Class.Gato;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        Gato gato1 = new Gato("Miau", 2, "Branco", "Siamês", "Fêmea", "João", "1234567890", "joao@gmail.com");
        Gato gato = null;

        try {
           File f = new File("c:/a/");
           if (!f.exists()) {
              f.mkdir();
           }

           FileOutputStream arqOSer = new FileOutputStream("c:/a/clara.ser");
           ObjectOutputStream oOSer = new ObjectOutputStream(arqOSer);
           oOSer.writeObject(gato1);
           oOSer.close();
           System.out.println("---------------Antes de alterar idade-------------");
           System.out.println(gato1);
           gato1.setIdade(5);
           System.out.println("---------------Depois de alterar idade-------------");
           System.out.println(gato1);
           FileInputStream arqISer = new FileInputStream("c:/a/clara.ser");
           ObjectInputStream iOSer = new ObjectInputStream(arqISer);
           gato = (Gato)iOSer.readObject();
           iOSer.close();
           System.out.println("---------------Depois de recuperar os valores-------------");
           System.out.println(gato);
        } catch (FileNotFoundException var7) {
           var7.printStackTrace();
        } catch (IOException var8) {
           var8.printStackTrace();
        } catch (ClassNotFoundException var9) {
           var9.printStackTrace();
        }
    }
}
