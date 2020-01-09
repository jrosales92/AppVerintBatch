package com.bbva.verint.principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
			System.out.println("Entre en switch 1 a cargaInicial");
			cargaInicial(args[0]);
			break;
		case 2:
			System.out.println("Entre a switch 2 lectura result");
			procesaResult(args[0]);
			break;
		default:
			break;
		case 3:
			System.out.println("Entre a switch 3 lectura archivoerror");
			cargaArchERR(args[0]);
			break;	
		}
	}

	public static boolean cargaInicial(String pathFile){
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
					MetadataController.mapJsonInputString(jsonObj.toString());
					isOk = SendDocument.almacenaInformacion(jsonObj);
				}
            }
            input.close();
        } catch (CustomException ex) {
    		GeneraArchivos ga = new GeneraArchivos();
//    		TODO ACORDAR DE AJUSTAR Y PONER LA FECHA DEL SISTEMA PARA EL NOMBRE DEL ARCHIVO DE ERRORES
    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "ERRORES_VERINT_RESULT.txt", ex.getMessage());
        } catch (FileNotFoundException e) {
			e.printStackTrace();
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
	
	public static boolean cargaArchERR(String pathFile){
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
					
					isOk = (ErrorRucEuController.mapJsonInputString(jsonObj) !=null ? true : false);
					
				}
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		return isOk;
	}
}