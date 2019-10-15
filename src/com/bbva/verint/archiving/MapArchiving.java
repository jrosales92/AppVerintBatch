package com.bbva.verint.archiving;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bbva.verint.bean.VerintBean;

public class MapArchiving {
	
	public Map<String, Object>  generaMetadata (VerintBean verint){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String date = sdf.format(new Date()); 
	
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("t", verint.getTituloAplicacion());//Titulo aplicacion
		metadata.put("g", verint.getIdGabinete());//id Gabinete
		metadata.put("d", verint.getIdDocumento());//id Documento
		metadata.put("ca", verint.getIdCarpeta());//id Carpeta
		metadata.put("v", verint.getIdVersion());//id Version
		metadata.put("np", verint.getNumeroPagina());//id numeroPagina
		metadata.put("f", verint.getFolioDigitalizacion());//id folio Digitalizacion
		metadata.put("fh", verint.getDateTime());//id Fecha
		metadata.put("n", verint.getNameRecord());//Nombre Archivo
		metadata.put("e", verint.getExt());//Extension
		metadata.put("s", verint.getSize());//Size
		metadata.put("ki", verint.getKeyIntervener()); //keyIntervener
		metadata.put("dk", verint.getDocumentKey());		//clave del Documento
		metadata.put("cv", verint.getContactIdVerint());     //id contact Verint
		metadata.put("dt", date);            //fecha
		metadata.put("to", verint.getTypeOperation());      //id Carpeta
		metadata.put("tm", verint.getTypeMatrix());    //idDocumentos
		metadata.put("td", verint.getTypeDocument());      //id version
		metadata.put("p",(verint.getProduct()));	//clave del Documento
		metadata.put("cr", verint.getCr());			//CR
		metadata.put("ci", verint.getCustomerId());                  //usuario
		metadata.put("fu", verint.getFuntion());                  //extension
		metadata.put("tt", verint.getTypeTransact()); //tama�o archivo
		metadata.put("sha1n", verint.getSha1n());   //Digestion SHA1 del Archivo
		metadata.put("dd", verint.getDescriptionDocument());
		metadata.put("se", verint.getService());
		metadata.put("sa", verint.getSignatureAdviser());
		metadata.put("cid", verint.getContractId());
		metadata.put("nr", verint.getNameRecord());
		metadata.put("ic", verint.getIdCertificacion());
		metadata.put("po", verint.getPhaseOperation());

		return metadata;
		
	}
	
	 public static String quitaSignos(String cadena) {
		 String response = null;
		 if (cadena != null)
		 response = cadena.replaceAll("[^\\dA-Za-z. ]", "");
		 return response;
		 }

}
