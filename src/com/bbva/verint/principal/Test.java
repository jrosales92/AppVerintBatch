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
	
	public static boolean integraArchEntrada(String pathFile) {
		boolean isOk = false;
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String Hoy = formato.format(fecha);
	    try {
            Scanner input = new Scanner(new File(pathFile + "file_json_" + Hoy + "_1030.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                GeneraArchivos ga = new GeneraArchivos();
	    		ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "Archivo_Final_Inicial.txt", line.toString());
            }
            try {
	    		Scanner input2 = new Scanner(new File(pathFile + "file_json_" + Hoy + "_1330.txt"));
	            while (input2.hasNextLine()) {
                        String line2 = input2.nextLine();
                        GeneraArchivos ga = new GeneraArchivos();
                        ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "Archivo_Final_Inicial.txt", line2.toString());
	            }
                    try {
                          Scanner input3 = new Scanner(new File(pathFile + "file_json_" + Hoy + "_1630.txt"));
                          while (input3.hasNextLine()) {
                              String line3 = input3.nextLine();
                              GeneraArchivos ga = new GeneraArchivos();
                              ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "Archivo_Final_Inicial.txt", line3.toString());
                          }
                          try {
                                Scanner input4 = new Scanner(new File(pathFile + "file_json_" + Hoy + "_1930.txt"));
                                while (input4.hasNextLine()) {
                                    String line4 = input4.nextLine();
                                    GeneraArchivos ga = new GeneraArchivos();
                                    ga.writeInfoInFile(ParametrosVerint.PATHFILEERRORES + File.separator + "Archivo_Final_Inicial.txt", line4.toString());
                                }
                                    input.close();
                                
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

				catch (Exception ex2) {
					ex2.printStackTrace();
				}

			} catch (Exception ex3) {
				ex3.printStackTrace();
			}

		} catch (Exception ex4) {
			ex4.printStackTrace();
		}
		
		return isOk;
                 
	}
            
	
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
		    		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
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
	    		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
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