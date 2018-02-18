package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author a4-matheson
 */
public class GA {

    int populationSize;
    int chromosomeSize;
    double crossoverRate;
    double mutationRate;
    Random random = new Random();

    public GA(int populationSize, int chromosomeSize, double crossoverRate, double mutationRate) {
        this.populationSize = populationSize;
        this.chromosomeSize = chromosomeSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    public Individual[] initPop() {
        Individual[] population = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual(chromosomeSize);
        }
        return population;
    }

    public void evaluatePop(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            population[i].evaluate();
        }
    }

    public Individual[] tournament(Individual[] population) {
        Individual[] selectionTemp = new Individual[populationSize];
        for (int i = 0; i < population.length; i++) {

            Individual parent1 = population[random.nextInt(population.length)];
            Individual parent2 = population[random.nextInt(population.length)];
            
            if (parent1.getFitness() >= parent2.getFitness()) {
                selectionTemp[i] = parent1;
            } else {
                selectionTemp[i] = parent2;
            }
        }
        population = selectionTemp;
        return population;
    }

    public Individual[] crossover(Individual[] population) {
        for (int i = 0; i < population.length - 1; i += 2) {
            Individual offspring1 = new Individual(population[0].getChromosome().length);
            Individual offspring2 = new Individual(population[0].getChromosome().length);

            int xpoint = 1 + random.nextInt(chromosomeSize - 1);

            if (random.nextDouble() < crossoverRate) {
                for (int j = 0; j < xpoint; j++) {
                    offspring1.setGene(j, population[i].getGene(j));
                    offspring2.setGene(j, population[i+1].getGene(j));
                }
                for (int j = xpoint; j < population[0].getChromosome().length; j++) {
                    offspring1.setGene(j, population[i+1].getGene(j));
                    offspring2.setGene(j, population[i].getGene(j));
                }
            }
            population[i] = offspring1;
            population[i+1] = offspring2;
        }
        return population;
    }

    public Individual[] mutate(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            for (int j = 0; j < population[i].getChromosome().length; j++) {
                if (random.nextDouble() < mutationRate) {
                    population[i].mutate(j);
                }
            }
        }
        return population;
    }

    public Individual[] elitism(Individual[] population) {
        Individual min = population[0];
        int minOffset = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() <= min.getFitness()) {
                min = population[i];
                minOffset = i;
            }
        }
        Individual max = population[0];
        int maxOffset = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() >= max.getFitness()) {
                max = population[i];
                maxOffset = i;
            }
        }
        population[minOffset] = population[maxOffset];
        return population;
    }

    // <editor-fold defaultstate="collapsed" desc="Debug logic...">
    public int totalFitness(Individual[] population) {
        int population_fitness = 0;
        for (int i = 0; i < population.length; i++) {
            population_fitness += population[i].getFitness();
        }
        return population_fitness;
    }

    public double avgFitness(Individual[] population) {
        return (double) totalFitness(population) / population.length;
    }

    public int bestFitness(Individual[] population) {
        int max = population[0].getFitness();
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() > max) {
                max = population[i].getFitness();
            }
        }
        return max;
    }
    
        public Individual bestIndividual(Individual[] population) {
        Individual max = population[0];
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() >= max.getFitness()) {
                max = population[i];
            }
        }
        return max;
    }

    public void printFitness(Individual[] population) {
        System.out.println("Total fitness: " + totalFitness(population));
        System.out.println("Average fitness: " + avgFitness(population));
        //System.out.println("Best fitness: " + bestFitness(population));
        System.out.println("Best individual: " + bestIndividual(population));
    }
    
    public String[] returnFitness(Individual[] population) {
        String[] str = new String[3];
        str[0] = Integer.toString(totalFitness(population));
        str[1] = Double.toString(avgFitness(population));
        str[2] = Integer.toString(bestFitness(population));
        return str;
    }
    
    public void printPop(Individual[] population) {
        for (int i = 0; i < population.length; i++) {
            System.out.println(Arrays.toString(population));
        }
    }
    // </editor-fold>
}
