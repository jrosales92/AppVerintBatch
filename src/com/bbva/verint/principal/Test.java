package com.bbva.verint.principal;

import java.io.File;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	public static void main(String args[]) throws Exception{
		System.out.println(NuevoDocto());
//		System.out.println(ConsultaDocto());
	}

	public static boolean NuevoDocto(){
		boolean isOk = false;
	    try {
	    	String pathFile = "C://Users//GESFOR-676//Documents//ejemploJson4sept (1).txt";
//	    	String pathFile = "/home/iros/Documents/proceso/ejemploJson4sept.txt";
            Scanner input = new Scanner(new File(pathFile));
	    	
            while (input.hasNextLine()) {
                String line = input.nextLine();
        		JSONArray jsonarray = new JSONArray(line);
        		JSONObject jsonObj = null;
        		for (int i = 0; i < jsonarray.length(); i++) {
					jsonObj = jsonarray.getJSONObject(i);
//					System.out.println(jsonObj);
					System.out.println("Procesando linea: " +i);
					isOk = SendDocument.almacenaInformacion(jsonObj);
				}
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		return isOk;
	}
}