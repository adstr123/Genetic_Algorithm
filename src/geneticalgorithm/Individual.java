package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author a4-matheson
 */
public class Individual {

    public int[] chromosome;
    public int fitness = 0;
    Random random = new Random();

    public Individual(int chromosomeSize) {
        this.chromosome = new int[chromosomeSize];
        for (int i = 0; i < chromosomeSize; i++) {
            this.setGene(i, random.nextInt(2));
        }
    }

    // Initializes individual with a blank chromosome (all genes 0)
    public Individual(int chromosomeSize, boolean isBlank) {
        this.chromosome = new int[chromosomeSize];
        Arrays.fill(chromosome, 0);
    }

    public void mutate(int offset) {
        if (this.getGene(offset) == 1) {
            this.setGene(offset, 0);
        } else {
            this.setGene(offset, 1);
        }
    }

    public void evaluate() {
        int count = 0;
        for (int offset = 0; offset < this.chromosome.length; offset++) {
            if (this.getGene(offset) == 1) {
                count++;
            }
        }
        this.setFitness(count);
    }

    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        String output = "Binary gene representation: ";
        for (int i = 0; i < this.chromosome.length; i++) {
            output += this.getGene(i);
        }
        System.out.println(output);
        System.out.println("Fitness: " + this.getFitness());
        return output;
    }
}
