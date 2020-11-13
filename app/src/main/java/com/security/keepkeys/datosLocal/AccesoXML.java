package com.security.keepkeys.datosLocal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AccesoXML {
    // Indicador para definir si se va a escribir o leer el archivo xml con las claves.
    // Escribir - 0
    // Leer - 1
    int tipoAcceso;
    Context context;


    /**
     * Constructor por defecto
     */
    public AccesoXML() {
    }

    public AccesoXML(int optn, Context context) {
        tipoAcceso = optn;
        this.context = context;

        opcion();
    }

    private void opcion() {
        switch (tipoAcceso) {
            case 0:
                escribirXML();
            case 1:
                leerXML();
        }
    }

    /**
     * Metodo que va a escribir los datos en un archivo xml
     */
    private void escribirXML() {
        try {
            //Creamos el serializer
            XmlSerializer ser = Xml.newSerializer();

            //Creamos un fichero en memoria interna
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            context.openFileOutput("datos.xml",
                                    Context.MODE_PRIVATE));

            //Asignamos el resultado del serializer al fichero
            ser.setOutput(fout);

            //Construimos el XML
            ser.startTag("", "usuario");
            ser.startTag("", "nombre");
            ser.text("Usuario1");
            ser.endTag("", "nombre");

            ser.startTag("", "apellidos");
            ser.text("ApellidosUsuario1");
            ser.endTag("", "apellidos");

            ser.endTag("", "usuario");

            ser.endDocument();

            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("ERROR XML", "Error al escribir xml: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void leerXML() {

    }


}
