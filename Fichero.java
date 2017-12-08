package programa;
import java.io.*;
/**
 * clase Fichero con metodos: 
 * registrar : registrar usuarios en archivo login.txt,
 * listar : lee y muestra datos usuarios de archivo login.txt,
 * comprobar : para evitar duplicidad de dato user
 * desarrollado por Fernando Calatayud López
 */
public class Fichero {
	//metodo para registrar Usuario
	public void registrar(Usuario nuevo){ 
		try{
			File f = new File("login.txt");
			FileWriter fw;
			BufferedWriter bw;
			
			char[] charPass;
			String cadenaPass;
			//conversion char[] a String del password
			charPass = nuevo.getPassword();
			cadenaPass = String.valueOf(charPass);
			//si existe  escribe los datos en una linea, si no crea una nueva linea y escribe
			if(f.exists()){
				fw = new FileWriter(f, true);
				bw = new BufferedWriter(fw);
				
				
				bw.write(nuevo.getUser()+"%"+cadenaPass);
			}else{
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(nuevo.getUser()+"%"+cadenaPass);
			}
			//cierra BufferedWriter y Filewriter
			bw.close();
			fw.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}
	//metodo para listar Usuarios
	public void listar(){
		try{
			File f = new File("login.txt");
			if(f.exists()){
			    FileReader fr = new FileReader(f);
			    BufferedReader br = new BufferedReader(fr);
			    String linea;
			    while((linea = br.readLine())!=null){
				   String [] datos = linea.split("%");
				   String cadena = datos[1];
				   char[] aChar = cadena.toCharArray();
				   Usuario u = new Usuario(datos[0], aChar);
				   u.listar();
				   System.out.println("#############################");
				   br.close();
			    }
			}else{
				System.out.println("no se encuentra archivo login.txt, mal vamos");
			}
			
		}catch (Exception e){
			System.out.println(e);
		}
	}
	//metodo que comprueba si el usuario ya existe
	public void comprobar(String nombre){
		try{
			File f = new File("login.txt");
			if(f.exists()){
			    FileReader fr = new FileReader(f);
			    BufferedReader br = new BufferedReader(fr);
			    String linea;
			    while((linea = br.readLine())!=null){
				   String [] datos = linea.split("%");
				   
				  if(datos[0].equalsIgnoreCase(nombre)){
					  System.out.println("el usuario ya existe, pon otro o no te registras!");
				  }
			    }
			    fr.close();
			}else{
				System.out.println("no se encuentra archivo login.txt, mal vamos");
			}
		}catch (Exception e){
			System.out.println(e);
		}
	}

}
