package br.com.vbank.timers;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class SincronizacaoComBancoCentralDoBrasil {

    @Schedule(second = "*", minute = "*/5", hour = "*")
    public void sincronizar() {

    }
}
