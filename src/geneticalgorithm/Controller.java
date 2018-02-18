package geneticalgorithm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Adam Matheson
 */
public class Controller {

    public static void main(String[] args) throws FileNotFoundException {
        final int P = 50;                   // population size
        final int N = 50;                   // chromosome length
        final double C = 0.95;              // crossover rate
        final double M = (double) 1 / P;    // mutation rate
        final int G = 200;                 // # of generations

        GA ga = new GA(P, N, C, M);

        PrintWriter pw = new PrintWriter("output.csv");
        StringBuilder sb = new StringBuilder();

        // Initialise population of random individuals
        Individual[] population = ga.initPop();

        // "Counting ones" fitness evaluation
        ga.evaluatePop(population);
        ga.printFitness(population);

        int generation = 1;
        for (int i = 0; i < G; i++) {

            System.out.println("\nGeneration: " + generation);

            // Tournament selection
            population = ga.tournament(population);

            // Tournament winners fitness evaluation
            ga.evaluatePop(population);

            // Single-point crossover
            population = ga.crossover(population);

            // Crossover children fitness evaluation
            ga.evaluatePop(population);

            // Bit-wise mutation
            population = ga.mutate(population);

            // Post-mutation population fitness evaluation
            ga.evaluatePop(population);

            // Elitism replacement (remove the worst gene and replace with a copy of the best)
            population = ga.elitism(population);

            // Post-elitism population fitness evaluation
            ga.evaluatePop(population);
            ga.printFitness(population);

            // output generation to csv
            for (int j = 0; j < ga.returnFitness(population).length; j++) {
                sb.append(ga.returnFitness(population)[j]);
                if (j != 2) {
                    sb.append(',');
                }
            }
            sb.append("\n");

            // Setup next generation and check for termination
            generation++;

            if (ga.bestFitness(population) == 50) {
                break;
            }
        }
        pw.write(sb.toString());
        pw.close();
    }
}
