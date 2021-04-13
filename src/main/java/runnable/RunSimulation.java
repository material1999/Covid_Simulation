package runnable;

import graph.Network;
import graph.Node;
import model.Variant;
import reader.Read;
import simulations.SimpleSimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RunSimulation {

	public static void main(String[] args) throws IOException {

		String networkFilePath;

		LinkedList<Node> initInfected = new LinkedList<>();

		// Create and store virus variants
		ArrayList<Variant> variants = Variant.loadVariants();
		System.out.println("\n" + variants);

		// Console info
		System.out.println("\nRunning COVID simulation ...");
		System.out.println("\nParameters:");
		System.out.println("name1: value1, name2: value2, ...");

		// Run the COVID simulation for each file
		//for (int i = 1; i <= Parameters.FILE_COUNT; i++) {
		for (int i = 1; i <= 1; i++) {

			System.out.println("\nStarted with file " + i + "\n");

			// Path to the current network file
			networkFilePath = Parameters.NETWORKS_FOLDER + i + "/edgeweighted.csv";

			// Generate the network
			Network network = Read.ReadCsv(networkFilePath);

			// Set which nodes are infected at the beginning
			initInfected = Variant.setInitInfected(network.getNodeMap(), variants);
			System.out.println("\n" + initInfected);

			// Output file with the results
			File output = new File(Parameters.RESULTS_FOLDER + "simulation_" + i + ".txt");

			try {
				// Open FileWriter
				FileWriter fw = new FileWriter(output);

				// Run the simulation on the current file
				fw.write("Parameters:\n");
				fw.write("name1: value1, name2: value2, ..." + "\n\n");

				// Call the SimpleSimulation function
				SimpleSimulation.Simulation(network, initInfected, Parameters.SIM_SIZE);

				// TODO: call other simulation functions here

				// Close FileWriter
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("\nDone with file " + i);
		}

		System.out.println("\nProgram finished successfully, press ENTER to exit ...");

		// Waiting for ENTER to exit
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
