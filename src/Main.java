
import java.io.*; // Gestiona las operaciones de entrada y de salida
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws IOException{
        Scanner introduce = new Scanner(System.in);

        // Declaracion de variables que usaremos mas adelante para introducir los datos nuevos
        int nuevoId = 0;
        String nuevoNombre = "";
        String nuevaLocalidad = "";

        File fichero = new File("Departamentos.dat");
        DataOutputStream escritura = new DataOutputStream (new FileOutputStream(fichero,false));

        //Datos originales del fichero
        introducirDatos(1,"Laia","Barcelona",escritura);
        introducirDatos(2,"Alex","Vallecas",escritura);
        introducirDatos(3,"Miguel","Arganda",escritura);
        introducirDatos(4,"Amane","Valdemoro",escritura);
        introducirDatos(5,"Gon","Galapagar",escritura);
        escritura.close();

        DataInputStream lectura = new DataInputStream (new FileInputStream(fichero));

        System.out.println("Que departamento quieres modificar");
        nuevoId=introduce.nextInt();

        if(exists(nuevoId,lectura)==true) {

            System.out.println("Introduce el nombre nuevo: ");
            nuevoNombre=introduce.next(); // Cuando son Strings solo se usa el next
            System.out.println("Introduce la localidad: ");
            nuevaLocalidad=introduce.next();

            RandomAccessFile fich = new RandomAccessFile(fichero, "rw");

            switch(nuevoId) {
                case 1:
                    fich.seek(0);
                    System.out.println(fich.readInt());
                    System.out.println(fich.readUTF());
                    System.out.println(fich.readUTF());
            }
        } else {
            System.out.println("El numero que introduces no existe como departamento");
        }
    }

    // Metodo para introducir los datos dentro del fichero
    public static void introducirDatos(int departamento, String nombre, String localidad, DataOutputStream escritura) throws IOException {
        escritura.writeInt(departamento);
        escritura.writeUTF(nombre);
        escritura.writeUTF(localidad);
    }

    public static boolean exists(int nuevoId,DataInputStream lectura) throws IOException {
        boolean buleano=false;
        int prueba=0; //variable que activa el booleano
        try{
            while (lectura.available()>0) {
                if(lectura.readInt()==nuevoId) {
                    prueba++;
                    System.out.println("--------------------------\nNumero de Departamento: "+nuevoId+"\nNombre: "+lectura.readUTF()+"\nLocalidad: "+lectura.readUTF());
                }else {
                    lectura.readUTF();
                    lectura.readUTF();
                }
            }
            lectura.close();
        } catch (EOFException e) {
            System.out.println("No funciona");
        }
        if(prueba==1) {
            buleano=true;
        }
        return buleano;
    }
}