package com.bbva.verint.principal;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bbva.verint.controller.ErrorRucEuController;
import com.bbva.verint.controller.MetadataController;
import com.bbva.verint.controller.ResultController;
import com.bbva.verint.dao.GeneraArchivos;
import com.bbva.verint.exception.CustomException;
import com.bbva.verint.parametros.ParametrosVerint;

public class Test {
	
	public static void main(String args[]) throws Exception{
		int ejecuta = Integer.parseInt(args[1]);
		switch (ejecuta) {
		case 1:
			System.out.println("Entre a switch 1 integra txt entrada");
			integraArchEntrada(args[0]);
			break;	
		case 2:
			System.out.println("Entre en switch 2 a cargaInicial");
			cargaInicial(args[0]);
			break;
		case 3:
			System.out.println("Entre a switch 3 lectura result");
			procesaResult(args[0]);
			break;
		default:
			break;
		case 4:
			System.out.println("Entre a switch 4 lectura archivoerrorRuc");
			cargArchErrRuc(args[0]);
			break;	
		case 5:
			System.out.println("Entre a switch 4 lectura archivoerrorEU");
			cargArchErrEU(args[0]);
			break;	
		}
	}
	
	public static void integraArchEntrada(String pathFile) {
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      FileWriter fichero = null;
	      PrintWriter pw = null;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (pathFile);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);

	         pw = new PrintWriter(linea);
	             fichero = new FileWriter(ParametrosVerint.PATHFILEERRORES + File.separator + "Json_Final_Entrada.txt");

	             for (int i = 0; i > 10; i++)
	                 pw.println("Linea " + i);

	         } catch (Exception ex) {
	             ex.printStackTrace();
	         } finally {
	            try {
	            // Nuevamente aprovechamos el finally para 
	            // asegurarnos que se cierra el fichero.
	            if (null != fichero)
	               fichero.close();
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
	         }
	      }
	   
//		boolean isOk = false;
//		try {
//			Scanner input = new Scanner(new File(pathFile));
//			while (input.hasNextLine()) {
//				String line = input.nextLine();
//				JSONArray jsonarray = new JSONArray(line);
//				JSONArray jsonObj = null;
//				for (int i = 0; i < jsonarray.length(); i++) {
//					jsonObj = new JSONArray(line);
//					System.out.println("Procesando linea: " + i);
//					GeneraArchivos ga = new GeneraArchivos();
//		    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "Json_Final_Entrada.txt", line.toString());
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return isOk;
//}	
	
	public static boolean cargaInicial(String pathFile) {
		boolean isOk = false;
	    try {
            Scanner input = new Scanner(new File(pathFile));
            while (input.hasNextLine()) {
                String line = input.nextLine();
        		JSONArray jsonarray = new JSONArray(line);
        		JSONObject jsonObj = null;
        		for (int i = 0; i < jsonarray.length(); i++) {
					jsonObj = jsonarray.getJSONObject(i);
					System.out.println("Procesando linea: " +i);
				try {	
					MetadataController.mapJsonInputString(jsonObj.toString());
					isOk = SendDocument.almacenaInformacion(jsonObj);
				}catch(Exception ex) {
					Date fecha = new Date();
		    		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		    		String Hoy = formato.format(fecha);
		    		GeneraArchivos ga = new GeneraArchivos();
		    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "ERRORES_VERINT_RESULT_" + Hoy + ".txt", ex.getMessage());
				}
				}
            }
            input.close();
        
        } catch (FileNotFoundException e) {
			e.printStackTrace();
        } catch (Exception ex) {
		}
		return isOk;

	}
	
	public static boolean procesaResult(String pathFile){
		boolean isOk = false;
		int j = 0;
	    try {
            Scanner input = new Scanner(new File(pathFile));
            while (input.hasNextLine()) {
                String lineResult = input.nextLine();
                System.out.println("Procesando linea: " + j);
                if(ResultController.validaResult(lineResult)) {
                	JSONObject jsonObj = new JSONObject(lineResult);
                		isOk = ResultController.registraFolio(jsonObj);
                }
                j++;
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		return isOk;
	}
	
	public static boolean cargArchErrRuc(String pathFile){
		boolean isOk = false;
	    try {
            Scanner input = new Scanner(new File(pathFile));
            while (input.hasNextLine()) {
                String line = input.nextLine();
        		JSONObject jsonObj = new JSONObject(line);
				isOk = (ErrorRucEuController.mapJsonInputString(jsonObj) !=null ? true : false);
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		return isOk;
	}
	
	public static boolean cargArchErrEU(String pathFile){
		boolean isOk = false;
	    try {
            Scanner input = new Scanner(new File(pathFile));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                StringBuilder sb = new StringBuilder();
                sb.append("ERROR004").append("|").append(line);
                Date fecha = new Date();
	    		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
	    		String Hoy = formato.format(fecha);
                GeneraArchivos ga = new GeneraArchivos();
	    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "ERRORES_VERINT_RESULT_" + Hoy + ".txt", sb.toString());
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		return isOk;
	}
}