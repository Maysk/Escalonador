/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CaioViktor
 */
import java.util.ArrayList;
import java.util.TreeSet;
public abstract class StrategyEscalonador {
    protected ArrayList<Processo> processos;
    protected TreeSet<Processo> processosEstadoPronto;
    protected Processo processoCorrente;
    protected int tempoCorrente;
    public abstract ArrayList<Execucao> escalonar();
}
