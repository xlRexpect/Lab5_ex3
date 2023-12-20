package ex3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static List<Mobilier> citire() {
        try {
            File file=new File("src/main/resources/mobilier.json");
            ObjectMapper mapper=new ObjectMapper();
            List<Mobilier> persoane = mapper
                    .readValue(file, new TypeReference<List<Mobilier>>(){});
            return persoane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Mobilier> listaMobilier=citire();
        //System.out.println(listaMobilier);
        Scanner scan=new Scanner(System.in);
        int ui=0;
        do{
            System.out.println("0=exit");
            System.out.println("1=afisare");
            System.out.println("2=cautare mobilier");
            System.out.println("3=numărul colilor de pal necesare pentru un mobilier");
            ui=scan.nextInt();
            switch (ui){
                case 0->{
                    System.out.println("iesire din program");
                }
                case 1->{
                    for(Mobilier m:listaMobilier) {
                        System.out.println(m);
                    }
                }
                case 2->{
                    System.out.println("numele mobilierului?");
                    Scanner scan2=new Scanner(System.in);
                    String uiName=scan2.nextLine();
                    for(int i=0;i<listaMobilier.size();i++){
                        if(uiName.equals(listaMobilier.get(i).getNume())){
                            System.out.println(listaMobilier.get(i));
                        }
                    }
                }
                case 3->{
                    System.out.println("numele mobilierului?");
                    Scanner scan2=new Scanner(System.in);
                    String uiName=scan2.nextLine();
                    float dim=0;
                    boolean found = false;
                    for(int i=0;i<listaMobilier.size();i++){
                        if(uiName.equals(listaMobilier.get(i).getNume())){
                            found = true;
                            for(Placa p:listaMobilier.get(i).getPlaci()){
                                dim=dim+(p.getLungime()*p.getLatime()*p.getNr_bucati());
                                //System.out.println(dim);
                            }
                            break;
                        }
                    }
                    if (found) {
                        float arieCoala = 2800 * 2070;
                        int numarColiNecesare = (int) Math.ceil(dim / arieCoala);
                        float numarColiNecesare2=dim/arieCoala;
                        System.out.println("Numărul estimativ de coli de pal necesare: " + numarColiNecesare+" ("+numarColiNecesare2+") ");
                    } else {
                        System.out.println("Mobilierul nu a fost găsit în lista dvs.");
                    }
                }
            }
        }
        while(ui!=0);

    }

}
