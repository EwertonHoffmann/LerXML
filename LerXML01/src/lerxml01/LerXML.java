/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lerxml01;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author labin
 */
public class LerXML {

    public static void main(String[] args) throws JDOMException, IOException {
        //Aqui você informa o nome do arquivo XML.  
        File f = new File("C:\\Users\\labin\\Desktop\\clientes.xml");

        //Criamos uma classe SAXBuilder que vai processar o XML4  
        SAXBuilder sb = new SAXBuilder();

        //Este documento agora possui toda a estrutura do arquivo.  
        Document d = sb.build(f);

        //Recuperamos o elemento root  
        Element cidades = d.getRootElement();

        //Recuperamos os elementos filhos (children)  
        List cidades1 = cidades.getChildren();
        Iterator i = cidades1.iterator();

        //Iteramos com os elementos filhos, e filhos do dos filhos  
        while (i.hasNext()) {
            Element cidade = (Element) i.next();
            System.out.println("Códido:" + cidade.getAttributeValue("codigo"));
            System.out.println("Nome:" + cidade.getAttributeValue("nome"));
            System.out.println("Uf:" + cidade.getAttributeValue("uf"));
            System.out.println("\n");

            List clientes = cidade.getChildren();
            Iterator j = clientes.iterator();

            while (j.hasNext()) {
                Element cliente = (Element) j.next();
                System.out.println("Matrícula: " + cliente.getChildText("matricula"));
                System.out.println("Nome: " + cliente.getChildText("nome"));
                System.out.println("Nascimento: " + cliente.getChildText("nascimento"));
                System.out.println("\n");
            }
        }
    }

}
