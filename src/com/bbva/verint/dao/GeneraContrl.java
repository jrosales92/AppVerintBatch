package com.bbva.verint.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneraContrl {
	
	public void generaArchivo(String metadata) {
		String ruta = "C:/Users/GESFOR-676/Desktop/Archivos de texto/Verint.cntrl";
		String contenido = metadata;
		File file = new File(ruta);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = null;
			bw = new BufferedWriter(fw);
			
				bw.write(contenido);
				bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CreaArchivoStart(){
		String ruta = "C:/Users/GESFOR-676/Desktop/Archivos de texto/Verint.start";
        String contenido2 = " ";
        File file = new File(ruta);
        // Si el archivo no existe es creado
        try {  
        	if (!file.exists()) {
        		file.createNewFile();
        }
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = null;
				bw=	new BufferedWriter(fw);
				bw.write(contenido2);
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
