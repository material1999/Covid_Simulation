package Runnable;

import Graph.Network;
import Graph.Node;
import Reader.Read;
import Simulations.SimpleSimulation;

import java.io.*;
import java.util.LinkedList;

public class RunSimulation {

	public static void main(String[] args) throws IOException {

		String networkFilePath;

		LinkedList<Node> initInfected = new LinkedList<>();

		//TODO: randomize, which nodes are infected at the beginning

		// Console info
		System.out.println("\nRunning COVID simulation ...");
		System.out.println("\nParameters:");
		System.out.println("name1: value1, name2: value2, ...");

		// Run the COVID simulation for each file
		for (int i = 1; i <= Parameters.FILE_COUNT; i++) {

			// Path to the current network file
			networkFilePath = Parameters.NETWORKS_FOLDER + i + "/edgeweighted.csv";

			// Generate the network
			Network network = Read.ReadCsv(networkFilePath);

			// Output file with the results
			File output = new File(Parameters.RESULTS_FOLDER + "simulation_" + i + ".txt");

			try {
				// Open FileWriter
				FileWriter fw = new FileWriter(output);

				System.out.println("\nStarted with file " + i);

				// Run the simulation on the current file
				fw.write("Parameters:\n");
				fw.write("name1: value1, name2: value2, ..." + "\n\n");

				// TODO: call the simulation functions here (SimpleSimulation, ...)
				SimpleSimulation.Simulation(network, initInfected, Parameters.SIM_SIZE);

				// Close FileWriter
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Done with file " + i);
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
