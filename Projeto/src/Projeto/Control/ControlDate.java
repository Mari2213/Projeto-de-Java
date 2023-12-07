package Projeto.Control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControlDate {

    public static String SincronizarData(){
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataAtual.format(formatoData);
    }
}
